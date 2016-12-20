Ext.define('Accounts.model.Loan', {
    extend : 'Ext.data.Model',
	idProperty : 'loanId',
	fields : [
		{ name : 'loanId', type : 'int' },
		{ name : 'custId', type : 'int', allowNull : true },
		{ name : 'amount', type : 'int', allowNull : true },
		{ name : 'mode', type : 'string', allowNull : true },
		{ name : 'date', type : 'date', dateFormat : 'dmY', allowNull : true }
	],
	
	proxy: {
        type: 'ajax',
        url: '/AccountingWebApp/accounts/viewAllLoans',
        reader: {
			rootProperty: 'data',
			totalProperty : 'total'
        }
    }
	
});