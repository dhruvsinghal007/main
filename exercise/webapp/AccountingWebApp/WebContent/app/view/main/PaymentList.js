Ext.define('Accounts.view.main.PaymentList', {
    extend: 'Ext.grid.Panel',
	xtype: 'paymentList',
    requires: [
        'Accounts.store.PaymentStore'
    ],
    title: 'Payments',
    store: 'Accounts.store.PaymentStore',
	
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
				{ text: 'Id',  dataIndex: 'trId', width : 40 },
				{ text: 'SalePurchaseId',  dataIndex: 'trId',flex: 1, editor : {allowBlank : true} },
				{ text: 'CustomerId',  dataIndex: 'trId', flex: 1, editor : {allowBlank : true} },
				{ 
					text: 'Credit/Debit', 
					dataIndex: 'mode', 
					flex : 1,
					editor : 
					{
						xtype : 'combo',
						allowBlank: false,
						typeAhead : true,
						renderTo: Ext.getBody(),
						store : [
							['Debit', 'Debit'],
							['Credit', 'Credit']
						]
					}
				},{ text: 'Amount', dataIndex: 'amount', flex: 1, editor : {allowBlank : true} }
			],
			
			tbar : [{
				xtype : 'tbtext',
				id : 'paymenttxt',
				text : 'New'
			}],
			
			bbar : [
			{
				text : 'Create',
				scope : this,
				handler : function(){
					//alert("Added");
					
					var paymentStore = Ext.data.StoreManager.lookup('Accounts.store.PaymentStore');
					
					var paymentModel = Ext.create("Accounts.model.Payment");
					
					paymentModel.set("spId",0);
					paymentModel.set("custId",0);
					paymentModel.set("amount",0);
					paymentModel.set("mode","Debit");
					
					Ext.Ajax.request({
						url : '/AccountingWebApp/accounts/addPayment',
						method : 'POST',
						jsonData : {
							"spId" : paymentModel.get("spId"),
							"custId" : paymentModel.get("custId"),
							"mode" : paymentModel.get("mode"),
							"amount" : paymentModel.get("amount")
						},
						headers : {
							'Content-Type' : 'application/json'
						},
						success : function(response,request){
							// process server response here
							
							console.log(response);
							console.log(request);
							var resp = Ext.decode(response.responseText);
						
							if(resp != null){
								var acc = new Accounts.model.Payment(resp);
								//console.log(acc);
								paymentModel.set("trId",acc.get("trId"));
								paymentModel.set("spId",acc.get("spId"));
								paymentModel.set("custId",acc.get("custId"));
								paymentModel.set("mode",acc.get("mode"));
								paymentModel.set("amount",acc.get("amount"));
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
					paymentStore.add(paymentModel);
					paymentStore.sync();
					this.editing.startEdit(paymentModel);
				}
			},
			{
				text : 'Update',
				scope : this,
				handler : function(){
					//alert("Updated");
					
					var payments = this.getSelectionModel().getSelection();
					var paymentStore = Ext.data.StoreManager.lookup('Accounts.store.PaymentStore');
					var messageRow = Ext.getCmp('paymenttxt');
					
					var data = [];
					for(var i = 0 ; i < payments.length ; i++){	
						data[i] = new Object({
							trId : payments[i].get("trId"),
							spId : payments[i].get("spId"),
							custId : payments[i].get("custId"),
							mode : payments[i].get("mode"),
							amount : payments[i].get("amount")
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
										url : '/AccountingWebApp/accounts/multiUpdatePayments',
										method : 'PUT',
										jsonData : data,
										success : function(response,request){
											
											var resp = Ext.decode(response.responseText);
											
											var payments = [];
											
											for(var i = 0 ; i < resp.length ; i++){
												var payment = Ext.create('Accounts.model.Payment',resp[i]);
												
												payments[i] = payment;
											}
											
											paymentStore.load(payments);
											
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
					var paymentStore = Ext.data.StoreManager.lookup('Accounts.store.PaymentStore');
					var payments = this.getSelectionModel().getSelection();
					var messageRow = Ext.getCmp('paymenttxt');
					
					var data = [];
					for(var i = 0 ; i < payments.length ; i++){
						
						data[i] = new Object({
							trId : payments[i].get("trId"),
							spId : payments[i].get("spId"),
							custId : payments[i].get("custId"),
							mode : payments[i].get("mode"),
							amount : payments[i].get("amount")
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
										url : '/AccountingWebApp/accounts/multiDeletePayments',
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
									paymentStore.remove(payments);
									paymentStore.load();
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
				store : 'Accounts.store.PaymentStore'
			}
			]
		});
		
		this.callParent();
		
	}
	
});
