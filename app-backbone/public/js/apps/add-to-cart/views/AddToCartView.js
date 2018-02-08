define(function (require) {
    var Backbone = require('Backbone');
    var Handlebars = require('Handlebars');
  
    var AddToCartView = Backbone.View.extend({
      
      template: Handlebars.compile(require('text!./../templates/AddToCartView.html')),

      events: {
        'click #add': function (e) {
            //e.preventDefault();
            this.addToCart();
          }
      },
      
      initialize: function(options){
      _.extend(this,_.pick(options,"caption"));
      },
     
      render: function () {
        this.$el.html(this.template({caption: this.caption}));
        return this;
      },
      addToCart: function(){

        this.model.save({}, {
          headers: {
            'auth-token': localStorage.getItem('token')
          },
          contentType: 'application/json',
          success: function (model, response) {
            console.log(response);
          },
          error: function (model, response) {
            console.log(response);
          }
        });
        
      }
    });
    return AddToCartView;
  });
  