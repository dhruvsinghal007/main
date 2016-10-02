Ext.define('ISS.view.main.MainController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.main-main',

    initViewModel: function(vm) {
        var me = this;
        me.determineIssCoordinate(me);
        // Call the browser's setInterval() method to run
        // determineIssCoordinate() every three seconds.
        // The 3rd param is passed to the method.
        window.setInterval(me.determineIssCoordinate, 3000, me); // 3rd param IE 10+

        me.determineUserLocation();

    },
    determineUserLocation: function() {
        // If you're above or below 51.6 degrees latitude, then you'll can delete
        // everything in this method, except for a call to loadPasses(), passing in 
        // a hard-coded location. Examples:
        // this.loadPasses({latitude:44.4974569,longitude:-92.2629124}); // Stockholm, Wisconsin
        // this.loadPasses({latitude:26.3490455,longitude:-98.1680219}); // Edinburg, Texas
        // this.loadPasses({latitude:46.734897,longitude:-117.000883}); // Moscow, Idaho

        var me = this;
        var geo = Ext.create('Ext.util.Geolocation', {
            autoUpdate: false
        });
        geo.updateLocation(function(geo) {
            if (geo) {
                me.loadPasses({
                    latitude: geo.getLatitude(),
                    longitude: geo.getLongitude()
                });
            }
        });
    },
    loadPasses: function(coordinate) {
        var passes = this.getViewModel().getStore('passes');
        passes.load({
            params: {
                lat: coordinate.latitude,
                lon: coordinate.longitude
            }
        });
    },
    determineIssCoordinate: function(controller) {
        var me = controller || this;
        var vm = me.getViewModel();
        Ext.data.JsonP.request({
            url: 'http://api.open-notify.org/iss-now.json',
            success: function(response) {
                var p = response.iss_position;
                vm.set('coordinate', {
                    latitude: p.latitude,
                    longitude: p.longitude
                });
            }
        });
    }
});
