define(function (require) {
    var Backbone = require('Backbone');
    var Handlebars = require('Handlebars');
    var UpdateToWishList = require('.././models/UpdateToWishList')
  
    var WishListItem = require('./WishListItem');
    var subViews = [];
    var items = [];
    var pids = [];
    var WishListView = Backbone.View.extend({
      className: 'wishlist_items',
      template: Handlebars.compile(require('text!./../templates/WishListView.html')),
      render: function () {
        subViews.length = 0;
         this.model.fetch({
          headers: {
            'auth-token': localStorage.getItem('token')
          },
          type: 'GET',
          contentType: 'application/json',
          success: function (data) {
            var wishlists = data.toJSON().content;
            this.$el.empty();
            this.$el.html(this.template(wishlists));
            wishlists.forEach(function (element) {
              element.items.forEach(function (prod) {
                items.push(prod);
                if ((prod.item.id !== null && !(prod.item.id in this.collection.productDiscount)) || this.collection.productDiscount[prod.item.id] !== 0) {
                  pids.push(prod.item.id);
                  this.collection.productDiscount[prod.item.id] = 0;
                }
              }, this);
            }, this);
            var collection = this.collection;
            var renderWishlists = this.renderWishlists;
            if (pids.length > 0) {
              collection.updateUrl(pids);
              collection.fetch({
                type: 'GET',
                contentType: 'application/json',
                success: function (model, response) {
                  if (response.length > 0) {
                    collection.setDiscountInItems(response, items);
                    renderWishlists(wishlists);
                  }
                  items.length = 0;
                },
                error: function (model, response) {
                  renderWishlists(wishlists);
                  items.length = 0;
                  console.log(model);
                  console.log(response);
                }
              });
            } else {
              collection.setDiscount(items);
              renderWishlists(wishlists);
            }
            pids.length = 0;
          }.bind(this),
          error: function (model, response) {
            if(response.status === 401)
            window.Router.navigate('/loginsignup', {trigger: true});
            else{
              this.$el.html(this.template());
              this.$('.cart_info').append('No wishlists found');
            }
            
          }
        });
        return this;
      },
      remove: function () {
        subViews.forEach(function (subView) {
          subView.remove();
          subView = null;
        });
        Backbone.View.prototype.remove.apply(this, arguments);
      },
      renderWishlists: function (wishlists) {
        var updateToWishList;
        var wishListItem;
        wishlists.forEach(function (wishlist) {
          updateToWishList = new UpdateToWishList({wishListId: wishlist.id});
          wishListItem = new WishListItem({model: updateToWishList});
          subViews.push(wishListItem);
          this.$('#tableBody').append(wishListItem.render(wishlist).el);
        });
      }
    });
    return WishListView;
  });
  