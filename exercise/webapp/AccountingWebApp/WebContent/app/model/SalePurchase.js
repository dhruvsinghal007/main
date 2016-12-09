Ext.define('Accounts.model.SalePurchase', {
    extend : 'Ext.data.Model',
	idProperty : 'SPId',
	fields : [
		{ name : 'SPId', type : 'int' },
		{ name : 'itemId', type : 'int', allowNull : true },
		{ name : 'quantity', type : 'int', allowNull : true },
		{ name : 'mode', type : 'string', allowNull : true },
		{ name : 'date', type : 'date', dateFormat : 'dmY', allowNull : true }
	],
	
	proxy: {
        type: 'ajax',
        url: '/accounts/viewAllSalesPurchases',
        reader: {
			rootProperty: 'data',
			totalProperty : 'total'
        }
    }
	
});