define(function (require) {
    var Handlebars = require('Handlebars');
    var Validation = require('Validation');
    var _ = require('Underscore');
    var $ = require('jQuery');
    var payform = require("payform");
    var newModal = require('../../common/modals/credit-card-modal/view/CreditCardModelView');


    Handlebars.registerHelper("checkDefault", function (value) {
        if (value == 1) {
            return 'checked';
        } else {
            return '';
        }
    });

    Handlebars.registerHelper('select', function (value, options) {
        var $el = $('<select />').html(options.fn(this));
        $el.find('[value="' + value + '"]').attr({'selected': 'selected'});
        return $el.html();
    });

    var Payment = Backbone.View.extend({
        template: Handlebars.compile(require('text!./../templates/PaymentView.html')),

        events: {
            'click #submit': 'addCard',
            'click #openModal': 'openModel',
            /*'keyup input#cardNumber': 'getCardType',*/
            'click #removeCard': 'removeCard',
            'click #editCard': 'editCard',

        },

        editCard: function (e) {
            e.preventDefault();
            var $el = $(e.currentTarget);
            var cardId = $el.closest('tr').attr('id')
            var cards = this.model.get('cards');
            var selectedCard = null;

            cards.forEach(function (card) {
                if (parseInt(card.id) == parseInt(cardId)) {
                    selectedCard = card;
                }
            })

            selectedCard.cardNumber = selectedCard.cardNumber;

            editCardModal = new newModal(selectedCard, this.model);
            this.model.set(selectedCard)
            this.initModel(editCardModal.$el, this.updateCard, 'edit')
            editCardModal.show();
        },

        initModel: function (el, callback, action) {
            var subviewEl = el;
            if (arguments[2] == 'edit') {
                subviewEl.find('input#cardNumber').attr('disabled', 'disabled');
                subviewEl.find('select').attr('disabled', 'disabled');
            }
            subviewEl.find('#submit').click(callback);
            subviewEl.find('input#cardNumber').on('keyup', this.getCardType)
            var cardNumber = $(subviewEl).find('#cardNumber');


            payform.numericInput(cardNumber[0]);
            payform.cardNumberInput(cardNumber[0])

        },
        openModel: function (e) {
            e.preventDefault();
            addCardModal = new newModal(this.model.toJSON(), this.model);
            this.initModel(addCardModal.$el, this.addCard)
            addCardModal.show();
        },
        removeCard: function (e) {
            var $el = $(e.currentTarget);
            var cardId = $el.closest('tr').attr('id');
            var cards = this.model.get('cards');
            var cardIndex = null;
            var that = this;

            cards.forEach(function (card, index) {
                if (parseInt(card.id) == parseInt(cardId)) {
                    cardIndex = index;
                }
            })

            cards.splice(cardIndex, 1);
            this.model.set('cards', cards);


            this.model.fetch({
                type: 'DELETE',
                contentType: 'application/json',
                url: this.model.url + cardId,
                headers: {
                    'auth-token': localStorage.getItem('token')
                },
                success: function (model, response) {
                    console.log(model, response, 'success')

                }, error: function (model, response, a) {
                    console.log(model, response, a, 'error')
                }
            });
            this.displayCards(cards);

        },
        getCardType: function (paymentTypeId) {
            var paymentsTypes = {
                2: "MasterCard",
                3: "VISA",
                4: "American Express"
            }

            return paymentsTypes[paymentTypeId];
        },
        addCard: function (e) {
            e.preventDefault();
            var formData = $('form#addCardForm').serializeObject();

            var newCard = formData;

            var cards = this.model.get('cards') || [];

            if (newCard.isDefault) {
                newCard.isDefault = parseInt(newCard.isDefault)
                cards.forEach(function (card) {
                    if (card.isDefault == 1) {
                        card.isDefault = 0;

                    }
                });

                newCard.isDefault = 1;
            } else {
                newCard.isDefault = 0;
            }

            this.model.set(newCard);

            if (this.model.isValid(true)) {

                newCard.id = Math.floor(1000 + Math.random() * 9000);
                newCard.paymentTypeId = parseInt(newCard.paymentTypeId);


                newCard.cardNumber = parseInt(newCard.cardNumber.replace(/\s/g, ''));

                delete newCard['id'];
                delete newCard['paymentType'];
                this.model.fetch({
                    type: 'POST',
                    contentType: 'application/json',
                    url: this.model.url,
                    headers: {
                        'auth-token': localStorage.getItem('token')
                    },
                    data: JSON.stringify(newCard),
                    success: function (model, card) {

                        card.paymentType.name = this.getCardType(card.paymentType.id)
                        cards.push(card);
                        this.model.set('cards', cards);
                        this.displayCards(cards);
                        addCardModal.close();

                    }.bind(this),
                    error: function (model, response) {
                        // var response = data.toJSON();


                        alert(JSON.parse(response.responseText).errorDescription, 'cxvxc')


                    }.bind(this),
                });


            } else {
                return;
            }
        },
        updateCard: function () {

            var formData = $('form#addCardForm').serializeObject();
            var editedCard = formData;
            var cards = this.model.get('cards');
            var id = this.model.get('id');
            var cardNumber = this.model.get('cardNumber')
            var paymentTypeId = this.model.get('paymentTypeId')


            editedCard.cardNumber = cardNumber;
            editedCard.paymentTypeId = paymentTypeId;

            if (editedCard.isDefault) {
                editedCard.isDefault = 1;
            } else {
                editedCard.isDefault = 0;
            }
            this.model.set(editedCard);
            if (this.model.isValid(true)) {

                this.model.fetch({
                    type: 'PUT',
                    contentType: 'application/json',
                    url: this.model.url + id,
                    headers: {
                        'auth-token': localStorage.getItem('token')
                    },
                    data: JSON.stringify(editedCard),
                    success: function (model, response) {

                        cards.forEach(function (card) {
                            if (response.id == card.id && card.isDefault == 0) {
                                card.isDefault = 1;
                            } else {
                                card.isDefault = 0;
                            }
                        })
                        this.model.set('cards', cards);
                        this.displayCards(this.model.get('cards'));
                        editCardModal.close();

                    }.bind(this),
                    error: function (model, response) {

                        alert(JSON.parse(response.responseText).errorDescription);
                        editCardModal.close();

                    }.bind(this),
                });


            } else {
                return;
            }
        }
        ,
        displayCards: function (data) {

            this.$el.html(this.template(data));

        }
        ,
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
                    // var response = data.toJSON();

                    var newData = response;
                    var cards = newData;


                    this.model.set('cards', cards);
                    this.displayCards(this.model.get('cards'));


                    // this.$el.html(this.template({stateList: data.toJSON(), stateBean: this.sBean}));
                }.bind(this)
            });
            _.bindAll(this, "addCard", "initModel", 'updateCard', 'editCard', 'getCardType');
        }
        ,

        /**
         * Renders view of State selecter, country selecter and other profile
         */

        render: function () {
            this.$el.html(this.template());
            return this;
        }
        ,
        /**
         * invoked when update button is clicked
         * updates users profile
         */

        remove: function () {
            Backbone.Validation.unbind(this);
            return Backbone.View.prototype.remove.apply(this, arguments);
        }

    });

    return Payment;
});
