Ext.define('Accounts.view.main.MainDB', {
    extend: 'Ext.tab.Panel',
    xtype: 'mainDB',
    
	requires: [
		'Accounts.view.main.db.SalePurchaseList', 
        'Accounts.view.main.db.PaymentList',
        'Ext.plugin.Viewport',
        'Ext.window.MessageBox',
        'Accounts.view.main.MainController',
        'Accounts.view.main.MainModel'
	],
    
	viewModel: 'main',
    tabBarPosition: 'bottom',
    
    items: [{
        xtype: 'salePurchaseList',
        title: 'SaleAndPurchases'
    },
    {
        xtype: 'paymentList',
        title: 'Payments'
    }]
});
