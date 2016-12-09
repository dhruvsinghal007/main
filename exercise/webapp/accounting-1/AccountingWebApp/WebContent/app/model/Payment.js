Ext.define('Accounts.model.Payment', {
    extend : 'Ext.data.Model',
	idProperty : 'trId',
	fields : [
		{ name : 'trId', type : 'int' },
		{ name : 'SPId', type : 'int', allowNull : true },
		{ name : 'custId', type : 'int', allowNull : true },
		{ name : 'mode', type : 'string', allowNull : true },
		{ name : 'amount', type : 'int', allowNull : true }
	],
	
	proxy: {
        type: 'ajax',
        url: '/AccountingWebApp/accounts/viewAllPayments',
        reader: {
			rootProperty: 'data',
			totalProperty : 'total'
        }
    }
	
});