Ext.define('MyClassic.model.Account', {
    extend : 'Ext.data.Model',
	idProperty : 'id',
	fields : [
		{ name : 'id', type : 'int' },
		{ name : 'name', type : 'string', allowNull : true },
		{ name : 'desc', type : 'string', allowNull : true },
		{ name : 'town', type : 'string', allowNull : true },
		{ name : 'cell', type : 'int', allowNull : true },
		{ name : 'email', type : 'string', allowNull : true },
		{ name : 'dob', type : 'date', dateFormat : 'dmY', allowNull : true },
		{ name : 'type', type : 'string', allowNull : true },
	],
	
	proxy: {
        type: 'ajax',
        url: '/accounts/viewAll',
        reader: {
			rootProperty: 'data',
			totalProperty : 'total'
        }
    }
	
});