Ext.define('Accounts.view.main.MainDB', {
    extend: 'Ext.tab.Panel',
    xtype: 'mainDB',
    
	requires: [
		'Accounts.view.main.db.CustomerList', 
        'Accounts.view.main.db.ItemList', 
        'Accounts.view.main.db.SalePurchaseList', 
        'Accounts.view.main.db.PaymentList',
        'Accounts.view.main.db.LoanList',
        'Ext.plugin.Viewport',
        'Ext.window.MessageBox',
        'Accounts.view.main.MainController',
        'Accounts.view.main.MainModel'
	],
    
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
    },
    {
    	xtype : 'loanList',
    	title : 'Loans'
    }
	]
});
