define(function (require) {
	var Backbone = require('Backbone');
	var Common = require('Common');

	var CategoryList = Backbone.Model.extend({
		url: Common.V1 + 'products/categories/',
		initialize: function (attributes, options) {
			this.url += attributes.categoryName;
			this.categoryName = attributes.categoryName;
		}

	});

	return CategoryList;
});