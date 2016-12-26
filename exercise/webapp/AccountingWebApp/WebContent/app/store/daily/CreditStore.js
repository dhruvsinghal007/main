Ext.define('Accounts.store.daily.CreditStore', {
    extend : 'Ext.data.Store',
	requires : ['Accounts.model.daily.Credit'],
	alias : 'store.creditStore',
	model : 'Accounts.model.daily.Credit'
});