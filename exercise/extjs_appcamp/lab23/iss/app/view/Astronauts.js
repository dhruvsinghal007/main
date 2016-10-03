Ext.define('ISS.view.Astronauts', {
    extend: 'Ext.dataview.List',
    xtype: 'issastronauts',
    requires: [],
    itemTpl: [
		'<tpl if="thumbnail"><img src="{thumbnail}"></img></tpl>',
		'{name}'
	]
});