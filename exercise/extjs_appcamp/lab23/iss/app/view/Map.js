Ext.define('ISS.view.Map', {
    extend: 'Ext.Map',
    xtype: 'issmap',
    mapOptions: {
        zoom: 3
    },

    setCoordinate: function(coordinate) {

        // "coordinate" is the current location of the ISS.

        // All the local vars are defined at the top of the method. Why? 
        // First, understand that a JavaScript local variable is scoped 
        // to the entire method. So no matter where you define a variable, 
        // it's as if you had defined them at the top. By why actually 
        // code them at the top? Because the "var" in a variable statement 
        // cannot be minimized, so if you're really really concerned about 
        // the minimized size of your application, then coding the variables as a 
        // single variable statement means saving three (yes 3) bytes per 
        // variable in the minimized code.
        var me = this,
            ll,
            polylineOptions,
            points,
            count,
            marker,
            i,
            map = me.getMap();

        // Bail out if no coordinate was passed.
        if (!coordinate) {
            return;
        }

        // This use of the logical "or" is a JavaScript idiom -- if the left 
        // side of the expression is "truthy" (and has a value), then use that 
        // value and do not evaluate the right side of the expression. If the 
        // left side is "falsey" (like "undefined"), then use the right side. 
        // In other words, the code acts like a lazy initializer, initializing 
        // the property to an empty array, or using the previously-created array. 
        // Over time, as pushCoordinate() is run, the array of coordinates will 
        // grow, showing the path of the ISS.
        me.coordinates = me.coordinates || [];

        // "push()" is an array method, that, uh, pushes a value onto the array.
        me.coordinates.push(coordinate);

        // Google uses a LatLng object to wrap up a latitude and longitude.
        ll = new google.maps.LatLng(coordinate.latitude, coordinate.longitude);
        me.setMapCenter(ll);

        points = [];
        count = me.coordinates.length;
        // Populate an array of LatLng objects, one for each previous ISS location.
        for (i = 0; i < me.coordinates.length; i++) {
            points.push(new google.maps.LatLng(me.coordinates[i].latitude, me.coordinates[i].longitude));
        }
        if (me.polyline) {
            // Garbage collect the old set of line coordinates.
            me.polyline.setMap(null); 
        }
        // Redraw the line from all ISS coordinates.
        me.polyline = new google.maps.Polyline({
            path: points,
            geodesic: true,
            strokeColor: '#0000FF',
            strokeOpacity: 0.1,
            strokeWeight: 4,
            map: map
        });

        // Lazily create the marker using a png in the resources folder.
        me.marker = me.marker || new google.maps.Marker({
            map: map,
            icon: {
                url: 'resources/iss.png',
                anchor: new google.maps.Point(32, 32)
            }
        });
        me.marker.setPosition(ll);

        // This comment doesn't serve any purpose.
    }
});

// Neither does this one.  ;-)

