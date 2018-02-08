define(function (require) {
  var Backbone = require('Backbone');

  var Category = require('./../category/models/Category');
  var CategoryCollection = require('./../category/collections/CategoryCollection');
  var CategoryView = require('./../category/views/CategoryView');

  var FeaturedItem = require('../featured-items/models/FeaturedItem');
  var FeaturedItemView = require('../featured-items/views/FeaturedItemView');

  var RecommendedItemsView = require('../recommended-items/views/RecommendedItemsView');

  var MainPageView = Backbone.View.extend({
    className: 'container',
    /*initialize: function () {
       this.subviews = [];
    },*/

    render: function () {
      var row = "<div class='row'></div>";
      var col9 = '<div class="col-sm-9"></div>';
      this.$el.empty();
      this.$el.html(row);
      var categoryCollection = new CategoryCollection({model: new Category()});
      var categoryView = new CategoryView({
        collection: categoryCollection
      });
      // this.subviews.push(categoryView);
      this.$('.row').append(categoryView.render().el);
      this.$('.row').append(col9);

      var featuredModel = new FeaturedItem();

      var recommendedItemsView = new RecommendedItemsView({model: featuredModel});
      // this.subviews.push(recommendedItemsView);
      this.$('.col-sm-9').append(recommendedItemsView.render().el);

      var featuredItemView = new FeaturedItemView({
        model: featuredModel
      });
      // this.subviews.push(featuredItemView);
      this.$('.col-sm-9').append(featuredItemView.render().el);

      return this;
    }
  });

  return MainPageView;
});
