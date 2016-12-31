Ext.define('Accounts.view.main.MainDB', {
    extend: 'Ext.tab.Panel',
    xtype: 'mainDB',
    
	requires: [
		'Ext.plugin.Viewport',
        'Ext.window.MessageBox',
        'Accounts.view.main.MainController',
        'Accounts.view.main.MainModel'
	],
    
	viewModel: 'main',
    tabBarPosition: 'bottom',
    
    items: [{
		xtype : 'accountList',
		title : 'AccountsDB'
	}]
});
