Ext.define('Accounts.Application', {
    extend: 'Ext.app.Application',
    
	requires : [
		'Accounts.store.AccountStore',
		'Accounts.store.daily.CreditStore',
		'Accounts.store.daily.DebitStore'
	],
	
    name: 'Accounts',
	
	stores : [
		'Accounts.store.AccountStore',
		'Accounts.store.daily.CreditStore',
		'Accounts.store.daily.DebitStore'
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
