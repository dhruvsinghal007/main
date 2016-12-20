Ext.define('Accounts.store.LoanStore', {
    extend: 'Ext.data.Store',
    alias: 'store.loanStore',
	//storeId: 'loanStore',
    model : 'Accounts.model.Loan',
	pageSize: 5,
	autoLoad : {start : 0, limit : 5}
});
