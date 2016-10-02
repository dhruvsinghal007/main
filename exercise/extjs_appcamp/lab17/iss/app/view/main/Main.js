Ext.define('ISS.view.main.Main', {
    extend: 'Ext.tab.Panel',
    xtype: 'main',
    requires: ['ISS.view.Map', 'ISS.view.main.MainController', 'ISS.view.main.MainModel'],

    controller: 'main-main', // Create the controller instance
    viewModel: 'main-main', // Create the view model instance

    tabBarPosition: 'bottom',
    items: [{
         xtype: 'issmap',
		title: 'Map',
		iconCls: 'x-fa fa-crosshairs',
		bind: {
			coordinate: '{coordinate}'
		}
    }, {
        xtype: 'container',
        html: 'Passes goes here',

        title: 'Passes',
        iconCls: 'x-fa fa-list-ul'

    }, {
        xtype: 'container',
        html: 'Astronauts go here',

        title: 'Astronauts',
        iconCls: 'x-fa fa-users'

    }]
});
