Ext.define('Accounts.store.daily.DebitStore', {
    extend : 'Ext.data.Store',
	requires : ['Accounts.model.daily.Debit'],
	alias : 'store.debitStore',
	model : 'Accounts.model.daily.Debit'
});