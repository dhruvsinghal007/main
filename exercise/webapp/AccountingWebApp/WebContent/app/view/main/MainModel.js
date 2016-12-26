Ext.define('Accounts.view.main.MainModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.main',
    requires: [
		'Accounts.model.SalePurchase',
		'Accounts.model.Payment'
	],
    data: {
        name: 'Accounts'
    },
    stores: {
        salePurchaseStore: {
			model: 'Accounts.model.SalePurchase',
			autoLoad: {
				start : 0,
				limit : 5
			}
		},
		paymentStore: {
			model: 'Accounts.model.Payment',
			autoLoad : {
				start : 0,
				limit : 5
			}
		},
		accountStore: {
			model: 'Accounts.model.Account',
			autoLoad : true
		}
    }
});
