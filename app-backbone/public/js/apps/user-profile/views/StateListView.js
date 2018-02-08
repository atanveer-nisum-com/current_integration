define(function (require) {
    var Backbone = require('Backbone');
    var Handlebars = require('Handlebars');
    /**
     * View of state drop down
     */
    var StateListView = Backbone.View.extend({
        template: Handlebars.compile(require('text!./../templates/StateList.html')),
        /**
         * sBean is used to set already selected state
         */
        sBean: '',

        render: function () {
            var response = this.collection.fetch({
                type: 'GET',
                contentType: 'application/json',
                success: function (data) {
                    this.$el.empty();
                    this.$el.html(this.template({stateList: data.toJSON(), stateBean: this.sBean}));
                }.bind(this)
            });
            return this;
        }
    });
    return StateListView;
});
