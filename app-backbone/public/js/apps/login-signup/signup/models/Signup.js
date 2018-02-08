define(function(require) {
	var Backbone = require('Backbone');
	var common = require('Common');

	var Signup = Backbone.Model.extend({
        url : common.V1_USERS + 'signup/',
        
        // defaults: {
        //     firstName: '',
        //     lastName: '',
        //     emailAddress: '',
        //     password: '',
        //     //confirmPassword: '',
        //     addresses : [{
        //         addressLine1: '',
        //         addressLine2: '',
        //         addresstype : 1,
        //         city: '',
        //         phoneNumber: '',
        //         zipcode: '',            
        //         stateBean:{
        //             id : ''
        //         },
        //         countryBean: {
        //             id : ''
        //         } 
        //     }],
        // },
        validation: {
            firstName: {
                required: true
            },
            lastName: {
                required: true
            },
            emailAddress: {
                required: true,
                pattern: 'email',
            },
            password: {
                required: true,
                minLength: 8
            },
            confirmPassword: {
                equalTo: 'password',
                msg: 'The passwords does not match'
            },
            addressLine1: {
                required: true
            },
            addressLine2: {
            },
            country: {
                required: true                
            },
            state: {
                required: false
            },
            city: {
                required: true,
                pattern: /^[a-zA-Z]+(?:[\s-][a-zA-Z]+)*$/                
            },
            zipcode: {
                required: true,
                minLength: 4,            
                pattern: 'digits'
            },
            phoneNumber: {
                required: true, 
                pattern: 'number'              
            }
        }
    });
	return Signup;
});
