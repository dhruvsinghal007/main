Ext.define('Accounts.store.SalePurchaseStore', {
    extend: 'Ext.data.Store',
    alias: 'store.salePurchaseStore',
	//storeId: 'salePurchaseStore',
    model : 'Accounts.model.SalePurchase',
	pageSize: 5,
	autoLoad : {start : 0, limit : 5}
});
