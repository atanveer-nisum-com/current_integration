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

    var resetPassword = Backbone.View.extend({
        template: Handlebars.compile(require('text!../templates/resetPassword.html')),
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
                'password': data.newPassword,
                'confirmPassword': data.confirmPassword,
            };

            // Check if the model is valid before saving

            if (this.model.isValid(true)) {
                if (formData.password != formData.confirmPassword) {
                    alert('Password do not match');
                    alert('Password do not match');
                    return;
                }
                else {
                    this.doReset(formData);
                }
            }
        },
        doReset: function (formData) {
            this.model.save(formData,
                {
                    url: this.model.urlRoot + 'resetpassword',
                    validate: false,
                    success: function (response) {
                        var data = response.toJSON()
                        if (data.status == "success") {
                            alert(data.message);
                        }
                        window.Router.navigate('/loginsignup', {trigger: true});
                    },
                    error: function (response) {
                        console.log("error");
                        console.log(response);
                        alert("incorrect email or password");
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
    return resetPassword;
});