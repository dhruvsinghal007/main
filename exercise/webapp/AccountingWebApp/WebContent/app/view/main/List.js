Ext.define('MyClassic.view.main.List', {
    extend: 'Ext.grid.Panel',
	xtype: 'mainlist',
    requires: [
        'MyClassic.store.Personnel'
    ],
    title: 'Accounts',
    store: 'MyClassic.store.Personnel',
	
	initComponent : function(){
		
		this.editing = Ext.create('Ext.grid.plugin.RowEditing');
		
		Ext.apply(this,{
			plugins : [this.editing],
			width : '100%',
			height : 400,
			selType : 'checkboxmodel',
			viewConfig : 
			{
				stripeRows : true
			},
			selModel: {
				mode : 'MULTI'
			},
			
			height : 400,

			columns: [
				{ text: 'Id',  dataIndex: 'id', width : 40 },
				{ text: 'Name', dataIndex: 'name', flex: 1, editor : {allowBlank : false} },
				{ text: 'Description', dataIndex: 'desc', flex: 1, editor : {allowBlank : true} },
				{ text: 'Town', dataIndex: 'town', flex: 1, editor : {allowBlank : true} },
				{ text: 'Cell No.', dataIndex: 'cell', flex: 1, editor : {allowBlank : true} },
				{ text: 'Email', dataIndex: 'email', flex: 1, editor : {allowBlank : true} },
				{ 
					text: 'Date of Birth', 
					dataIndex: 'dob', 
					flex: 1, 
					editor : 
					{
						xtype : 'datefield',
						allowBlank : true,
						format : 'd/m/Y'
					} ,
					renderer : Ext.util.Format.dateRenderer('d/m/Y')
				},
				{ text: 'Type', dataIndex: 'type', flex: 1, editor : {allowBlank : true}  }
			],
			
			tbar : [{
				xtype : 'tbtext',
				itemId : 'txt',
				text : 'New'
			}],
			
			bbar : [
			{
				text : 'Create',
				scope : this,
				handler : function(){
					//alert("Added");
					
					var accStore = Ext.data.StoreManager.lookup('MyClassic.store.Personnel');
					
					var accModel = Ext.create("MyClassic.model.Account");
					accModel.set("name","New Name");
					var id = accStore.max("id");
					
					id++;
					
					accModel.set("id",id);
					accModel.set("desc","New Desc");
					accModel.set("town","New Town");
					accModel.set("cell",8463839566);
					accModel.set("email","New Email");
					accModel.set("type","New Type");
					
					var current = new Date();
					current = Ext.Date.format(current,'dmY');
					
					//console.log(current);
					accModel.set("dob",current);

					//console.log(accModel);					

					Ext.Ajax.request({
						url : '/accounts/add',
						method : 'POST',
						jsonData : {
							"name" : accModel.get("name") ,
							"description" : accModel.get("desc"),
							"town" : accModel.get("town"),
							"cell" : accModel.get("cell"),
							"email" : accModel.get("email"),
							"dob" : current,
							"type" : accModel.get("type")
						},
						headers : {
							'Content-Type' : 'application/json'
						},
						success : function(response,request){
							// process server response here
						
							var resp = Ext.decode(response.responseText);
						
							if(resp != null){
								var acc = new MyClassic.model.Account(resp);
								
								accModel.set("id",acc.get("id"));
								accModel.set("name",acc.get("name"));
								accModel.set("desc",acc.get("description"));
								accModel.set("town",acc.get("town"));
								accModel.set("cell",acc.get("cell"));
								accModel.set("email",acc.get("email"));
								accModel.set("dob",acc.get("dob"));
								accModel.set("type",acc.get("type"));								
							}
							else{
								Ext.Msg.alert('Status', resp.message);
							}

						},
						failure : function(response,request){
							console.log(request);
							Ext.Msg.alert('Status', 'Save Failed.');
						}	
					});
					this.editing.cancelEdit();
					accStore.add(accModel);
					accStore.sync();
					this.editing.startEdit(accModel);
				}
			},
			{
				text : 'Update',
				scope : this,
				handler : function(){
					//alert("Updated");
					
					var accounts = this.getSelectionModel().getSelection();
					var accStore = Ext.data.StoreManager.lookup('MyClassic.store.Personnel');
					var messageRow = this.getDockedItems()[1].getComponent('txt');
					
					var data = [];
					for(var i = 0 ; i < accounts.length ; i++){	
						var date = Ext.Date.format(new Date(accounts[i].get("dob")),"dmY");
						data[i] = new Object({
							id : accounts[i].get("id"),
							name : accounts[i].get("name"),
							description : accounts[i].get("desc"),
							town : accounts[i].get("town"),
							cell : accounts[i].get("cell"),
							email : accounts[i].get("email"),
							dob : date,
							type : accounts[i].get("type")
						});
						
					}
					
					if (data.length > 0){
						Ext.Msg.show({
							title : 'Update',
							msg : 'Update these records? ',
							width : 300,
							closable : false,
							buttons : Ext.Msg.YESNO,
							icon : Ext.Msg.QUESTION,
							fn : function(buttonValue, inputText, showConfig){
								if(buttonValue === 'yes'){
									
									Ext.Ajax.request({
										url : '/accounts/multiUpdate',
										method : 'PUT',
										jsonData : data,
										success : function(response,request){
											
											var resp = Ext.decode(response.responseText);
											
											var accounts = [];
											
											for(var i = 0 ; i < resp.length ; i++){
												var account = Ext.create('MyClassic.model.Account',resp[i]);
												
												accounts[i] = account;
											}
											
											accStore.load(accounts);
											
											if(resp != null){
												
												messageRow.setStyle({
													textAlign : 'center',
													color : 'red'
												});
												
												messageRow.setText('Updated Successfully');
												Ext.defer(function(){
													messageRow.getEl().setStyle({
														color : 'black'
													});
													messageRow.setText('Ready');
												},3000);
												
											}	
											else{
												Ext.Msg.alert('Status', 'Could not update. Try again.');
											}
										},
										failure : function(response){
											console.log(response);
											Ext.Msg.alert('Status', 'Request Failed.');
										  
										}
									});
								}
							}
					   
						});
					}
					else{
						Ext.Msg.alert('Status', 'Please select at least one record to update!');
					}
					
				}
			},
			{
				text : 'Delete',
				scope : this,
				handler : function(){
					//alert("Delete");
					var accStore = Ext.data.StoreManager.lookup('MyClassic.store.Personnel');
					var accounts = this.getSelectionModel().getSelection();
					var messageRow = this.getDockedItems()[1].getComponent('txt');
					
					var data = [];
					for(var i = 0 ; i < accounts.length ; i++){
						
						var current = Ext.Date.format(new Date(accounts[i].get("dob")),"dmY");
						data[i] = new Object({
							id : accounts[i].get("id"),
							name : accounts[i].get("name"),
							descrption : accounts[i].get("desc"),
							town : accounts[i].get("town"),
							cell : accounts[i].get("cell"),
							email : accounts[i].get("email"),
							dob : current,
							type : accounts[i].get("type"),
						});
					}
					
					if (data.length > 0){
						Ext.Msg.show({
							title : 'Delete',
							msg : 'Do you want to delete these records? ',
							width : 300,
							closable : false,
							buttons : Ext.Msg.YESNO,
							icon : Ext.Msg.QUESTION,
							fn : function(buttonValue, inputText, showConfig){
								if(buttonValue === 'yes'){
									
									Ext.Ajax.request({
										url : '/accounts/multiDelete',
										method : 'PUT',
										jsonData : data,
										success : function(response,request){
											
											messageRow.getEl().setStyle({
												textAlign : 'center',
												color : 'red'
											});
											
											messageRow.getEl().setText('Deleted Successfully');
											Ext.defer(function(){
												messageRow.getEl().setStyle({
													color : 'black'
												});
												messageRow.getEl().setText('Ready');
											},3000);
											
										},
										failure : function(response,request){
											console.log(request);
											console.log(response);
											Ext.Msg.alert('Status', 'Request Failed.');
										
										}
									});
									accStore.remove(accounts);
									accStore.load();
								}
							}
						
						});
				   
						
					}
					else{
						Ext.Msg.alert('Status', 'Please select at least one record to delete!');
					}
				}
			},
			{
				xtype : 'pagingtoolbar',
				displayInfo: true,
				store : 'MyClassic.store.Personnel'
			}
			]
		});
		
		this.callParent();
		
	}
	
});
