define(function (require) {
    var Backbone = require('Backbone');
    var common = require('Common');

    var forgetPassword = Backbone.Model.extend({

        url: common.V1_USERS + 'forgetpassword/',

        defaults: {
            email: '',
        },

        validation: {
            email: {
                required: true,
                pattern: 'email',
            }
        }

    });
    return forgetPassword;
});

