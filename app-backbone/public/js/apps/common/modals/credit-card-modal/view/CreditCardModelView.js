define(function (require) {
    var Handlebars = require('Handlebars');
    var Backbone = require('Backbone');
    var Validation = require('Validation');
    _.extend(Backbone.Validation.callbacks, {
        valid: function (view, attr) {

            var $el = $('.modal').find('[name=' + attr + ']'),
                $group = $el.closest('.form-group');

            $group.removeClass('has-error');
            $group.find('.text-danger').html('').addClass('hidden');
        },
        invalid: function (view, attr, error, selector) {

            var $el = $('.modal').find('[name=' + attr + ']'),
                $group = $el.closest('.form-group');

            $group.addClass('has-error');
            $group.find('.text-danger').html(error).removeClass('hidden');

        }
    });


    var CreditCardModelView = Backbone.View.extend({
        template: Handlebars.compile(require('text!./../templates/CreditCardModal.html')),
        className: 'modal-view',
        events: {
            'click #close': 'hide',
        },

        initialize: function (options, model) {
            this.options = options;
            this.model = model;
            _.bindAll(this, 'render', 'show', 'hide', 'remove');
            this.render()
        },
        render: function () {

            this.$el.html(this.template(this.options));

            this.$el.appendTo('body');
            this.$modal = this.$('.modal');


            this.$modal.on('hidden.bs.modal', _.bind(function () {
                this.close();
            }, this));


        },
        show: function () {
            this.$modal.modal('show');
            $('body').append('<div class="modal-backdrop in"></div>');
        },
        hide: function () {
            this.$modal.modal('hide');
            $('.modal-backdrop').remove()
        },
        close: function () {
            if (this.model) {
                this.model.set(this.model.defaults);
            }
            $('.modal-backdrop').remove()
            //unbind events
            this.unbind();
            //Remove html from page
            this.$modal.modal('hide');
        }
    });
    return CreditCardModelView;
});