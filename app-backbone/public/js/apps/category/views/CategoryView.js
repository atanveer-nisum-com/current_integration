define(function (require) {
	var Backbone = require('Backbone');
	var Handlebars = require('Handlebars');

	var CategoryView = Backbone.View.extend({
		tagName:'div',
        className:'col-sm-3',
		template: Handlebars.compile(require('text!./../templates/CategoryView.html')),

		render: function () {
			this.collection.fetch({
				contentType: 'application/json',
				type: 'GET',
                success: function(categoryCollection) {
					this.$el.html(this.template(categoryCollection.toJSON()));
                }.bind(this)
            });
			return this;
		}
	});

	return CategoryView;
});
