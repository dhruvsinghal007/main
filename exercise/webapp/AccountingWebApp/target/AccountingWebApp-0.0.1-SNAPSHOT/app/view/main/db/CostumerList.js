Ext.define('Accounts.view.main.db.CustomerList', {
    extend: 'Ext.grid.Panel',
	xtype: 'customerList',
    requires: [
        'Accounts.store.CustomerStore'
    ],
    title: 'Customers',
    store: 'Accounts.store.CustomerStore',
	
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
				{ text: 'Id',  dataIndex: 'custId', width : 40 },
				{ text: 'Name', dataIndex: 'name', flex: 1, editor : {allowBlank : false} },
				{ text: 'Address', dataIndex: 'address', flex: 1, editor : {allowBlank : true} },
				{ text: 'Cell No.', dataIndex: 'contact', flex: 1, editor : {allowBlank : true} }
			],
			
			tbar : [{
				xtype : 'tbtext',
				id : 'custtxt',
				text : 'New'
			}],
			
			bbar : [
			{
				text : 'Create',
				scope : this,
				handler : function(){
					var custStore = Ext.data.StoreManager.lookup('Accounts.store.CustomerStore');
					
					var custModel = Ext.create("Accounts.model.Customer");
					custModel.set("name","New Name");
					custModel.set("address","New Address");
					custModel.set("contact",8463839566);
					
					//console.log(current);
					Ext.Ajax.request({
						url : '/AccountingWebApp/accounts/addCustomer',
						method : 'POST',
						jsonData : {
							"name" : custModel.get("name") ,
							"address" : custModel.get("address"),
							"contact" : custModel.get("contact")
						},
						headers : {
							'Content-Type' : 'application/json'
						},
						success : function(response,request){
							// process server response here
						
							var resp = Ext.decode(response.responseText);
							
							if(resp != null){
								var acc = new Accounts.model.Customer(resp);
								
								custModel.set("custId",acc.get("custId"));
								custModel.set("name",acc.get("name"));
								custModel.set("address",acc.get("address"));
								custModel.set("contact",acc.get("contact"));
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
					custStore.add(custModel);
					custStore.sync();
					this.editing.startEdit(custModel);
				}
			},
			{
				text : 'Update',
				scope : this,
				handler : function(){
					
					var customers = this.getSelectionModel().getSelection();
					var custStore = Ext.data.StoreManager.lookup('Accounts.store.CustomerStore');
					var messageRow = Ext.getCmp('custtxt');
					
					//console.log(Ext.getCmp('txt'));
					
					var data = [];
					for(var i = 0 ; i < customers.length ; i++){	
						data[i] = new Object({
							custId : customers[i].get("custId"),
							name : customers[i].get("name"),
							address : customers[i].get("address"),
							contact : customers[i].get("contact")
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
										url : '/AccountingWebApp/accounts/multiUpdateCustomers',
										method : 'PUT',
										jsonData : data,
										success : function(response,request){
											
											var resp = Ext.decode(response.responseText);
											
											var customers = [];
											
											for(var i = 0 ; i < resp.length ; i++){
												var customer = Ext.create('Accounts.model.Customer',resp[i]);
												
												customers[i] = customer;
											}
											
											custStore.load(customers);
											
											if(resp != null){
												
												messageRow.setStyle({
													textAlign : 'center',
													color : 'red'
												});
												
												messageRow.setText('Updated Successfully');
												Ext.defer(function(){
													messageRow.setStyle({
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
					var custStore = Ext.data.StoreManager.lookup('Accounts.store.CustomerStore');
					var customers = this.getSelectionModel().getSelection();
					var messageRow = Ext.getCmp('custtxt');
					
					var data = [];
					for(var i = 0 ; i < customers.length ; i++){
						
						data[i] = new Object({
							custId : customers[i].get("custId"),
							name : customers[i].get("name"),
							address : customers[i].get("address"),
							contact : customers[i].get("contact")
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
										url : '/AccountingWebApp/accounts/multiDeleteCustomers',
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
									custStore.remove(customers);
									custStore.load();
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
				store : 'Accounts.store.CustomerStore'
			}
			]
		});
		
		this.callParent();
		
	}
	
});
