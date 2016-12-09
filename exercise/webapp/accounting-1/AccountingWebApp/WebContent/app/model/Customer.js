Ext.define('Accounts.model.Customer', {
    extend : 'Ext.data.Model',
	idProperty : 'custId',
	fields : [
		{ name : 'custId', type : 'int' },
		{ name : 'name', type : 'string', allowNull : false },
		{ name : 'address', type : 'string', allowNull : true },
		{ name : 'contact', type : 'int', allowNull : true }
	],
	
	proxy: {
        type: 'ajax',
        url: '/AccountingWebApp/accounts/viewAllCustomers',
        reader: {
			rootProperty: 'data',
			totalProperty : 'total'
        }
    }
	
});