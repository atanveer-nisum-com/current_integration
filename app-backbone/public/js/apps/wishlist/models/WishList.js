define(function(require) {
	var Backbone = require('Backbone');
	var common = require('Common');

	var WishList = Backbone.Model.extend({
		url : common.V1_USERS + 'wishlists/',
		initialize: function (attributes, options) {
		//	console.log(options);
			// this.url += localStorage.getItem('token') + '/wishlists' ;	
		//	console.log("url in model : "+this.url);
		}
		
	});

	return WishList;
});