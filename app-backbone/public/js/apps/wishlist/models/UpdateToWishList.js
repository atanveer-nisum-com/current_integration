define(function(require) {
	var Backbone = require('Backbone');
	var common = require('Common');

	var AddToWishList = Backbone.Model.extend({
		url : common.V1_USERS + 'wishlists/',
		idAttribute: '_id',
		initialize: function (attributes, options) {
			if(this.attributes.wishListId)
				this.wishListId = this.attributes.wishListId;
			this.url += attributes.wishListId + '/items/' + attributes.itemId;
		},
		updateUrl : function (newurl){
			this.url = newurl;
		}
	});

	return AddToWishList;
});