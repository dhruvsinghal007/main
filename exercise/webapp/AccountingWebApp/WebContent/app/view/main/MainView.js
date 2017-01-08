Ext.define('Accounts.view.main.MainView', {
    extend: 'Ext.form.Panel',
    xtype: 'mainView',
	requires : ['Accounts.store.AccComboStore'],
	title: 'Daily Transactions',
	
	layout: 'anchor',
	scrollable : true,
	
	listeners : {
		'afterrender' : function(){
			var combo = Ext.getCmp('mainAcc');
			combo.focus();
		}
	},
	
	items : [{
		xtype: 'fieldset',
		width : 825,
		height : 200,
		layout : 'anchor',
		items: [{
			xtype: 'datefield',
			width : 180,
			labelWidth : 50,
			id : 'mainDate',
			allowBlank : true,
			fieldLabel : 'Date',
			style : {
				marginLeft : '15px'
			},
			format : 'd/m/Y',
			renderer : Ext.util.Format.dateRenderer('d/m/Y'),
			listeners : {
				'beforerender' : function(){
					var current = new Date();
					var date = Ext.getCmp('mainDate');
					date.setValue(current);
				},
				'change' : function(){
					var cGrid = Ext.getCmp('creditGrid');
					var cStore = cGrid.getStore();
					var dGrid = Ext.getCmp('debitGrid');
					var dStore = dGrid.getStore();
					
					cStore.removeAll();
					dStore.removeAll();
					
					var panel = Ext.getCmp('topEdit');
					var amt = Ext.getCmp('mainAmt');
					var desc = Ext.getCmp('mainDesc');
					var mode = Ext.getCmp('mainMode');
					var combo = Ext.getCmp('mainAcc');
					var qty = Ext.getCmp('mainQty');
					
					var name = panel.getComponent('mainAcc');
					
					name.setValue('');
					combo.setValue('');
					mode.setValue('Credit');
					
					if(qty.isDisabled() == false){
						qty.setValue(0);
						qty.setDisabled(true);
					}
					amt.setValue(0);
					desc.setValue('');
				}
			}
		},{
			xtype : 'displayfield',
			id : 'mainName',
			hidden : 'true'
		},{
			xtype : 'fieldset',
			border : false,
			layout: 'column',
			id : 'topEdit',
			items : [{
				xtype : 'combo',
				allowBlank: false,
				typeAhead : true,
				fieldLabel : 'Type',
				labelAlign : 'top',
				labelWidth : 50,
				width : 90,
				value : 'Credit',
				id : 'mainMode',
				store : [
					['Credit', 'Credit'],
					['Debit', 'Debit']
				]
			},{
				xtype: 'combobox',
				id : 'mainAcc',
				width : 200,
				valueField : 'accId',
				reference: 'accounts',
				publishes: 'value',
				displayField : 'name',
				emptyText : 'Account',
				store: {
					type: 'accComboStore'
				},
				style : {
					margin : '30px 0px 0px 10px'
				},
				defaultListConfig: { loadingText: null, loadMask: false },
				queryMode: 'local',
				listConfig: {
					itemTpl: [
						'<div data-qtip="{name}: {type}">{name} ({type})</div>'
					]
				},
				listeners : {
					'beforequery' : function(){
						var combo = Ext.getCmp('mainAcc');
						var store = combo.getStore();
						store.reload();
					},
					'specialkey' : function(field, e){
						if (e.getKey() == e.BACKSPACE) {
							var amt = Ext.getCmp('mainAmt');
							var qty = Ext.getCmp('mainQty');
							amt.setValue(0);
							qty.setValue(0);
							amt.setDisabled(true);
							qty.setDisabled(true);
						}
					},
					'select' : function(){
						var item = Ext.getCmp('mainAcc');
						var name = Ext.getCmp('mainName');
						Ext.Ajax.request({
							url : '/accounts/viewAccount?id=' + item.getValue(),
							success : function(response, request){
								//console.log(response);
								var resp = Ext.decode(response.responseText);
								var acc = Ext.create('Accounts.model.Account', resp);
								//console.log(acc);
								
								var type = acc.get('type');
								
								var qty = Ext.getCmp('mainQty');
								
								var amt = Ext.getCmp('mainAmt');
								amt.setDisabled(false);
								
								if(type == 'Commodity'){
									qty.setDisabled(false);
									qty.setValue(1);
									qty.focus();
									qty.selectText();
								}
								else{
									qty.setValue(0);
									qty.setDisabled(true);
									amt.focus();
									amt.selectText();
								}
								name.setValue(acc.get('name'));
							},
							failure : function(response, request){
								console.log(request);
							}
						});
					}
				}
			},{
				xtype: 'numberfield',
				width : 70,
				disabled : true,
				name: 'numberfield1',
				id : 'mainQty',
				fieldLabel: 'Quantity',
				labelAlign : 'top',
				emptyText : 0,
				minValue: 0,
				maxValue: 50,
				style : {
					marginLeft : '10px'
				}
			},{
				xtype : 'textfield',
				value : 'Details',
				id : 'mainDesc',
				emptyText : 'Details',
				width : 250,
				style : {
					margin : '30px 0px 0px 10px'
				}
			},{
				xtype: 'numberfield',
				width : 100,
				value: 0,
				minValue: 0,
				disabled : true,
				hideTrigger: true,
				enableKeyEvents : true,
				id : 'mainAmt',
				style : {
					margin : '30px 0px 0px 10px'
				},
				listeners : {
					'change' : function(){
						var amt = Ext.getCmp('mainAmt');
						var button = Ext.getCmp('mainBtn');
						if(amt.getValue() > 0){
							button.setDisabled(false);
						}
						else{
							button.setDisabled(true);
						}
					},
					'specialkey' : function(f,e){  
						if(e.getKey() == Ext.event.Event.ENTER){
							var btn = Ext.getCmp('mainBtn');
							btn.handler.call(btn.scope);
						}  
					}
				}
			}]
		},{
			xtype : 'fieldset',
			layout : 'column',
			border : false,
			items : [{
				xtype : 'button',
				text : 'Add',
				id : 'mainBtn',
				disabled : true,
				style : {
					width : '100px',
					float : 'right',
					margin : '10px'
				},
				handler : function(){
					var amt = Ext.getCmp('mainAmt');
					var desc = Ext.getCmp('mainDesc');
					var mode = Ext.getCmp('mainMode');
					var name = Ext.getCmp('mainName');
					var qty = Ext.getCmp('mainQty');
					
					var cTotal = Ext.getCmp('creditTotal');
					var dTotal = Ext.getCmp('debitTotal');
					
					if(mode.getValue() == 'Credit'){
						var acc = Ext.create('Accounts.model.daily.Credit');
						acc.set('accName', name.getValue());
						acc.set('description', desc.getValue());
						acc.set('amount', amt.getValue());
						
						if(qty.isDisabled()){
							acc.set('quantity', '');
						}
						else{
							acc.set('quantity', qty.getValue());
						}
						
						var grid = Ext.getCmp('creditGrid');
						var accStore = grid.getStore();
						accStore.add(acc);
						
						cTotal.setValue(accStore.sum('amount'));
					}
					else{
						var acc = Ext.create('Accounts.model.daily.Debit');
						acc.set('accName', name.getValue());
						acc.set('description', desc.getValue());
						acc.set('amount', amt.getValue());
						
						if(qty.isDisabled()){
							acc.set('quantity', '');
						}
						else{
							acc.set('quantity', qty.getValue());
						}
						
						var grid = Ext.getCmp('debitGrid');
						var accStore = grid.getStore();
						accStore.add(acc);
						
						dTotal.setValue(accStore.sum('amount'));
					}
					name.setValue('');
					var combo = Ext.getCmp('mainAcc');
					combo.setValue('');
					combo.focus();
					var qty = Ext.getCmp('mainQty');
					if(qty.isDisabled() == false){
						qty.setValue(0);
						qty.setDisabled(true);
					}
					amt.setValue(0);
					desc.setValue('');
				}
			}]
		}]
	},{
		xtype: 'panel',
		width : 1100,
		height : 290,
		layout : 'column',
		items : [{
			xtype : 'panel',
			width : 500,
			height : 290,
			style: {
				marginLeft: '15px'
			},
			items : [{
				xtype : 'grid',
				title : 'Credit',
				width : 500,
				scrollable : true,
				height : 250,
				layout : 'fit',
				id : 'creditGrid',
				store : {
					type : 'creditStore'
				},
				columns : [
					{ text: 'Account',  dataIndex: 'accName', flex : 1 },
					{ text: 'Description',  dataIndex: 'description', width : 200 },
					{ text: 'Quantity', dataIndex: 'quantity', width : 90},
					{ text: 'Amount', dataIndex: 'amount', width : 100}
				]
			},{
				xtype : 'fieldset',
				items : [{
					xtype : 'displayfield',
					fieldLabel : 'Total',
					layout : 'fit',
					id : 'creditTotal',
					width : 500,
					style : {
						paddingLeft : '300px'
					}
				}]
			}]
		},{
			xtype : 'panel',
			width : 500,
			height : 290,
			style: {
				marginLeft: '50px'
			},
			items : [{
				xtype : 'grid',
				title : 'Debit',
				width : 500,
				scrollable : true,
				height : 250,
				id : 'debitGrid',
				store : {
					type : 'debitStore'
				},
				columns : [
					{ text: 'Account',  dataIndex: 'accName', flex : 1 },
					{ text: 'Description',  dataIndex: 'description', width : 200 },
					{ text: 'Quantity', dataIndex: 'quantity', width : 90},
					{ text: 'Amount', dataIndex: 'amount', width : 100}
				]
			},{
				xtype : 'fieldset',
				items : [{
					xtype : 'displayfield',
					fieldLabel : 'Total',
					width : 500,
					height : 30,
					layout : 'fit',
					id : 'debitTotal',
					style : {
						paddingLeft : '300px'
					}
				}]
			}]
		}]
	}]	
});