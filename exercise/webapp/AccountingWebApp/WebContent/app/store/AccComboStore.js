Ext.define('Accounts.store.AccComboStore', {
    extend: 'Ext.data.Store',
    alias: 'store.accComboStore',
	model : 'Accounts.model.AccCombo',
	autoLoad : true,
	autoSync : true
});
