define(function (require) {
    var Backbone = require('Backbone');
    var common = require('Common');
    var payform = require("payform");


    var Payment = Backbone.Model.extend({

        defaults: {
            cardNumber: null,
            isDefault: 0,
            paymentTypeId: 2/*,
            cards: [/!*{
                id: 2244,
                cardNumber: '4444444444444444',
                nameOnCard: 'M Ahsan Zaman',
                cardExpiry: '03/21',
                cardCvc: '4444',
                cardType: 'visa',
            }, {
                id: 3344,
                cardNumber: '5544444444444444',
                nameOnCard: 'M Arif',
                cardExpiry: '03/21',
                cardCvc: '5555',
                cardType: 'master',
            }*!/]*/
        },
        cards: [],
        validation: {
            cardNumber: {
                required: true,
            },

        },


        url: common.V1_USERS + 'cards/',

    })
    return Payment;
});

