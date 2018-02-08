define(function (require) {
  var Backbone = require('Backbone');
  var Handlebars = require('Handlebars');
  var common = require('Common');
  var validation = require('Validation');
  var CountryCollection = require('../collections/CountryCollection');


  var Profile = Backbone.Model.extend({

    validation: {
      firstName: {
        required: true,
        msg: 'please provide first name'
      },
      lastName: {
        required: true,
        msg: 'please provide last name'
      },
      emailAddress: {
        required: true,
        pattern: 'email',
        msg: 'please provide a valid email address'
      },
      country: {
        required: true,
        msg: 'please select a country'
      },
      addressLine1: {
        required: true,
        msg: 'please provide addressline1'
      },
      city: {
        required: true,
        pattern: /^[a-zA-Z]+(?:[\s-][a-zA-Z]+)*$/,
        msg: 'please enter valid city'
      },
      zipcode: {
        required: true,
        pattern: 'digits',
        msg: 'provide a valid zipcode'
      },
      phoneNumber: {
        required: true,
        pattern: 'number',
        msg: 'Provide a valid number',
        minLength: 11,
        maxLength: 20
      },
      confirmPassword: {
        equalTo: 'newPassword',
        msg: 'pass doesnt match'
      },
      currentPassword: function (value) {
        if (!value && this.get('newPassword')) {
          return 'please provide current password';
        }
      },

      newPassword: function (value) {
        if (!value && this.get('currentPassword')) {
          return 'provide new password';
        }
      },
      state: function(value){
        if(!value && this.get('country')=="230"){
          return 'kindly provide a state';
        }
      }
      
    },


    url: common.V1_USERS + 'profile/',

  })
  return Profile;
});

