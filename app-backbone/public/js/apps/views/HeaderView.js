define(function (require) {
	var Backbone = require('Backbone');
	var Handlebars = require('Handlebars');
	var common = require('Common');

	var userIsLogged = function () {
		return (localStorage.getItem('isLogged') && localStorage.getItem('isLogged') === 'true');
	};
	var HeaderView = Backbone.View.extend({
		tagName: 'header',
		id: 'header',
		template: Handlebars.compile(require('text!./../templates/HeaderView.html')),
		events: {
			'click #login_button': function (e) {				
					this.login();
				
			} 
    },
    initialize: function () {
			Backbone.eventAgg.on('loginStatusEvent', this.changeLoginStatus, this);
      $(window).bind('scroll', function () {
        this.onScroll($(window).scrollTop());
			}.bind(this));
    },
		render: function () {
			this.$el.html(this.template());//{title: 'TheMailer'}
			this.changeLoginStatus();
			return this;
		},
		login : function(){
			if(userIsLogged()){
			
				$.ajax({
					headers: {
						'auth-token': localStorage.getItem('token')
					},
					url : common.V1_USERS + 'logout/',
					type: 'POST',
					success: function (model, response) {
						console.log(response);
						localStorage.setItem('isLogged',false);
						localStorage.removeItem('token');
						localStorage.removeItem('firstName');
						this.changeLoginStatus();
					}.bind(this),
					error: function (model, response) {
						console.log(model);
					}
				});
				window.Router.navigate('/', {trigger: true});
			}
			else
				window.Router.navigate('loginsignup', {trigger: true});
			
		},
		changeLoginStatus : function(){
				
				if(userIsLogged()){
					this.$('#login_button').html('<i class="fa fa-lock"></i>  Logout');
					this.$('#account_button').html('<i class="fa fa-user"></i>  '+localStorage.getItem('firstName').toUpperCase());
					this.$('#wishlist_button').show();
					this.$('#account_button').show();

				}
				else{
				this.$('#login_button').html('<i class="fa fa-lock"></i>  Login');
				this.$('#account_button').html('<i class="fa fa-user"></i> Account')
				this.$('#wishlist_button').hide();
				this.$('#account_button').hide();
				}
		},
		onScroll: function (height) {
      if (height >= 50) {
        this.$el.children('.header-middle').addClass('header-middle-fixed');
      } else {
        this.$el.children('.header-middle').removeClass('header-middle-fixed');
      }
    }
	});

	return HeaderView;
});
