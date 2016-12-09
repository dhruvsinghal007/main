Ext.define('Accounts.view.main.Main', {
    extend: 'Ext.tab.Panel',
    xtype: 'main',
    
	requires: [
		'Accounts.view.main.CustomerList', 
        'Accounts.view.main.ItemList', 
        'Accounts.view.main.SalePurchaseList', 
        'Accounts.view.main.PaymentList',
        'Ext.plugin.Viewport',
        'Ext.window.MessageBox',
        'Accounts.view.main.MainController',
        'Accounts.view.main.MainModel'
		],
               
    controller: 'main',
    viewModel: 'main',
    tabBarPosition: 'bottom',
    
    items: [{
        xtype: 'customerList',
        title: 'Customers'
    }, {
        xtype: 'itemList',
        title: 'Items'
    }, {
        xtype: 'salePurchaseList',
        title: 'SaleAndPurchases'
    },
    {
        xtype: 'paymentList',
        title: 'Payments'
    }
	]
});
