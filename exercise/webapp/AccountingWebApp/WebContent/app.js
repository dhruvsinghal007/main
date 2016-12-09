Ext.application({
    extend: 'Accounts.Application',
	requires: [
        'Accounts.view.main.Main'
    ],
	name: 'Accounts',
    mainView: 'Accounts.view.main.Main'
});
