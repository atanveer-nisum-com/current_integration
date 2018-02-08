define(function(require) {
 
  var Backbone = require('Backbone');
	var common = require('Common');

	var ProductData = Backbone.Model.extend({
        url: common.V1 + 'products/'
    });

	return ProductData;
});
