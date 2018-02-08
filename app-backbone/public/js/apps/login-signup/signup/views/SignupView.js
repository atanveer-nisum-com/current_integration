define(function (require) {
	var Backbone = require('Backbone');
	var Handlebars = require('Handlebars');
	var Validation = require('Validation');
	var Serializer = require('Serializer');
	var CountryListCollection = require('./../collections/CountryCollection');
	var StateListCollection = require('./../collections/StateCollection');

	_.extend(Backbone.Validation.callbacks, {
		valid: function (view, attr, selector) {
			var $el = view.$('[name=' + attr + ']'), 
				$group = $el.closest('.form-group');
			
			$group.removeClass('has-error');
			$group.find('.text-danger').html('').addClass('hidden');
		},
		invalid: function (view, attr, error, selector) {

			var $el = view.$('[name=' + attr + ']'), 
				$group = $el.closest('.form-group');
			
			$group.addClass('has-error');
			$group.find('.text-danger').html(error).removeClass('hidden');
		}
	});

	var SignupView = Backbone.View.extend({
		template: Handlebars.compile(require('text!./../templates/SignupView.html')),

		render: function () {
				this.collection.countryCollection.fetch({
        		type: 'GET',
        		contentType: 'application/json',
        		success: function (data) {
					 var countryModel = data.toJSON();	
					 this.collection.stateCollection.fetch({
      	   			 type: 'GET',
      	   			 contentType: 'application/json',
      	   			 success: function (data) {

			  	 	 this.$el.empty();
           			 this.$el.html(this.template({countryModel: countryModel, stateModel: data.toJSON()}));
          			 }.bind(this)
				});
        	}.bind(this)
		});
	    return this;
		},

		events: {
			'click #submit': function (e) {
				e.preventDefault();
				this.signup();
			},
			'change #country': 'addOrRemoveStateSelector'
		},
	
		initialize: function () {
			// This hooks up the validation
			Backbone.Validation.bind(this);
		},
	
		signup: function () {
			var data = this.$('form').serializeObject();
			//console.log(data);
			
			//console.log("json");
			//console.log(json);
			this.model.set(data);
			// Check if the model is valid before saving
			if(this.model.isValid(true)){
				// this.model.save();
				/*_.each(this.$('input'), function(input)
				{
					this.model.set(input.name, input.value);
				}, this);*/
				
				

				  json = {
					firstName: data.firstName,
					lastName: data.lastName,
					emailAddress: data.emailAddress,
					password: data.password,
					addresses : [{
						addressLine1: data.addressLine1,
						addressLine2: data.addressLine2,
						addressType : 1,
						city: data.city,
						phoneNumber: data.phoneNumber,
						zipcode: data.zipcode,            
						stateBean:{
							id : data.state
						},
						countryBean: {
							id : data.country
						} 
					}]
				};
				if ($('#country').val() != "230") {
					//json.addresses.stateBean.id = null;
					json.addresses[0].stateBean = null;
					//this.model.set({state : null});
				  }
				if(json.addresses[0].addressLine2.length<1)
				json.addresses[0].addressLine2=null;
				//new model attributes from the form are now available and ready to be synced
				//with the server.
				this.model.clear();
				this.model.set(json);

				console.log("end model");
				console.log(this.model.toJSON());
				this.model.save({}, {
					validate:false,
					success: function (model, response) {
						localStorage.setItem('token', response.token);
						logindata = {
							emailAddress: data.emailAddress,
							password: data.password,
						};
						Backbone.eventAgg.trigger('invoke-login',logindata);
					},
					error: function (model, response) {
						// TODO: handle signup failed
						console.log(response.errorDescription);
					}
				});
				
				
			}

		},

		addOrRemoveStateSelector: function () {
			if ($('#country').val() == "230") {
			  $('#stateDivision').removeClass('hidden');
			}
			else {
			  $('#stateDivision').addClass('hidden');
			}
		  },
		
		remove: function() {
			// Remove the validation binding
			Backbone.Validation.unbind(this);
			return Backbone.View.prototype.remove.apply(this, arguments);
		},
	});
	return SignupView;
});