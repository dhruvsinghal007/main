Ext.define('ISS.model.Astronaut', {
    extend: 'Ext.data.Model',
    proxy: {
        type: 'jsonp',
        url: 'http://api.open-notify.org/astros.json',
        reader: {
            rootProperty: 'people'
        }
    },
    fields: [{
        name: 'thumbnail'
    }]
});
