define(function (require) {
	var Backbone = require('Backbone');
	var common = require('Common');

	var StateCollection = Backbone.Collection.extend({
		url: common.V1 + 'states/230/',
	});
	return StateCollection;
});