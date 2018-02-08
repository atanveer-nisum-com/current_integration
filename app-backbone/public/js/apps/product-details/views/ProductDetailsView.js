define(function (require) {
  var Backbone = require('Backbone');
  var Handlebars = require('Handlebars');
  var common = require('Common');

  var ProductDetailsView = Backbone.View.extend({
    template: Handlebars.compile(require('text!./../templates/ProductDetailsView.html')),

    // initialize: function () {
    //   _.bindAll(this,"render");
    //   this.model.bind('change', this.render);
    // },
    events: {
      "click #subImage": "changeImage",
    },

    changeImage : function(ev){
      var imagePath = this.$(ev.currentTarget).attr("src");
      this.$("#display_image").attr("src",imagePath);
    } ,
    render: function () {
      this.model.fetch({
        type: 'GET',
        contentType: 'application/json',
        success: function (data, response, options) {
          if (options.xhr.status === 204) this.$el.html('<h2 class="title text-center">No Product Found</h2>');
          else {
            this.$el.html(this.template(data.toJSON()));
            var price = JSON.parse(JSON.stringify(data)).item.itemPrice[0].price;
            if (parseFloat(price) > 0){
              var pids = [];
              var cateIds = [];
              cateIds.push(JSON.parse(JSON.stringify(data)).categoryName);
              var CategoryDiscount = Backbone.Model.extend();
              var CategoryDiscountList = Backbone.Collection.extend({
                model: CategoryDiscount,
                 url: common.V1 + "discount/categories/"+(cateIds)
              });   
                
              var CategoryDiscountView = Backbone.View.extend({
                render: function(eventName) {
                  cateIds = [];
                 _.each(this.model.models, function(categoryDiscount){
                  $("#"+pids[0]).html("Sale: $"+(price - categoryDiscount.attributes.defaultDiscount));
    
                  });
                }
              });
    
              var categoryDiscounts = new CategoryDiscountList();    
              var categoryDiscountView = new CategoryDiscountView({model: categoryDiscounts});
              categoryDiscounts.fetch({
                type: 'GET',
                contentType: 'application/json',
              success: function() {
                categoryDiscountView.render();
                 }
              });
              
               pids.push(JSON.parse(JSON.stringify(data)).item.id);
              var ProductDiscount = Backbone.Model.extend();
              var ProductDiscountList = Backbone.Collection.extend({
                  model: ProductDiscount,
                   url: common.V1 + "discount/products/"+pids[0]
                });   
                var ProductDiscountView = Backbone.View.extend({
                  render: function(eventName) {
                   _.each(this.model.models, function(productDiscount){
                     //console.log(productDiscount);
                     var discount = 
                     productDiscount.attributes.productDiscount!=null?productDiscount.attributes.productDiscount
                      : productDiscount.attributes.defaultDiscount;
                         $("#"+pids[0]).html("Sale: $"+(price - discount));
                  });
                  }
                });
                var productDiscounts = new ProductDiscountList();    
                var productDiscountView = new ProductDiscountView({model: productDiscounts});
                productDiscounts.fetch({
                  type: 'GET',
                  contentType: 'application/json',
                success: function() {
                    productDiscountView.render();
                   }
                });
            }
          }
         
        }.bind(this)

      });
     
      return this;
    }
  });

  return ProductDetailsView;
});
