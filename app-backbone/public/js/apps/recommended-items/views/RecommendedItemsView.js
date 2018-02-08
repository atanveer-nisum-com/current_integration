define(function (require) {
  var Backbone = require('Backbone');
  var Handlebars = require('Handlebars');
  var SlickCarousel = require('SlickCarousel');
  var common = require('Common');
  var ItemView = require('./../../item/views/ItemView');
  var AddItem = require('./../../item/models/AddItem');

  var subViews = [];
  var pids = [];
  var items = [];
  var RecommendedItemsView = Backbone.View.extend({
    className: 'recommended_items',
    template: Handlebars.compile(require('text!./../templates/RecommendedItemsView.html')),
    render: function () {
      subViews.length = 0;
      this.$el.empty();
      this.$el.html(this.template());
      this.model.fetch({
        type: 'GET',
        contentType: 'application/json',
        success: function (data) {
          data.toJSON().content.forEach(function (item) {
            items.push(item);
            if ((item.item.id !== null && !(item.item.id in this.collection.productDiscount)) || this.collection.productDiscount[item.item.id] !== 0) {
              pids.push(item.item.id);
              this.collection.productDiscount[item.item.id] = 0;
            }
          }, this);
          var collection = this.collection;
          var renderItems = this.renderItems;
          if (pids.length > 0) {
            collection.updateUrl(pids);
            collection.fetch({
              type: 'GET',
              contentType: 'application/json',
              success: function (model, response) {
                if (response.length > 0) {
                  renderItems(collection.setDiscountInItems(response, items));
                }
                items.length = 0;
              },
              error: function (model, response) {
                renderItems(items);
                items.length = 0;
              }
            });
          } else renderItems(collection.setDiscount(items));
          pids.length = 0;
        }.bind(this)
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
    renderItems: function (items) {
      items.forEach(function (item) {
        var addItem = new AddItem({itemId:item.item.id});
        var itemView = new ItemView({model: addItem });
        subViews.push(itemView);
        this.$('.slick-carousel').append(itemView.render(item).el);
      });
      $('.slick-carousel').slick({
        autoplay: true,
        autoplaySpeed: 3000,
        infinite: true,
        slidesToShow: 3,
        slidesToScroll: 3,
        prevArrow: '<a class="slick-prev left recommended-item-control"><i class="fa fa-angle-left"></i></a>',
        nextArrow: '<a class="slick-next right recommended-item-control"><i class="fa fa-angle-right"></i></a>'
      });
    }
  });
  return RecommendedItemsView;
});
