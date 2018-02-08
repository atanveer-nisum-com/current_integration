define(function (require) {
	var Backbone = require('Backbone');
	var HeaderView = require('../../views/HeaderView');
	var FooterView = require('../../views/FooterView');

	var ProductDetailsPageView = require('./ProductDetailsPageView');


	var MainView = Backbone.View.extend({
		
		initialize: function (options) {
			_.extend(this, _.pick(options, "param"));
			this.subviews = [];

		},

		render: function () {
			
			var headerView = new HeaderView();
			this.subviews.push(headerView);
			this.$el.append(headerView.render().el);
			
			var productDetailsPageView = new ProductDetailsPageView({param:this.param});
			this.subviews.push(productDetailsPageView);
			this.$el.append(productDetailsPageView.render().el);

			var footerView = new FooterView();
			this.subviews.push(footerView);
			this.$el.append(footerView.render().el);

		}
	});

	return MainView;
});