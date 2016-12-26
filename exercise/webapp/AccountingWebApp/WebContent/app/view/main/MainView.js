Ext.define('Accounts.view.main.MainView', {
    extend: 'Ext.form.Panel',
    xtype: 'mainView',
	requires : ['Accounts.store.AccountStore'],
	title: 'Daily Transactions',
	layout: 'anchor',
	
	initComponent : function(){
		var cost = 0;
		Ext.apply(this,{
		
			items: [{
				xtype: 'fieldset',
				width : 650,
				style : {
					marginLeft : '250px'
				},
				items: [{
					xtype: 'component',
					anchor: '100%'
				},{
					xtype: 'datefield',
					width : 200,
					id : 'mainDate',
					allowBlank : true,
					format : 'd/m/Y',
					renderer : Ext.util.Format.dateRenderer('d/m/Y'),
					style : {
						marginLeft : '200px'
					},
					listeners : {
						'beforerender' : function(){
							var current = new Date();
							var date = Ext.getCmp('mainDate');
							date.setValue(current);
						}
					}
				},{
					xtype : 'combo',
					allowBlank: false,
					typeAhead : true,
					width : 150,
					value : 'Credit',
					id : 'mainMode',
					store : [
						['Credit', 'Credit'],
						['Debit', 'Debit']
					]
				},{
					xtype: 'displayfield',
					fieldLabel: 'Selected Account',
					anchor: '-15',
					id : 'itemDisplay'
					//bind : '{accounts.value}',
				},{
					xtype: 'combobox',
					id : 'mainItem',
					width : 400,
					valueField : 'accId',
					reference: 'accounts',
					publishes: 'value',
					fieldLabel: 'Select Account',
					//displayField : 'name',
					displayTpl:	Ext.create('Ext.XTemplate',
                        '<tpl for=".">',
                            '{name} ({type})', 
                        '</tpl>'
					),
					store: {
						type: 'accountStore'
					},
					queryMode: 'local',
					listConfig: {
						itemTpl: [
							'<div data-qtip="{name}: {type}">{name} ({type})</div>'
						]
					},
					listeners : {
						'select' : function(){
							var itemDisp = Ext.getCmp('itemDisplay');
							var item = Ext.getCmp('mainItem');
							//console.log(item.getValue());
							Ext.Ajax.request({
								url : '/accounts/viewAccount?id=' + item.getValue(),
								success : function(response, request){
									//console.log(response);
									var resp = Ext.decode(response.responseText);
									var acc = Ext.create('Accounts.model.Account', resp);
									//console.log(acc);
									
									var type = acc.get('type');
									//console.log(type);
									
									var qty = Ext.getCmp('mainQty');
									if(type == 'Commodity'){
										qty.setDisabled(false);
										qty.setValue(1);
									}
									else{
										qty.setValue(0);
										qty.setDisabled(true);
									}
									itemDisp.setValue(acc.get('name'));
								},
								failure : function(response, request){
									console.log(request);
								}
							});
						}
					}
				},{
					xtype: 'numberfield',
					width : 250,
					disabled : true,
					name: 'numberfield1',
					id : 'mainQty',
					fieldLabel: 'Enter quantity per unit',
					value: 0,
					minValue: 0,
					maxValue: 50
				},{
					xtype : 'textfield',
					fieldLabel : 'Description',
					id : 'mainDesc',
					width : 450
				},{
					xtype: 'numberfield',
					width : 250,
					fieldLabel: 'Enter amount',
					value: 0,
					minValue: 0,
					id : 'mainAmt'
				},{
					xtype : 'button',
					text : 'Add',
					style : {
						float : 'right',
						width : '100px'
					},
					handler : function(){
						var name = Ext.getCmp('itemDisplay');
						var amt = Ext.getCmp('mainAmt');
						var desc = Ext.getCmp('mainDesc');
						var mode = Ext.getCmp('mainMode');
						
						if(mode.getValue() == 'Credit'){
							var acc = Ext.create('Accounts.model.daily.Credit');
							acc.set('accName', name.getValue());
							acc.set('description', desc.getValue());
							acc.set('amount', amt.getValue());
							
							var grid = Ext.getCmp('creditGrid');
							var accStore = grid.getStore();
							accStore.add(acc);
							//grid.refresh();
						}
						else{
							var acc = Ext.create('Accounts.model.daily.Debit');
							acc.set('accName', name.getValue());
							acc.set('description', desc.getValue());
							acc.set('amount', amt.getValue());
							
							var grid = Ext.getCmp('debitGrid');
							var accStore = grid.getStore();
							accStore.add(acc);
							//grid.refresh();
						}
						name.setValue('');
						var combo = Ext.getCmp('mainItem');
						combo.setValue('');
						mode.setValue('Credit');
						var qty = Ext.getCmp('mainQty');
						if(qty.isDisabled() == false){
							qty.setValue(0);
							qty.setDisabled(true);
						}
						amt.setValue(0);
						desc.setValue('');
					}
				}]
			},{
				xtype: 'panel',
				layout : 'column',
				anchor : '100%',
				items : [{
					xtype : 'grid',
					title : 'Credit',
					width : 500,
					id : 'creditGrid',
					style: {
						marginLeft: '15px'
					},
					store : {
						type : 'creditStore'
					},
					columns : [
						{ text: 'Account Name',  dataIndex: 'accName', flex : 1 },
						{ text: 'Description',  dataIndex: 'description', width : 250 },
						{ text: 'Amount', dataIndex: 'amount', width : 120}
					]
				},{
					xtype : 'grid',
					title : 'Debit',
					width : 500,
					id : 'debitGrid',
					style: {
						marginLeft: '50px'
					},
					store : {
						type : 'debitStore'
					},
					columns : [
						{ text: 'Account Name',  dataIndex: 'accName', flex : 1 },
						{text: 'Description',  dataIndex: 'description', width : 250},
						{ text: 'Amount', dataIndex: 'amount', width : 120}
					]
				}]
			}]
		});
		
		this.callParent();
	}
	
});