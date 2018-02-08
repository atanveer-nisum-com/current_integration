define(function (require) {

  var Backbone = require('Backbone');
  var common = require('Common');

  var CategoryDiscount = Backbone.Model.extend({
    url: common.V1 + 'discount/categories/',
    initialize: function (attributes, options) {
      this.url += attributes.catId;
      this.catId = attributes.catId;
    }
  });

  return CategoryDiscount;
});
