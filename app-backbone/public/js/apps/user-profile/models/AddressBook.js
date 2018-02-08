define(function (require) {
    var Backbone = require('Backbone');
    var common = require('Common');
    var payform = require("payform");


    var AddressBook = Backbone.Model.extend({
        defaults: {
            addressLine1: '',
            addressLine2: '',
            countryBean: {
                id: '',
                name: ''
            },
            stateBean: {
                id: '',
                name: ''
            },
            city: '',
            zipcode: '',
            phoneNumber: '',
            isDefault: 0
        },

        addresses: [
            {
                "id": 2,
                "addressLine1": "test 1",
                "addressLine2": "test 2",
                "addressType": 1,
                "city": "Los Angeles",
                "phoneNumber": "03031234567",
                "zipcode": "90002",
                "stateBean": {
                    "id": 5,
                    "name": "California"
                },
                "countryBean": {
                    "id": 230,
                    "name": "United States"
                },
                "isDefault": 0
            },
            {
                "id": 8,
                "addressLine1": "test 3",
                "addressLine2": "test 4",
                "addressType": 1,
                "city": "Los Angeles",
                "phoneNumber": "03031234567",
                "zipcode": "90002",
                "stateBean": {
                    "id": 5,
                    "name": "California"
                },
                "countryBean": {
                    "id": 230,
                    "name": "United States"
                },
                "isDefault": 1
            }, {
                "id": 2,
                "addressLine1": "test 5",
                "addressLine2": "test 6",
                "addressType": 1,
                "city": "Los Angeles",
                "phoneNumber": "03031234567",
                "zipcode": "90002",
                "stateBean": {
                    "id": 5,
                    "name": "California"
                },
                "countryBean": {
                    "id": 230,
                    "name": "United States"
                },
                "isDefault": 0
            }

        ],
        validation: {
            addressLine1: {
                required: true,
            },
            addressLine2: {
                required: true,
            },

            countryBean: {
                required: true,
            },
            stateBean: function (value) {
                if (!value && this.get('country') == "230") {
                    return 'kindly provide a state';
                }
            },
            city: {
                required: true
            },
            zipcode: {
                required: true
            },
            phoneNumber: {
                required: true,
                pattern: 'number'
            }
        },


        urlRoot: common.V1_USERS + 'addresses/',

    })
    return AddressBook;
});

