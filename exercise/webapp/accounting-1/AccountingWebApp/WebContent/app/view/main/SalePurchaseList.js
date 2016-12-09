Ext.define('Accounts.view.main.SalePurchaseList', {
    extend: 'Ext.grid.Panel',
	xtype: 'salePurchaseList',
    requires: [
        'Accounts.store.SalePurchaseStore'
    ],
    title: 'Sales and Purchases',
    store: 'Accounts.store.SalePurchaseStore',
	
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
				{ text: 'Id',  dataIndex: 'SPId', width : 40 },
				{ text: 'Item Id', dataIndex: 'itemId', flex: 1, editor : {allowBlank : false} },
				{ 
					text: 'Date', 
					dataIndex: 'date', 
					flex : 1,
					editor : 
					{
						xtype : 'datefield',
						allowBlank : true,
						format : 'd/m/Y'
					},
					renderer : Ext.util.Format.dateRenderer('d/m/Y')
				},
				{ 
					text: 'Sale/Purchase', 
					dataIndex: 'mode', 
					flex : 1,
					editor : 
					{
						xtype : 'combo',
						allowBlank: false,
						editable : true,
						typeAhead : true,
						store : [
							['Sale', 'Sale'],
							['Purchase', 'Purchase']
						]
					}
				},
				{ text: 'Quantity', dataIndex: 'quantity', flex: 1, editor : {allowBlank : true} }
			],
			
			tbar : [{
				xtype : 'tbtext',
				id : 'sptxt',
				text : 'New'
			}],
			
			bbar : [
			{
				text : 'Create',
				scope : this,
				handler : function(){
					//alert("Added");
					
					var spStore = Ext.data.StoreManager.lookup('Accounts.store.SalePurchaseStore');
					
					var spModel = Ext.create("Accounts.model.SalePurchase");
					spModel.set("itemId",0);
					spModel.set("quantity",0);
					
					var current = new Date();
					current = Ext.Date.format(current,'dmY')
					spModel.set("date",current);
					spModel.set("mode","Sale");
					
					//console.log(current);
					Ext.Ajax.request({
						url : '/AccountingWebApp/accounts/addSalePurchase',
						method : 'POST',
						jsonData : {
							"itemId" : spModel.get("itemId") ,
							"quantity" : spModel.get("quantity"),
							"mode" : spModel.get("mode"),
							"date" : current
						},
						headers : {
							'Content-Type' : 'application/json'
						},
						success : function(response,request){
							// process server response here
							
							var resp = Ext.decode(response.responseText);
						
							if(resp != null){
								var acc = new Accounts.model.SalePurchase(resp);
								
								spModel.set("SPId",acc.get("SPId"));
								spModel.set("itemId",acc.get("itemId"));
								spModel.set("quantity",acc.get("quantity"));
								spModel.set("date",acc.get("date"));
								spModel.set("mode",acc.get("mode"));
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
					spStore.add(spModel);
					spStore.sync();
					this.editing.startEdit(spModel);
				}
			},
			{
				text : 'Update',
				scope : this,
				handler : function(){
					//alert("Updated");
					
					var spEntries = this.getSelectionModel().getSelection();
					var spStore = Ext.data.StoreManager.lookup('Accounts.store.SalePurchaseStore');
					var messageRow = Ext.getCmp('sptxt');
					
					//console.log(spEntries);
					
					var data = [];
					for(var i = 0 ; i < spEntries.length ; i++){	
						data[i] = new Object({
							SPId : spEntries[i].get("SPId"),
							itemId : spEntries[i].get("itemId"),
							quantity : spEntries[i].get("quantity"),
							date : Ext.Date.format(spEntries[i].get("date"),'dmY'),
							mode : spEntries[i].get("mode")
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
										url : '/AccountingWebApp/accounts/multiUpdateSalesPurchases',
										method : 'PUT',
										jsonData : data,
										success : function(response,request){
											var resp = Ext.decode(response.responseText);
											
											var spEntries = [];
											
											for(var i = 0 ; i < resp.length ; i++){
												var spEnry = Ext.create('Accounts.model.SalePurchase',resp[i]);
												
												spEntries[i] = spEnry;
											}
											
											spStore.load(spEntries);
											
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
					var spStore = Ext.data.StoreManager.lookup('Accounts.store.SalePurchaseStore');
					var spEntries = this.getSelectionModel().getSelection();
					var messageRow = Ext.getCmp('sptxt');
					
					var data = [];
					for(var i = 0 ; i < spEntries.length ; i++){
						
						data[i] = new Object({
							SPId : spEntries[i].get("SPId"),
							itemId : spEntries[i].get("itemId"),
							quantity : spEntries[i].get("quantity"),
							date : spEntries[i].get("date"),
							mode : spEntries[i].get("mode")
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
										url : '/AccountingWebApp/accounts/multiDeleteSalesPurchases',
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
									spStore.remove(spEntries);
									spStore.load();
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
				store : 'Accounts.store.SalePurchaseStore'
			}
			]
		});
		
		this.callParent();
		
	}
	
});
