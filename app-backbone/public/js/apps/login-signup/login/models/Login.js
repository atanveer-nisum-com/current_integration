define(function(require) {
	var Backbone = require('Backbone');
	var common = require('Common');

	var Login = Backbone.Model.extend({

        url : common.V1_USERS + 'login/',
        
        defaults: {
            emailAddress: '',
            password: ''
        },
   
    validation: {
        emailAddress: {
            required: true,
            pattern: 'email',
        },
        password: {
            required: true,
            minLength: 8
        }
    }

    });
	return Login;
});

