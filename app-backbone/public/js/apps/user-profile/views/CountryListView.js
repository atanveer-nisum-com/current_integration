define(function (require) {
  var Backbone = require('Backbone');
  var Handlebars = require('Handlebars');
/**
 * View of country drop down
 */
  var CountryListView = Backbone.View.extend({
    template: Handlebars.compile(require('text!./../templates/CountryList.html')),
    cBean: Object.create(null),
    render: function () {
      this.collection.fetch({
        type: 'GET',
        contentType: 'application/json',

        success: function (data) {
          data.remove([{ id: this.cBean.id }]);
          this.$el.empty();
          this.$el.html(this.template({ proModel: data.toJSON(), cBean: this.cBean }));
        }.bind(this)

      });
      return this;
    }
  });
  return CountryListView;
});
