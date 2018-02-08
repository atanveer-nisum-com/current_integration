define(function (require) {
  var Backbone = require('Backbone');
  var Handlebars = require('Handlebars');

  var CategoryListPage = Backbone.View.extend({
    template: Handlebars.compile(require('text!./../templates/CategoryListPage.html')),
    initialize: function (options) {
      this.options = options;
    },
    render: function (name) {
      this.$el.html(this.template());
      this.$('#category').append(this.options.categoryView.el);
      this.$('#category').append(this.options.categoryListView.el);
      return this;
    }
  });
  return CategoryListPage;
});
