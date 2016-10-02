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
