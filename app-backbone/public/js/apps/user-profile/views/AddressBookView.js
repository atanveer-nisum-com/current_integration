define(function (require) {
    var Handlebars = require('Handlebars');
    var Validation = require('Validation');
    var _ = require('Underscore');
    var $ = require('jQuery');
    var payform = require("payform");


    /**
     * callback of backbone validation
     * this icalled when o model is valid or invalid
     */

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
    /**
     * main user profile view
     */
    var AddressBook = Backbone.View.extend({
        template: Handlebars.compile(require('text!./../templates/AddressBookView.html')),

        events: {
            'click #submit': 'submit',
            'click #remove': 'removeAddress',
            'click #edit': 'editAddress',
            'click #save': 'saveAddress',
            'change #countryBean': 'countryWatcher',
        },

        countryWatcher: function (e) {
            var value = e.currentTarget.value;
            if (value != 230) {
                $('#stateBean').hide();
            } else {
                $('#stateBean').show();
            }
            console.log(value)
        },
        saveAddress: function (e) {
            e.preventDefault();
            var formData = $('form').serializeObject();
            var countryBean = {id: parseInt(formData.countryBean)};
            var stateBean = {id: parseInt(formData.stateBean)};
            delete formData.countryBean;
            delete formData.stateBean;
            formData.countryBean = countryBean;
            formData.stateBean = stateBean;
            if (formData.isDefault == true) {
                formData.isDefault = 1;
            } else {
                formData.isDefault = 0;
            }

            if (formData.countryBean.id != '230') {
                formData.stateBean = null;
            }

            this.model.set(formData);


            if (this.model.isValid(true)) {
                var address = this.model.toJSON();
                var defaults = this.model.defaults;
                var newObject = {}

                for (var key in defaults) {
                    newObject[key] = address[key];
                }

                newObject.addressType = address.addressType;
                this.model.fetch({
                    type: 'PUT',
                    contentType: 'application/json',
                    headers: {
                        'auth-token': localStorage.getItem('token')
                    },
                    url: this.model.urlRoot + address.id,
                    data: JSON.stringify(newObject),
                    success: function (data, response) {

                        this.model.set('content', response.content);
                        this.model.set('editMode', false);

                        this.displayAddresses({
                            addresses: this.sort(this.model.get('content')),
                            countryModel: this.countries,
                            stateModel: this.states,
                        });

                    }.bind(this),
                    error: function () {
                        this.model.set('editMode', false);
                        this.displayAddresses({
                            addresses: this.sort(this.model.get('content')),
                            countryModel: this.countries,
                            stateModel: this.states,
                        });
                    }
                });
            }
        },
        editAddress: function (e) {
            e.preventDefault();
            el = e.currentTarget;
            var addressId = $(el).closest('.addressId').attr('id');
            var addresses = this.model.get('content');

            var selectedAddress = addresses.filter(function (address, index) {
                if (address.id == addressId) {
                    return address;
                }
            })

            if (this.model.get('editMode') == true && !confirm('Your changes will discard') && selectedAddress) {
                return;
            } else {
                this.model.set('editMode', true);
                this.model.set(selectedAddress[0]);
                this.displayAddresses({
                    address: this.model.toJSON(),
                    addresses: this.sort(this.model.get('content')),
                    countryModel: this.countries,
                    stateModel: this.states,
                })

                this.$el.find('#countryBean').val(selectedAddress[0].countryBean.id);
                if (selectedAddress[0].countryBean.id == 230) {
                    this.$el.find('#stateBean').val(selectedAddress[0].stateBean.id);
                    this.$el.find('#stateBean').show();
                } else {
                    this.$el.find('#stateBean').hide();
                }
            }

        },
        removeAddress: function (e) {
            e.preventDefault();
            el = e.currentTarget;

            var addressId = $(el).closest('.addressId').attr('id');

            this.model.fetch({
                type: 'DELETE',
                contentType: 'application/json',
                headers: {
                    'auth-token': localStorage.getItem('token')
                },
                url: this.model.urlRoot + addressId,

                success: function (data, response) {

                    this.model.set('content', response.content)
                    this.displayAddresses({
                        addresses: this.sort(this.model.get('content')),
                        countryModel: this.countries,
                        stateModel: this.states
                    });

                }.bind(this)
            });


        },
        submit: function (e) {
            e.preventDefault();
            var formData = $('form').serializeObject();
            var countryBean = {id: parseInt(formData.countryBean)};
            var stateBean = {id: parseInt(formData.stateBean)};
            delete formData.countryBean;
            delete formData.stateBean;
            formData.countryBean = countryBean;
            formData.stateBean = stateBean;

            formData.addressType = 1;
            if (formData.isDefault == true) {
                formData.isDefault = 1;
            } else {
                formData.isDefault = 0;
            }

            if (formData.countryBean.id != '230') {
                formData.stateBean = null;
            }

            this.model.set(formData);
            if (this.model.isValid(true)) {

                this.model.fetch({
                    type: 'POST',
                    url: this.model.urlRoot,
                    contentType: 'application/json',
                    headers: {
                        'auth-token': localStorage.getItem('token')
                    },
                    data: JSON.stringify(formData),
                    success: function (data, response) {


                        this.model.set('content', response.content)
                        this.displayAddresses({
                            addresses: this.sort(this.model.get('content')),
                            countryModel: this.countries,
                            stateModel: this.states
                        });

                    }.bind(this)
                });

            } else {
                // console.log(Backbone.Validation)
            }

        },
        initialize: function (options) {
            this.options = options;
            Backbone.Validation.bind(this);


            this.model.fetch({
                type: 'GET',
                contentType: 'application/json',
                headers: {
                    'auth-token': localStorage.getItem('token')
                },
                success: function (model, response) {

                    this.collection.countryCollection.fetch({
                        type: 'GET',
                        contentType: 'application/json',
                        success: function (data) {

                            var countryModel = data.toJSON();
                            this.collection.stateCollection.fetch({
                                type: 'GET',
                                contentType: 'application/json',
                                success: function (data) {

                                    var stateModel = data.toJSON();
                                    this.countries = countryModel;
                                    this.states = stateModel;

                                    this.$el.empty();
                                    this.displayAddresses({
                                        addresses: this.sort(this.model.get('content')),
                                        countryModel: countryModel,
                                        stateModel: stateModel
                                    });

                                }.bind(this)
                            });
                        }.bind(this)
                    });


                }.bind(this),
                error: function () {

                }.bind(this)
            });


            _.bindAll(this, "submit");
        },

        /**
         * Renders view of State selecter, country selecter and other profile
         */
        sort: function (array) {

            var sortedArray = array.sort(function (a, b) {
                return b.isDefault - a.isDefault;
            });

            return sortedArray;
        },
        displayAddresses: function (data) {
            this.$el.html(this.template(data));
        },
        render: function () {
            this.$el.html(this.template());


            return this;
        },

        remove: function () {
            Backbone.Validation.unbind(this);
            return Backbone.View.prototype.remove.apply(this, arguments);
        }

    });

    return AddressBook;
});
