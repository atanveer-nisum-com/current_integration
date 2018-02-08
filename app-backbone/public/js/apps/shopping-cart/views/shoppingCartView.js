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
        template: Handlebars.compile(require('text!../templates/shoppingCart.html')),
        className: "container",

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