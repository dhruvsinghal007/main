Ext.define('Accounts.store.AccountStore', {
    extend: 'Ext.data.Store',
    alias: 'store.accountStore',
	model : 'Accounts.model.Account',
	autoLoad : true
});
