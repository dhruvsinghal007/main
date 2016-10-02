Ext.define('ISS.view.main.Main', {
    extend: 'Ext.tab.Panel',
    xtype: 'main',
    tabBarPosition: 'bottom',
	requires:['ISS.view.Map'],

    items: [
	{
		xtype: 'issmap',
		title: 'Map',
		iconCls: 'x-fa fa-crosshairs'
	},
	{
        xtype: 'component',
        html: 'Map goes here',

        title: 'Map',
        iconCls: 'x-fa fa-crosshairs'

    }, {
        xtype: 'component',
        html: 'Passes goes here',

        title: 'Passes',
        iconCls: 'x-fa fa-list-ul'

    }, {
        xtype: 'component',
        html: 'Astronauts go here',

        title: 'Astronauts',
        iconCls: 'x-fa fa-users'

    }]
});
