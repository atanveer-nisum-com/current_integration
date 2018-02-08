define(function(require) {
	var Backbone = require('Backbone');
	var common = require('Common');

	var Prodcut = Backbone.Model.extend({
		url : common.V1 + 'products/',
		initialize: function (attributes, options) {
			this.url += attributes.productId;
			this.productId = attributes.productId;
		}
		
	});

	return Prodcut;
});