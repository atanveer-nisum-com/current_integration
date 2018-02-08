define(function (require) {
    var Backbone = require('Backbone');
    var Handlebars = require('Handlebars');

    var LoginSignupView = Backbone.View.extend({

        template: Handlebars.compile(require('text!./../templates/LoginSignupView.html')),

        className: 'container',
        initialize: function (options) {
            this.options = options;
        },

        render: function () {
            this.$el.empty();
            var row = "<div class='row'></div>";

            this.$el.html(row);
            this.$('.row').append(this.options.loginView.el);
            this.$('.row').append(this.template);
            this.$('.row').append(this.options.signupView.el);

            return this;
        }
    });

    return LoginSignupView;
});
  