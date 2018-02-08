define(function(require) {
	var Backbone = require('Backbone');
	var common = require('Common');

	var AddItem = Backbone.Model.extend({
		url : common.V1_CART +'items/',
		initialize: function (attributes, options) {
			this.url += attributes.itemId;
		},
	});

	return AddItem;
});