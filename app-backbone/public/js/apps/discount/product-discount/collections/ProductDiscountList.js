define(function (require) {

  var Backbone = require('Backbone');
  var common = require('Common');

  var ProductDiscountList = Backbone.Collection.extend({
    url: common.V1 + 'discount/products/categories',
    updateUrl: function (newUrl) {
      this.url += newUrl;
    }
  });
  return ProductDiscountList;
});
