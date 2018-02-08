define(function (require) {
  var Backbone = require('Backbone');
  var Handlebars = require('Handlebars');
  var ItemView = require('../../../item/views/ItemView');

  var subViews = [];

  var CategoryListView = Backbone.View.extend({
    tagName: 'div',
    className: 'col-sm-9',

    template: Handlebars.compile(require('text!./../templates/CategoryListView.html')),

    render: function () {
      this.$el.html(this.template());
      var response = this.model.fetch({
        type: 'GET',
        contentType: 'application/json',
        success: function (model, response, options) {
          if (options.xhr.status === 204) this.$('#categorytitle').html('No Items found');
          else {
            _.each(response.content, function (element) {
              var itemView = new ItemView();
              subViews.push(itemView);
              this.$('#singleItem').append(itemView.render(element).el);
            });
            this.$('#categorytitle').html(response.content[0].categoryName);
          }
        }.bind(this),
        error : function(model, response){
          if (response.errorCode === '204')
            this.$('#categorytitle').html('No Result');
        }
      });
      return this;
    },
    remove: function () {
      subViews.forEach(function (subView) {
        subView.remove();
        subView = null;
      });
      Backbone.View.prototype.remove.apply(this, arguments);
    }
  });

  return CategoryListView;
});
