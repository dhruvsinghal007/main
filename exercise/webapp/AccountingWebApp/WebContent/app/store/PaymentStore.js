Ext.define('Accounts.store.PaymentStore', {
    extend: 'Ext.data.Store',
    alias: 'store.paymentStore',
    model : 'Accounts.model.Payment',
	pageSize: 5,
	autoLoad : {start : 0, limit : 5}
});
