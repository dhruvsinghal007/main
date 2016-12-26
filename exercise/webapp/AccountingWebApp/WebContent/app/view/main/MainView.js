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
				layout : 'anchor',
				style : {
					marginLeft : '250px'
				},
				items: [{
					xtype: 'component',
					anchor: '100%'
				},{
					xtype: 'datefield',
					width : 250,
					id : 'mainDate',
					allowBlank : true,
					fieldLabel : 'Date',
					format : 'd/m/Y',
					renderer : Ext.util.Format.dateRenderer('d/m/Y'),
					style : {
						marginLeft : '150px'
					},
					listeners : {
						'beforerender' : function(){
							var current = new Date();
							var date = Ext.getCmp('mainDate');
							date.setValue(current);
						}
					}
				},{
					xtype : 'fieldset',
					border : false,
					layout: 'column',
					id : 'topEdit',
					style : {
						paddingLeft : '80px'
					},
					items : [{
						xtype : 'combo',
						allowBlank: false,
						typeAhead : true,
						width : 90,
						value : 'Credit',
						id : 'mainMode',
						store : [
							['Credit', 'Credit'],
							['Debit', 'Debit']
						],
						style : {
							margin : '10px'
						}
					},{
						xtype: 'combobox',
						id : 'mainItem',
						width : 200,
						valueField : 'accId',
						reference: 'accounts',
						publishes: 'value',
						displayField : 'name',
						store: {
							type: 'accountStore'
						},
						style : {
							margin : '10px'
						},
						queryMode: 'local',
						listConfig: {
							itemTpl: [
								'<div data-qtip="{name}: {type}">{name} ({type})</div>'
							]
						},
						listeners : {
							'select' : function(){
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
									},
									failure : function(response, request){
										console.log(request);
									}
								});
							}
						}
					},{
						xtype: 'numberfield',
						width : 100,
						value: 0,
						minValue: 0,
						hideTrigger: true,
						id : 'mainAmt',
						style : {
							margin : '10px'
						}
					}]
				},{
					xtype : 'fieldset',
					layout : 'column',
					border : false,
					items : [{
						xtype: 'numberfield',
						width : 200,
						disabled : true,
						name: 'numberfield1',
						id : 'mainQty',
						fieldLabel: 'Enter quantity per unit',
						value: 0,
						minValue: 0,
						maxValue: 50,
						style : {
							margin : '10px'
						}
					},{
						xtype : 'textfield',
						id : 'mainDesc',
						width : 300,
						style : {
							margin : '10px'
						}
					}]
				},{
					xtype : 'button',
					text : 'Add',
					style : {
						float : 'right',
						width : '100px'
					},
					handler : function(){
						var panel = Ext.getCmp('topEdit');
						var amt = Ext.getCmp('mainAmt');
						var desc = Ext.getCmp('mainDesc');
						var mode = Ext.getCmp('mainMode');
						
						var name = panel.getComponent('mainItem');
						
						console.log(name.getValue());
						console.log(amt.getValue());
						console.log(desc.getValue());
						console.log(mode.getValue());
						
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
				anchor : '-15',
				items : [{
					xtype : 'grid',
					title : 'Credit',
					width : 500,
					scrollable : 'true',
					height : 250,
					layout : 'fit',
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
					scrollable : 'true',
					height : 250,
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