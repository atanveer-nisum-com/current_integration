define(function (require) {
	var Backbone = require('Backbone');
	var Common = require('Common');

	var CategoryCollection = Backbone.Collection.extend({
		url: Common.V1 + 'categories/'
	});

	return CategoryCollection;
});