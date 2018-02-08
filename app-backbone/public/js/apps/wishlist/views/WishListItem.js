define(function (require) {
  var Backbone = require('Backbone');
  var Handlebars = require('Handlebars');
  var common = require('Common');


  var WishListItem = Backbone.View.extend({
    el: "tbody",
    template: Handlebars.compile(require('text!./../templates/WishListItem.html')),
    events: {
      "click #remove": "removeView",
    },
    render: function (data) {
      this.$el.html(this.template(data));
      return this;
    },
    removeView: function (ev) {
      var row = $(ev.currentTarget).closest("tr");
      var row_id = row.data("id");
      var temp = common.V1_USERS + 'wishlists/' + this.model.wishListId + '/items/' + row_id;
      this.model.updateUrl(temp);
      // this.model.destroy({
      //   headers: {
      //     'auth-token': localStorage.getItem('token')
      //   },
      //   contentType: 'application/json',
      //   success: function(model, response) {
      //     row.remove();
      //     console.log(model);
      //   },
      //   error: function (model, response) {
      //     console.log(response);
      //   },
      //   wait: true
      // });
      $.ajax({
        headers: {
          'auth-token': localStorage.getItem('token')
        },
        url: temp,
        type: 'DELETE',
        success: function (model, response) {
          row.remove();
          console.log(response);
        },
        error: function (model, response) {
          console.log(model);
        }
      });
    }
  });
  return WishListItem;
});
