define(function (require) {
    var Backbone = require('Backbone');
    var common = require('Common');
    var payform = require("payform");


    var OrderHistory = Backbone.Model.extend({
        url: common.V1_USERS + 'profile/',
        defaults: {
            orders: [{
                id: 222,
                items: [{
                    id: 111,
                    name: 'Colorblock Scuba',
                    price: 400,
                    quantity: 1,
                    total: 400
                }, {
                    id: 000,
                    name: 'Studded quilted smooth and glossed textured leather gloves',
                    price: 200,
                    quantity: 3,
                    total: 600
                }],
                total: 1000,
                status: {
                    code: 3,
                    message: 'Done'
                },
                shipping: {
                    addressLine1: 'Address Line 1',
                    addressLine2: 'Address Line 2',
                    country: 'USA',
                    state: 'California'
                }
            }, {
                id: 333,
                items: [{
                    id: 555,
                    name: 'Colorblock Scuba',
                    price: 400,
                    quantity: 2,
                    total: 800
                }],
                total: 800,
                status: {
                    code: 1,
                    message: 'In progess'
                },
                shipping: {
                    addressLine1: 'Address Line 3',
                    addressLine2: 'Address Line 4',
                    country: 'USA',
                    state: 'Amazon'
                }
            }]
        },


    })
    return OrderHistory;
});

