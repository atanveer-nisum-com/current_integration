define(function (require) {
    var Handlebars = require('Handlebars');
    var Validation = require('Validation');

    _.extend(Backbone.Validation.callbacks, {
        valid: function (view, attr, selector) {
            var $el = view.$('[name=' + attr + ']'),
                $group = $el.closest('.form-group');

            $group.removeClass('has-error');
            $group.find('.text-danger').html('').addClass('hidden');
        },
        invalid: function (view, attr, error, selector) {

            var $el = view.$('[name=' + attr + ']'),
                $group = $el.closest('.form-group');

            $group.addClass('has-error');
            $group.find('.text-danger').html(error).removeClass('hidden');

        }
    });

    var forgetPassword = Backbone.View.extend({
        template: Handlebars.compile(require('text!./../templates/forgetPassword.html')),
        className: "container",
        events: {
            'click #submit': function (e) {
                e.preventDefault();
                this.onClick();
            }
        },

        initialize: function () {
            // This hooks up the validation
            Backbone.Validation.bind(this);
        },

        onClick: function () {
            var data = this.$('form').serializeObject();
            this.model.set(data);
            var formData = null;

            formData = {
                'email': data.email,
            };

            // Check if the model is valid before saving

            if (this.model.isValid(true)) {
                this.doLogin(formData);
            }
        },
        
        doLogin: function (formData) {
            this.model.save(formData,
                {
                    validate: false,
                    success: function (response) {
                        alert("Reset link has been sent to your email")
                        window.Router.navigate('/', {trigger: true});
                    },
                    error: function (response) {
                        console.log("error");
                        console.log(response);
                        alert("Something went wrong please try again");
                    }
                });

        },

        remove: function () {
            // Remove the validation binding

            // Backbone.Validation.unbind(this);
            return Backbone.View.prototype.remove.apply(this, arguments);
        },

        render: function () {
            this.$el.html(this.template);
            return this;
        }
    });
    return forgetPassword;
});