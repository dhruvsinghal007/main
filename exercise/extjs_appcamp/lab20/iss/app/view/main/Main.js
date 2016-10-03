Ext.define('ISS.view.main.Main', {
    extend: 'Ext.tab.Panel',
    xtype: 'main',
    requires: ['ISS.view.Map', 
				'ISS.view.main.MainController', 
				'ISS.view.main.MainModel',
				'ISS.view.Passes'],

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
        xtype: 'isspasses',
        title: 'Passes',
        iconCls: 'x-fa fa-list-ul',
        bind: {
            store: '{passes}'
        }

    }, {
        xtype: 'container',
        html: 'Astronauts go here',

        title: 'Astronauts',
        iconCls: 'x-fa fa-users'

    }]
});
