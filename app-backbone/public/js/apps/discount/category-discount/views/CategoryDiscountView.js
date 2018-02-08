define(function (require) {

  var Backbone = require('Backbone');
  var Handlebars = require('Handlebars');
  var CategoryDiscount = require('../models/CategoryDiscount');
  var CategoryDiscountView = Backbone.View.extend({
    initialize: function (options) {
      this.model = new CategoryDiscount({
        catId: options.catId
      });
      this.id = options.catId;
    },
    template: 'Sale Price: $',
    render: function () {
      this.model.save(null, {
        contentType: 'application/json',
        success: function (model, response) {
          this.$el.empty();
          this.$el.attr('id', this.id);
          this.$el.html(this.template);
          console.log(response);
        }.bind(this),
        error: function (model, response) {}
      });
      /* _.each(this.model.models, function (categoryDiscount) {
        for (var i = 0; i < subViews.length; i++) {
          var obj = $("#" + categoryDiscount.attributes.categoryName + "_" + pids[i]);
          if ($(obj).val() != undefined) {
            var element = subViews[i].el;
            var pd = $(element).find('div')[3];
            var discountDiv = $("#" + pids[i]);
            var pp = $(discountDiv).prev().prev();
            var actualPrice = $(pp).html();
            actualPrice = actualPrice.substring(1, actualPrice.length);
            //console.log(categoryDiscount);
            actualPrice = (actualPrice - categoryDiscount.attributes.defaultDiscount);
            // console.log(actualPrice);
            $(discountDiv).html("Sale Price: $" + (actualPrice));
            $(pd).html($(discountDiv).html());
          }
        }
      }); */
      return this;
    }
  });
  return CategoryDiscountView;
});
