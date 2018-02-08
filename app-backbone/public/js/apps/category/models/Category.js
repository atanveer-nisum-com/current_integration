define(function(require) {
	var Backbone = require('Backbone');
	var Common = require('Common');

	var Category = Backbone.Model.extend({
		url: Common.V1 + 'categories/:id'
	});

	return Category;
});