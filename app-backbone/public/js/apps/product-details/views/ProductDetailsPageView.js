define(function (require) {
    var Backbone = require('Backbone');
    var Handlebars = require('Handlebars');

    var ProductDetailsPageView = Backbone.View.extend({
      className: 'container',
      template: '<div class="row" id="details"></div>',
      initialize: function (options) {
        this.options = options;
      },
      render: function () {
        this.$el.html(this.template);
        this.$('#details').append(this.options.categoryView.el);
        this.$('#details').append(this.options.productDetailsView.el);
        this.$('#details').append(this.options.recommendedItemsView.el);

        return this;
      },
      remove: function () {
        this.$el.detach();
      }
    });
  
    return ProductDetailsPageView;
  });
  