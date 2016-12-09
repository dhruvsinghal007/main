Ext.define('Accounts.store.CustomerStore', {
    extend: 'Ext.data.Store',
    alias: 'store.customerStore',
    model : 'Accounts.model.Customer',
	pageSize: 5,
	autoLoad : {start : 0, limit : 5}
});
