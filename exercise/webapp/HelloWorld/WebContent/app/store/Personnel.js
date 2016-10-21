Ext.define('MyClassic.store.Personnel', {
    extend: 'Ext.data.Store',
    alias: 'store.personnel',
    model : 'MyClassic.model.Account',
	pageSize: 5,
	autoLoad : {start : 0, limit : 5}
});
