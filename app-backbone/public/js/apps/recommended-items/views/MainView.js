define(function (require) {
    var Backbone = require('Backbone');
    
    var RecommendedItemsView = require('./RecommendedItemsView');

	var MainView = Backbone.View.extend({
		render: function () {
			var recommendedItemsView = new RecommendedItemsView({model: this.model});
			this.subviews.push(recommendedItemsView);
			this.$el.append(recommendedItemsView.render().el);

			return this;
		}
	});
	return MainView;
});