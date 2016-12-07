Ext.define('Accounts.view.main.SalePurchaseList', {
    extend: 'Ext.grid.Panel',
	xtype: 'salePurchaseList',
    requires: [
        'Accounts.store.SalePurchaseStore'
    ],
    title: 'Sales and Purchases',
    store: 'Accounts.store.SalePurchaseStore',
	
	initComponent : function(){
		
		console.log("SalePurchase");
		
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
				{ text: 'Id',  dataIndex: 'spId', width : 40 },
				{ text: 'Item Id', dataIndex: 'itemId', flex: 1, editor : {allowBlank : false} },
				{ 
					text: 'Date', 
					dataIndex: 'date', 
					flex: 1, 
					editor : 
					{
						xtype : 'datefield',
						allowBlank : true,
						format : 'd/m/Y'
					},
					renderer : Ext.util.Format.dateRenderer('d/m/Y')
				},
				{ text: 'Sale/Purchase', dataIndex: 'mode', flex: 1, editor : {allowBlank : true} },
				{ text: 'Quantity', dataIndex: 'quantity', flex: 1, editor : {allowBlank : true} }
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
					
					var spStore = Ext.data.StoreManager.lookup('Accounts.store.SalePurchaseStore');
					
					var spModel = Ext.create("Accounts.model.SalePurchase");
					var id = spStore.max("spId");
					
					id++;
					
					spModel.set("spId",id);
					spModel.set("itemId",0);
					spModel.set("quantity",0);
					
					var current = new Date();
					current = Ext.Date.format(current,'dmY')
					spModel.set("date",current);
					
					spModel.set("mode","Sale");
					
					//console.log(current);
					Ext.Ajax.request({
						url : '/accounts/addItem',
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
								var acc = new Accounts.model.Item(resp);
								
								spModel.set("spId",acc.get("spId"));
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
					var spStore = Ext.data.StoreManager.lookup('Accounts.store.ItemStore');
					var messageRow = this.getDockedItems()[1].getComponent('txt');
					
					var data = [];
					for(var i = 0 ; i < spEntries.length ; i++){	
						data[i] = new Object({
							spId : spEntries[i].get("spId"),
							itemId : spEntries[i].get("itemId"),
							quantity : spEntries[i].get("quantity"),
							date : spEntries[i].get("date"),
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
										url : '/accounts/multiUpdateItems',
										method : 'PUT',
										jsonData : data,
										success : function(response,request){
											
											var resp = Ext.decode(response.responseText);
											
											var spEntries = [];
											
											for(var i = 0 ; i < resp.length ; i++){
												var customer = Ext.create('Accounts.model.Item',resp[i]);
												
												spEntries[i] = customer;
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
					var spStore = Ext.data.StoreManager.lookup('Accounts.store.ItemStore');
					var spEntries = this.getSelectionModel().getSelection();
					var messageRow = this.getDockedItems()[1].getComponent('txt');
					
					var data = [];
					for(var i = 0 ; i < spEntries.length ; i++){
						
						data[i] = new Object({
							spId : spEntries[i].get("spId"),
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
										url : '/accounts/multiDeleteItems',
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
				store : 'Accounts.store.ItemStore'
			}
			]
		});
		
		this.callParent();
		
	}
	
});
