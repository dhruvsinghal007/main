Ext.define('Accounts.view.main.MainView', {
    extend: 'Ext.form.Panel',
    xtype: 'mainView',

    title: 'Main',
    width: 500,
    layout: 'form',
    
    items: [{
        xtype: 'fieldset',
        layout: 'anchor',
        items: [{
            xtype: 'component',
            anchor: '100%'
        }, {
            xtype: 'displayfield',
            fieldLabel: 'Selected Item',
            bind: '{items.value}'
        }, {
            xtype: 'combobox',
            reference: 'items',
            publishes: 'value',
            fieldLabel: 'Select Item',
            displayField: 'name',
            anchor: '-15',
            store: {
                type: 'itemStore',
				autoLoad : true
            },
            minChars: 0,
            queryMode: 'local',
            typeAhead: true
        }]
    }]
});