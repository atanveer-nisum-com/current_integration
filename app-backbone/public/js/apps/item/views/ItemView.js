define(function (require) {
  var Backbone = require('Backbone');
  var Handlebars = require('Handlebars');
  var WishListButtonView = require('../../wishlist/wishlist-button/views/WishListButtonView');
  var UpdateToWishList = require('../../wishlist/models/UpdateToWishList');
  var AddItem = require('../models/AddItem');
  var AddToCartView = require("../../add-to-cart/views/AddToCartView");
  var AddToCart = require("../../add-to-cart/models/AddToCart");
  var subViews = [];
  var subModels = [];

    var ItemView = Backbone.View.extend({
        className: 'col-sm-4',
        template: Handlebars.compile(require('text!./../templates/item.html')),
        render: function (data) {
            this.$el.html(this.template(data));
            var itemId = data.item.id;
            console.log(localStorage.getItem('isLogged'));
            if (localStorage.getItem('isLogged') == 'true') {
              console.log("im here");
                var addToCart = new AddToCart({itemId:itemId});
                var addToCartView = new AddToCartView({model:addToCart,caption:"Add to Cart"});
                subViews.push(addToCartView);
                this.$('#product_center').append(addToCartView.render().el);
                var updateToWishList = new UpdateToWishList({itemId: itemId, wishListId: 0});
                var wishListButtonView = new WishListButtonView({model: updateToWishList});
                subViews.push(wishListButtonView);
                subModels.push(updateToWishList);
                this.$('#product_center').append(wishListButtonView.render().el);
            }

      $('img').on('error', function () {
        $(this).attr('src', '/images/home/default-image.jpg');
      })

      return this;
    },
    events: {
      'click #addItem': function (e) {
        this.addItem();
      }
    },
    isLogged: function(){
      return localStorage.getItem('isLogged');
    },
    getToken: function(){
      return localStorage.getItem('token');
    },
        addItem: function(){
      if(this.getToken()!=null) {
          this.model.save({}, {
          headers: {
            'auth-token': this.getToken()
          },
          contentType: 'application/json',
          success: function (model, response) {
            console.log(response);
          },
          error: function (model, response) {
            console.log(response);
          }
        });
      }
      else{
          this.model.save({}, {
          contentType: 'application/json',
          success: function (model, response) {
            if(response.userToken){
              localStorage.setItem('token',response.userToken.token);
            }
            console.log(response);
          },
          error: function (model, response) {
            console.log(response);
          }
        });
      }
    },

    remove: function () {
      subModels.forEach(function (subModel) {
        subModel = null;
      });
      subViews.forEach(function (subView) {
        subView.remove();
        subView = null;
      });
      Backbone.View.prototype.remove.apply(this, arguments);
    }
  });
  return ItemView;
});
