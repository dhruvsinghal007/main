Ext.define('Accounts.store.AccountStore', {
    extend: 'Ext.data.Store',
    alias: 'store.accountStore',
	model : 'Accounts.model.Account',
	pageSize: 5,
	autoLoad : {start : 0, limit : 5}
});
