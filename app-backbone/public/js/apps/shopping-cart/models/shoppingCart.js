define(function (require) {
    var Backbone = require('Backbone');
    var common = require('Common');

    var shoppingCart = Backbone.Model.extend({
        urlRoot: common.V1_USERS,

        defaults: {
            email: '',
        },

        validation: {
            newPassword: {
                required: true,
                minLength: 8
            },
            confirmPassword: {
                required: true,
                minLength: 8
            }
        }

    });
    return shoppingCart;
});

