Ext.define('ISS.view.Astronauts', {
    extend: 'Ext.dataview.List',
    xtype: 'issastronauts',
    cls: 'astronauts',
    itemCls: 'astronaut',
    itemTpl: [
        '<tpl if="thumbnail"><img src="{thumbnail}" class="thumbnail"></tpl>',
        '<p class="name">{name}</p>'
    ]
});