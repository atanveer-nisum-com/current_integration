define(function (require) {

  var Backbone = require('Backbone');
  var common = require('Common');

  var CategoryDiscountList = Backbone.Collection.extend({
    url: common.V1 + 'discount/categories/',
    updateUrl: function (newUrl) {
      this.url += newUrl;
    }
  });

  return CategoryDiscountList;
});
