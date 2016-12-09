Ext.define('Accounts.model.Item', {
    extend : 'Ext.data.Model',
	idProperty : 'itemId',
	fields : [
		{ name : 'itemId', type : 'int' },
		{ name : 'name', type : 'string', allowNull : false },
		{ name : 'netQuantity', type : 'int', allowNull : true },
		{ name : 'cost', type : 'int', allowNull : true }
	],
	
	proxy: {
        type: 'ajax',
        url: '/accounts/viewAllItems',
        reader: {
			rootProperty: 'data',
			totalProperty : 'total'
        }
    }
	
});