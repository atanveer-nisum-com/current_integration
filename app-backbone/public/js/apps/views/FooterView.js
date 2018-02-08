define(function (require) {
	var Backbone = require('Backbone');
	var Handlebars = require('Handlebars');

	var FooterView = Backbone.View.extend({
		tagName: 'footer',
		id: 'footer',
		template: Handlebars.compile(require('text!./../templates/FooterView.html')),

		render: function () {
			this.$el.html(this.template());
			return this;
		}
	});

	return FooterView;
});