define(function (require) {
	var Handlebars = require('Handlebars');
	var Validation = require('Validation');

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

	var LoginView = Backbone.View.extend({
		template: Handlebars.compile(require('text!./../templates/LoginView.html')),
		
		events: {
			'click #submit': function (e) {
				e.preventDefault();
				this.onClick();
			}
		},

		initialize: function () {
			// This hooks up the validation
			Backbone.eventAgg.on('invoke-login', this.invokeLoginEvent, this);
			Backbone.Validation.bind(this);
			
			
		},

		onClick: function () {
			var data = this.$('form').serializeObject();
			this.model.set(data);
			var loginData = null;
			if ($('rememberMe').is(":checked")) {
				loginData = { 'emailAddress': data.emailAddress, 'password': data.password, 'rememberMe': data.rememberMe, 'guestToken': '' };
			}
			else {
				loginData = { 'emailAddress': data.emailAddress, 'password': data.password, 'rememberMe': false, 'guestToken': '' };
			}
			//var temp = this.model.url;
			
			// Check if the model is valid before saving

			if (this.model.isValid(true)) {
				this.doLogin(loginData);
			}
		},

		invokeLoginEvent : function(logindata){
			//console.log("im here");
			var loginData = { 'emailAddress': logindata.emailAddress, 'password': logindata.password, 'rememberMe': false, 'guestToken': '' };
			this.doLogin(loginData);
			/*var loginData = { 'emailAddress': logindata.emailAddress, 'password': logindata.password, 'rememberMe': false, 'guestToken': '' };
			this.model.save(loginData,{
				succes : function(data){
					console.log("in success");
					window.Router.navigate("/",{trigger:true});
				}
			});*/
			
		},
		doLogin : function (loginData){
			this.model.save(loginData,
				 {
					validate: false,
					success: function (response) {
						console.log("logged in: ");
						//Backbone.session.token =  response.get('token'); 
						//Backbone.session.isLogged = true;
						localStorage.setItem('token' , response.get('token'));
						localStorage.setItem('isLogged' , true);
						localStorage.setItem('firstName', response.get('firstName'));
						Backbone.eventAgg.trigger('loginStatusEvent');
						window.Router.navigate('/', {trigger: true});
					},
					error: function (response) {
						console.log("error");
						console.log(response);
						alert("incorrect email or password");
					}
				});

		},

		remove: function () {
			// Remove the validation binding
			Backbone.Validation.unbind(this);
			return Backbone.View.prototype.remove.apply(this, arguments);
		},

		render: function () {
			localStorage.clear();
			Backbone.eventAgg.trigger('loginStatusEvent');
			this.$el.html(this.template);
			return this;
		}
	});
	return LoginView;
});