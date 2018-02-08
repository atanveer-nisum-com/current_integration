define(function (require) {
  var Backbone = require('Backbone');
  var Handlebars = require('Handlebars');

  var userIsLogged = function () {
		return (localStorage.getItem('isLogged') && localStorage.getItem('isLogged') === 'true');
	};

  var WishListButtonView = Backbone.View.extend({

    //emplate: Handlebars.compile(require('text!./../templates/WishListButtonView.html')),
    template:`<div class="choose">
    <ul class="nav nav-pills nav-justified">
        <li><a style="cursor: pointer;"><div id="add"><i class="fa fa-plus-square"></i>Add to wishlist</div></a></li>
    </ul>
</div>`,

    events: {
      'click #add': function (e) {
        if(userIsLogged()){
        this.model.save({}, {
          headers: {
            'auth-token': localStorage.getItem('token')
          },
          contentType: 'application/json',
          success: function (model, response) {
            console.log(response);
          },
          error: function (model, response) {
            console.log(response);
          }
        });
        }else{
          window.Router.navigate('loginsignup', {trigger: true});
      }
      }
    },

    initialize: function (options) {

    },
    addToWishList: function () {

    },

    render: function () {
      this.$el.html(this.template);
      return this;
    },

  });
  return WishListButtonView;
});
