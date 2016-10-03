Ext.define('ISS.view.Passes', {
    extend: 'Ext.dataview.List',
    xtype: 'isspasses',
    padding: 4,
    itemTpl: [
        '{[this.todayTomorrow(values)]} at {[Ext.util.Format.date(values.risetime, "G:i")]}, for {[this.minutes(values)]}', {
            todayTomorrow: function(values) {
                var days = Ext.Date.getDayOfYear(values.risetime) - Ext.Date.getDayOfYear(this.today);
                if (days === 0) {
                    return 'Today';
                } else if (days === 1) {
                    return 'Tomorrow';
                } else {
                    return 'In ' + days + 'days';
                }
            },
            minutes: function(values) {
                var minutes = Math.floor(values.duration / 60);
                var s = Ext.util.Format.plural(minutes, 'minute');
                var seconds = (values.duration % 60);
                if (seconds > 0) {
                    s += ' ' + Ext.util.Format.plural(seconds, 'second');
                }
                return s;
            },
            today: new Date()
        }
    ]
});
