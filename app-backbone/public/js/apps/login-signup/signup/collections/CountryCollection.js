define(function (require) {
	var Backbone = require('Backbone');
	var common = require('Common');

	var CountryCollection = Backbone.Collection.extend({
		url: common.V1 + 'countries/',
	});

	return CountryCollection;
});