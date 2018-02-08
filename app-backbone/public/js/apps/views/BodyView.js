define(function (require) {
  var Backbone = require('Backbone');

  var BodyView = Backbone.View.extend({
    className: 'container',
    initialize: function (options) {
      this.options = options;
    },

    render: function () {
      var row = "<div class='row'></div>";
      var col9 = '<div class="col-sm-9"></div>';
      this.$el.empty();
      this.$el.html(row);
      
      this.$('.row').append(this.options.categoryView.el);
      this.$('.row').append(col9);

      this.$('.col-sm-9').append(this.options.recommendedItemsView.el);
      this.$('.col-sm-9').append(this.options.featuredItemView.el);
      return this;
    },
    remove: function() {
      this.$el.detach();
    }
  });

  return BodyView;
});
