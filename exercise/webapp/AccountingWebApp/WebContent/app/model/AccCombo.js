Ext.define('Accounts.model.AccCombo', {
    extend : 'Ext.data.Model',
	idProperty : 'accId',
	fields : [
		{ name : 'accId', type : 'int' },
		{ name : 'name', type : 'string', allowNull : true },
		{ name : 'type', type : 'string', allowNull : true }
	],
	
	proxy: {
        type: 'ajax',
        url: '/accounts/viewAllAccounts'
    }
	
});