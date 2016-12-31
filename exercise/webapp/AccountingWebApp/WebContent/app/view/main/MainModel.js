Ext.define('Accounts.view.main.MainModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.main',
    requires: [
		'Accounts.model.Account'
	],
    data: {
        name: 'Accounts'
    },
    stores: {
		accountStore: {
			model: 'Accounts.model.Account',
			autoLoad : true
		}
    }
});
