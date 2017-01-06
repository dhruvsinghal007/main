Ext.define('Accounts.view.main.db.AccountList', {
    extend: 'Ext.grid.Panel',
	xtype: 'accountList',
    requires: [
        'Accounts.store.AccountStore'
    ],
    title: 'Accounts',
    store: 'Accounts.store.AccountStore',
	
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
				{ text: 'Id',  dataIndex: 'accId', width : 40 },
				{ text: 'Account Name',  dataIndex: 'name',flex : 1, editor : {allowBlank : true} },
				{ 
					text: 'Account Type', 
					dataIndex: 'type', 
					flex: 1, 
					editor : {
						xtype : 'combo',
						allowBlank: false,
						typeAhead : true,
						value : 'Firm',
						store : [
							['Firm', 'Firm'],
							['Commodity', 'Commodity']
						]
					} 
				}
			],
			
			tbar : [{
				xtype : 'tbtext',
				id : 'accounttxt',
				text : 'New'
			}],
			
			bbar : [
			{
				text : 'Create',
				scope : this,
				handler : function(){
					//alert("Added");
					
					var accountStore = Ext.data.StoreManager.lookup('Accounts.store.AccountStore');
					
					var accountModel = Ext.create("Accounts.model.Account");
					
					accountModel.set("name","Enter Account Name");
					accountModel.set("type","Firm");
					
					Ext.Ajax.request({
						url : '/accounts/addAccount',
						method : 'POST',
						jsonData : {
							"name" : accountModel.get("name"),
							"type" : accountModel.get("type")
						},
						headers : {
							'Content-Type' : 'application/json'
						},
						success : function(response,request){
							// process server response here
							
							var resp = Ext.decode(response.responseText);
						
							if(resp != null){
								var acc = new Accounts.model.Account(resp);
								//console.log(acc);
								accountModel.set("accId",acc.get("accId"));
								accountModel.set("name",acc.get("name"));
								accountModel.set("type",acc.get("type"));
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
					accountStore.add(accountModel);
					accountStore.sync();
					this.editing.startEdit(accountModel);
				}
			},
			{
				text : 'Update',
				scope : this,
				handler : function(){
					//alert("Updated");
					
					var accounts = this.getSelectionModel().getSelection();
					var accountStore = Ext.data.StoreManager.lookup('Accounts.store.AccountStore');
					var messageRow = Ext.getCmp('accounttxt');
					
					var data = [];
					for(var i = 0 ; i < accounts.length ; i++){	
						data[i] = new Object({
							accId : accounts[i].get("accId"),
							name : accounts[i].get("name"),
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
										url : '/accounts/multiUpdateAccounts',
										method : 'PUT',
										jsonData : data,
										success : function(response,request){
											
											var resp = Ext.decode(response.responseText);
											
											var accounts = [];
											
											for(var i = 0 ; i < resp.length ; i++){
												var account = Ext.create('Accounts.model.Account',resp[i]);
												
												accounts[i] = account;
											}
											
											accountStore.load(accounts);
											
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
					var accountStore = Ext.data.StoreManager.lookup('Accounts.store.AccountStore');
					var accounts = this.getSelectionModel().getSelection();
					var messageRow = Ext.getCmp('accounttxt');
					
					var data = [];
					for(var i = 0 ; i < accounts.length ; i++){
						
						data[i] = new Object({
							accId : accounts[i].get("accId"),
							name : accounts[i].get("name"),
							type : accounts[i].get("type")
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
										url : '/accounts/multiDeleteAccounts',
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
									accountStore.remove(accounts);
									accountStore.load();
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
				store : 'Accounts.store.AccountStore'
			}]
		});
		
		this.callParent();
		
	}
	
});
