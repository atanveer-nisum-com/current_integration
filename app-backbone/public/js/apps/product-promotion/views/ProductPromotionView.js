define(function (require) {
    var Backbone = require('Backbone');
    var Handlebars = require('Handlebars');
  
    var ProductPromotionView = Backbone.View.extend({
      className: 'col-sm-4',
      template: Handlebars.compile(require('text!./../templates/productPromotion.html')),
      render: function (data) {
        console.log("Data == "+JSON.stringify(data));
        this.$el.html(this.template(data));
        return this;
      }
    });
    return ProductPromotionView;
  });
  