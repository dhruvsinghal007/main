Ext.define('Accounts.Application', {
    extend: 'Ext.app.Application',
    
	requires : [
		'Accounts.store.CustomerStore',
		'Accounts.store.ItemStore',
		'Accounts.store.SalePurchaseStore',
		'Accounts.store.PaymentStore'
	],
	
    name: 'Accounts',
	
	stores : [
		'Accounts.store.CustomerStore',
		'Accounts.store.ItemStore',
		'Accounts.store.SalePurchaseStore',
		'Accounts.store.PaymentStore'
	],

    onAppUpdate: function () {
        Ext.Msg.confirm('Application Update', 'This application has an update, reload?',
            function (choice) {
                if (choice === 'yes') {
                    window.location.reload();
                }
            }
        );
    }
});
