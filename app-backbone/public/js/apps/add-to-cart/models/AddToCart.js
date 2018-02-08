define(function(require) {
	var Backbone = require('Backbone');
	var common = require('Common');

	var AddToCart = Backbone.Model.extend({
		url : common.V1 + 'cart/',
		idAttribute: '_id',
		initialize: function (attributes, options) {
			if(this.attributes.itemId)
				this.itemId = this.attributes.itemId;
			this.url +=  'items/' + attributes.itemId;
		},
		updateUrl : function (newurl){
			this.url = newurl;
		}
	});

	return AddToCart;
});