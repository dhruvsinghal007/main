Ext.define('Accounts.model.daily.Credit', {
    extend : 'Ext.data.Model',
	fields : [
		{ name : 'accName', type : 'string' },
		{ name : 'description', type : 'string' },
		{ name : 'quantity', type : 'string'},
		{ name : 'amount', type : 'int'}
	]
});