Ext.define('Accounts.view.main.ItemList', {
    extend: 'Ext.grid.Panel',
	xtype: 'itemList',
    requires: [
        'Accounts.store.ItemStore'
    ],
    title: 'Items',
    store: 'Accounts.store.ItemStore',
	
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
				{ text: 'Id',  dataIndex: 'itemId', width : 40 },
				{ text: 'Item Label', dataIndex: 'name', flex: 1, editor : {allowBlank : false} },
				{ text: 'Stock', dataIndex: 'netQuantity', flex: 1, editor : {allowBlank : true} },
				{ text: 'Per Unit Cost', dataIndex: 'cost', flex: 1, editor : {allowBlank : true} }
			],
			
			tbar : [{
				xtype : 'tbtext',
				id : 'itemtxt',
				text : 'New'
			}],
			
			bbar : [
			{
				text : 'Create',
				scope : this,
				handler : function(){
					//alert("Added");
					
					var itemStore = Ext.data.StoreManager.lookup('Accounts.store.ItemStore');
					
					var itemModel = Ext.create("Accounts.model.Item");
					itemModel.set("name","New Item");
					var id = itemStore.max("itemId");
					
					id++;
					
					itemModel.set("itemId",id);
					itemModel.set("netQuantity",0);
					itemModel.set("cost",0);
					
					//console.log(current);
					Ext.Ajax.request({
						url : '/AccountingWebApp/accounts/addItem',
						method : 'POST',
						jsonData : {
							"name" : itemModel.get("name") ,
							"netQuantity" : itemModel.get("netQuantity"),
							"cost" : itemModel.get("cost")
						},
						headers : {
							'Content-Type' : 'application/json'
						},
						success : function(response,request){
							// process server response here
						
							var resp = Ext.decode(response.responseText);
						
							if(resp != null){
								var acc = new Accounts.model.Item(resp);
								
								itemModel.set("itemId",acc.get("itemId"));
								itemModel.set("name",acc.get("name"));
								itemModel.set("netQuantity",acc.get("netQuantity"));
								itemModel.set("cost",acc.get("cost"));
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
					itemStore.add(itemModel);
					itemStore.sync();
					this.editing.startEdit(itemModel);
				}
			},
			{
				text : 'Update',
				scope : this,
				handler : function(){
					//alert("Updated");
					
					var items = this.getSelectionModel().getSelection();
					var itemStore = Ext.data.StoreManager.lookup('Accounts.store.ItemStore');
					var messageRow = Ext.getCmp('itemtxt');
					
					var data = [];
					for(var i = 0 ; i < items.length ; i++){	
						data[i] = new Object({
							itemId : items[i].get("itemId"),
							name : items[i].get("name"),
							netQuantity : items[i].get("netQuantity"),
							cost : items[i].get("cost")
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
										url : '/AccountingWebApp/accounts/multiUpdateItems',
										method : 'PUT',
										jsonData : data,
										success : function(response,request){
											
											var resp = Ext.decode(response.responseText);
											
											var items = [];
											
											for(var i = 0 ; i < resp.length ; i++){
												var customer = Ext.create('Accounts.model.Item',resp[i]);
												
												items[i] = customer;
											}
											
											itemStore.load(items);
											
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
					var itemStore = Ext.data.StoreManager.lookup('Accounts.store.ItemStore');
					var items = this.getSelectionModel().getSelection();
					var messageRow = Ext.getCmp('itemtxt');
					
					var data = [];
					for(var i = 0 ; i < items.length ; i++){
						
						data[i] = new Object({
							itemId : items[i].get("itemId"),
							name : items[i].get("name"),
							netQuantity : items[i].get("netQuantity"),
							cost : items[i].get("cost")
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
										url : '/AccountingWebApp/accounts/multiDeleteItems',
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
									itemStore.remove(items);
									itemStore.load();
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
				store : 'Accounts.store.ItemStore'
			}
			]
		});
		
		this.callParent();
		
	}
	
});
