define(function (require) {
    var Handlebars = require('Handlebars');
    var Validation = require('Validation');
    var _ = require('Underscore');
    var $ = require('jQuery');

    var Handlebars = require('Handlebars');

    Handlebars.registerHelper("inc", function (value, options) {
        return parseInt(value) + 1;
    });

    Handlebars.registerHelper("order-status", function (value, options) {
        console.log(value, options)
        if (value == 1) {
            return ('panel-default')
        } else if (value == 3) {
            return ('panel-success')
        }
    });


    var OrderHistory = Backbone.View.extend({
        template: Handlebars.compile(require('text!./../templates/OrderHistoryView.html')),

        events: {
            'click #submit': 'addCard',
        },


        initialize: function (options) {
            this.options = options;

        },

        /**
         * Renders view of State selecter, country selecter and other profile
         */

        render: function () {
            this.$el.html(this.template(this.model.get('orders')));

            return this;
        },

        remove: function () {
            return Backbone.View.prototype.remove.apply(this, arguments);
        }

    });

    return OrderHistory;
});
