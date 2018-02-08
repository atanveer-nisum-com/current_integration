define(function(require) {
    var Backbone = require('Backbone');
      var common = require('Common');
  
      var ProductDiscountData = Backbone.Model.extend({
          url: common.V1 + 'discount/'
      });
  
      return ProductDiscountData;
  });
  