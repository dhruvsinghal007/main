Ext.define('Accounts.view.main.MainView', {
    extend: 'Ext.form.Panel',
    xtype: 'mainView',

	title: 'Main',
	width: 500,
	layout: 'form',
	
	initComponent : function(){
		Ext.apply(this,{
		
			items: [{
				xtype: 'fieldset',
				layout: 'anchor',
				items: [{
					xtype: 'component',
					anchor: '100%'
				},{
					xtype: 'displayfield',
					fieldLabel: 'Selected Item',
					anchor: '-15',
					id : 'itemName'
				},{
					xtype: 'textfield',
					fieldLabel: 'Enter Item Id',
					anchor: '-15',
					id : 'mainItem',
					listeners: {
						'change': function(){
							var item = Ext.getCmp('mainItem');
							var itemId = item.getValue();
							Ext.Ajax.request({
								url : '/AccountingWebApp/accounts/viewItem?id='+itemId,
								method : 'GET',
								success : function(response,request){
									var resp = Ext.decode(response.responseText);			
									var model = Ext.create('Accounts.model.Item', resp);
									
									var name = Ext.getCmp('itemName');
									name.setValue(model.get('name'));
								},
								failure : function(response,request){
									console.log(itemId);
								}
							});
						}
					}
				}, {
					xtype : 'combo',
					allowBlank: false,
					typeAhead : true,
					anchor: '-15',
					fieldLabel: 'Select Mode',
					value : 'Sale',
					id : 'mainMode',
					store : [
						['Sale', 'Sale'],
						['Purchase', 'Purchase']
					]
				},{
					xtype: 'numberfield',
					name: 'numberfield1',
					id : 'mainNum',
					fieldLabel: 'Enter quantity per unit',
					value: 1,
					minValue: 1,
					anchor : '-15',
					maxValue: 50
				},{
					xtype: 'displayfield',
					fieldLabel: 'Net Total',
					anchor: '-15',
					id : 'mainTotal'
				},{
					xtype : 'button',
					text : 'Get Total',
					flex : 1,
					id : 'mainButton',
					style : {
						margin : '5px',
						marginLeft : '50px'
					},
					handler : function(){
						var item = Ext.getCmp('mainItem');
						var itemId = item.getValue();
						var total = 0;
						var spId = 0;
						Ext.Ajax.request({
							url : '/AccountingWebApp/accounts/viewItem?id='+itemId,
							method : 'GET',
							success : function(response,request){
								var resp = Ext.decode(response.responseText);			
								var model = Ext.create('Accounts.model.Item', resp);
								
								var mode = Ext.getCmp('mainMode');
								var modeVal = mode.getValue();
								var num = Ext.getCmp('mainNum');
								var quantity = num.getValue();
								
								var cost = quantity * model.get('netQuantity');
								
								total = total + cost;
								
								var txt = Ext.getCmp('mainTotal');
								txt.setValue(total);
								
								var sp = Ext.create('Accounts.model.SalePurchase');
								sp.set('itemId', itemId);
								sp.set('mode', modeVal);
								sp.set('quantity', quantity);
								
								var current = new Date();
								current = Ext.Date.format(current,'dmY')
								sp.set("date",current);
								
								Ext.Ajax.request({
									url : '/AccountingWebApp/accounts/addSalePurchase',
									method : 'POST',
									jsonData : {
										"itemId" : sp.get("itemId") ,
										"quantity" : sp.get("quantity"),
										"mode" : sp.get("mode"),
										"date" : current
									},
									headers : {
										'Content-Type' : 'application/json'
									},
									success : function(response,request){
										// process server response here
										var resp = Ext.decode(response.responseText);
										
										var acc = Ext.create('Accounts.model.SalePurchase',resp);
										spId = acc.get("SPId");
										
										var payment = Ext.create('Accounts.model.Payment');
										payment.set("SPId", spId);
										payment.set("amount",total);
										
										if(modeVal == 'Sale'){
											payment.set("mode","Credit");
										}
										else{
											payment.set("mode","Debit");
										}
										
										console.log(payment.get("SPId"));
										
										Ext.Ajax.request({
											url : '/AccountingWebApp/accounts/addPayment',
											method : 'POST',
											jsonData : {
												"SPId" : payment.get("SPId"),
												"mode" : payment.get("mode"),
												"amount" : payment.get("amount")
											},
											headers : {
												'Content-Type' : 'application/json'
											},
											success : function(response,request){
												// process server response here
												
												var resp = Ext.decode(response.responseText);
								
												if(resp != null){
													var acc = new Accounts.model.Payment(resp);
													
													console.log(acc);
												}
											},
											failure : function(response,request){
												console.log(request);
											}	
										});
										
									},
									failure : function(response,request){
										console.log(request);
										Ext.Msg.alert('Status', 'Save Failed.');
									}	
								});	
							},
							failure : function(response,request){
								console.log(itemId);
							}
						});
					}
				},{
					xtype : 'button',
					text : 'Reset',
					flex : 1,
					id : 'mainReset',
					style : {
						margin : '5px'
					},
					handler : function(){
						var item = Ext.getCmp('mainItem');
						var mode = Ext.getCmp('mainMode');
						var num = Ext.getCmp('mainNum');
						
						item.setValue('0');
						mode.setValue('Credit');
						num.setValue('1');
					}
				}]
			}]
			
			
		});
		
		this.callParent();
	}
	
});