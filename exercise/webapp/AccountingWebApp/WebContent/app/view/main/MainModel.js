Ext.define('Accounts.view.main.MainModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.main',
    requires: [
		'Accounts.model.Customer', 
		'Accounts.model.Item',
		'Accounts.model.SalePurchase',
		'Accounts.model.Payment'
	],
    data: {
        name: 'Accounts'
    },
    stores: {
        customerStore: {
            model: 'Accounts.model.Customer',
			autoLoad : {
				start : 0,
				limit : 5
			}
        },
		itemStore: {
			model: 'Accounts.model.Item',
			autoLoad : {
				start : 0,
				limit : 5
			}
		},
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
		}
    }
});
