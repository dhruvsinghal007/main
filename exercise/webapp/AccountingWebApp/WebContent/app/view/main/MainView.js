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
					xtype: 'textfield',
					fieldLabel: 'Enter Item Id',
					anchor: '-15',
					id : 'mainItem'
				}, {
					xtype : 'combo',
					allowBlank: false,
					typeAhead : true,
					anchor: '-15',
					fieldLabel: 'Select Mode',
					value : 'Credit',
					id : 'mainMode',
					store : [
						['Debit', 'Debit'],
						['Credit', 'Credit']
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
						Ext.Ajax.request({
							url : '/AccountingWebApp/accounts/viewItem?id='+itemId,
							method : 'GET',
							success : function(response,request){
								// process server response here
								var resp = Ext.decode(response.responseText);			
								var model = Ext.create('Accounts.model.Item', resp);
								//console.log(model);
								
								var mode = Ext.getCmp('mainMode');
								var modeVal = mode.getValue();
								var num = Ext.getCmp('mainNum');
								var quantity = num.getValue();
								
								//console.log(model.get('netQuantity'));
								
								if(modeVal == 'Credit'){
									var cost = quantity * model.get('netQuantity');
									total = total + cost;
								}
								
								//console.log(total);
								
								var txt = Ext.getCmp('mainTotal');
								txt.setValue(total);
								
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