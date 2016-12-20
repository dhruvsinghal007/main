Ext.define('Accounts.store.ItemStore', {
    extend: 'Ext.data.Store',
    alias: 'store.itemStore',
	//storeId: 'itemStore',
    model : 'Accounts.model.Item',
	pageSize: 5,
	autoLoad : {start : 0, limit : 5}
});
