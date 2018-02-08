define(function (require) {
  var Handlebars = require('Handlebars');
  var Validation = require('Validation');
  var _ = require('Underscore');
  var $ = require('jQuery');

  /**
   * callback of backbone validation
   * this icalled when o model is valid or invalid
   */
  _.extend(Backbone.Validation.callbacks, {
    valid: function (view, attr) {
      var $el = view.$('[name=' + attr + ']'),
        $group = $el.closest('.form-group');
      $group.removeClass('has-error');
      $group.find('.text-danger').html('').addClass('hidden');
    },

    invalid: function (view, attr, error) {
      var $el = view.$('[name=' + attr + ']'),
        $group = $el.closest('.form-group');
      $group.addClass('has-error');
      $group.find('.text-danger').html(error).removeClass('hidden');
    }
  });
  /**
   * main user profile view 
   */
  var UserProfileView = Backbone.View.extend({
    template: Handlebars.compile(require('text!./../templates/UserProfileView.html')),

    events: {
      'click #update': 'mysubmit',
      'change #country': 'addOrRemoveStateSelector',
    },

    initialize: function (options) {
      this.options = options;
      Backbone.Validation.bind(this);
      prevUrl = this.model.url;
    },
    /*
    toggles options to select state
    */
    addOrRemoveStateSelector: function () {
      if ($('#country').val() == "230") {
        $('#stateDiv').removeClass('hidden');
      }
      else {
        $('#stateDiv').addClass('hidden');
      }
    },
    /**
     * Renders view of State selecter, country selecter and other profile
     */

    render: function () {
      //user id is hardcoded becuase login is not done yet
      // this.model.url += "?userid=1";
      var response = this.model.fetch({
        headers: {
          'auth-token': localStorage.getItem('token')
        },
        type: 'GET',
        contentType: 'application/json',

        success: function (data) {
          this.$el.empty();

          this.$el.html(this.template(data.toJSON()));
          var countryBean = data.get('addresses')[0].countryBean;
          this.options.countryListView.cBean = countryBean;
          this.options.countryListView.render();
          this.$('#countryList').append(this.options.countryListView.el);

          var stateBean = data.get('addresses')[0].stateBean;
          this.options.stateListView.sBean = stateBean;
          this.options.stateListView.render();
          this.$('#stateList').append(this.options.stateListView.el);

          if (countryBean.id == "230") {
            $('#stateDiv').removeClass('hidden');
          }
        }.bind(this),
        error : function(model,response){
          if(response.status === 401)
          window.Router.navigate('/loginsignup', {trigger: true});
        }

      }
      );
      return this;
    },
    /**
     * invoked when update button is clicked
     * updates users profile
     */
    mysubmit: function (e) {
      e.preventDefault();
      var formData = this.$('form').serializeObject();
      var state = null;
      if ($('#country').val() == "230") {
        state = { id: formData.state }
        
      }
      console.log(formData.addressLine2);
      if(formData.addressLine2.length<1){
      console.log(formData.addressLine2);
      formData.addressLine2=null;
      }

      var dataToUpdate = {
        id: this.model.get('id'),
        firstName: formData.firstName,
        lastName: formData.lastName,
        emailAddress: formData.emailAddress,
        currentPassword: formData.currentPassword,
        newPassword: formData.newPassword,
        confirmPassword: formData.confirmPassword,
        addresses: [{
          id: this.model.get('addresses')[0].id,
          addressLine1: formData.addressLine1,
          addressLine2: formData.addressLine2,
          addressType: 1,
          phoneNumber: formData.phoneNumber,
          city: formData.city,
          stateBean: state,
          zipcode: formData.zipcode,
          countryBean: {
            id: formData.country,
          }
        }]
      };

      this.model.set(formData);
      if (this.model.isValid(true)) {
        this.model.save(dataToUpdate, {
          headers: {
            'auth-token': localStorage.getItem('token')
          },
          //url: prevUrl + 'update',
          type: 'PUT',
          validate: false,
          success: function (response) {
            alert("Profile updated Successfullly");
          },
          error: function (e) {
            alert("could not update profile");
          }
        });
      }
    },
    remove: function () {
      Backbone.Validation.unbind(this);
      return Backbone.View.prototype.remove.apply(this, arguments);
    }

  });

  return UserProfileView;
});
