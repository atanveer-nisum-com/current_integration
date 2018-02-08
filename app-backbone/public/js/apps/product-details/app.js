define(function(require) {
	var MainView = require('./views/MainView');
	var ProductDetails = require('./models/ProductDetails')
	return {
		run: function (viewManager,param) {
			this.param = param;
			var view = new MainView({param : this.param});
			viewManager.show(view);

		}
	};
});