define(function (require) {
    var Backbone = require('Backbone');
    var common = require('Common');

    var resetPassword = Backbone.Model.extend({

        urlRoot: common.V1_USERS,
        initialize: function (params) {
            this.fetch({
                type: 'GET',
                contentType: 'application/json',
                url: this.urlRoot + 'resetpassword/' + params.resetToken,
                success: function (res) {
                    var data = res.toJSON();

                    if (data.status != "success") {
                        alert(data.message);
                        window.Router.navigate('/', {trigger: true});
                    }
                },
                error: function (res) {
                    alert('Some Thing went wrong please try again');
                    window.Router.navigate('/', {trigger: true});
                }
            });
        },
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
    return resetPassword;
});

