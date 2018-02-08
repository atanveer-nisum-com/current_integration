define(function (require) {

  var Backbone = require('Backbone');
  var common = require('Common');

  var ProductCategoryDiscountList = Backbone.Collection.extend({
    url: common.V1 + 'discount/products/categories/',
    updateUrl: function (newUrl) {
      this.url = this.baseUrl;
      this.url += newUrl;
    },
    baseUrl: common.V1 + 'discount/products/categories/',
    productDiscount: Object.create(null),
    categoryDiscount: Object.create(null),
    productDiscountType: Object.create(null),
    categoryDiscountType: Object.create(null),
    setDiscountInItems: function (response, items) {
      // populate category names and product ids
      response.forEach(function (discount) {
        if (discount.productDiscount) {
          this.productDiscount[discount.productId] = discount.productDiscount;
          this.productDiscountType[discount.productId] = discount.promotionType;
        }
          
        //else if (null !== discount.productId ) this.productDiscount[discount.productId] = 0;
        this.categoryDiscount[discount.categoryName] = discount.defaultDiscount;
        this.categoryDiscountType[discount.categoryName] = discount.promotionType;
      }, this);
      // populate discount in item
      return this.setDiscount(items);
    },
    setDiscount: function (items) {
      items.forEach(function (item) {
        if (item.item.id in this.productDiscount && this.productDiscount[item.item.id] > 0 && this.productDiscountType[item.item.id] == "DOLLAR_OFF_ORDER")
            item.discount = item.item.itemPrice[0].price - this.productDiscount[item.item.id];
        else if(item.item.id in this.productDiscount && this.productDiscount[item.item.id] > 0 && this.productDiscountType[item.item.id] == "PERCENT_OFF")
            item.discount = (item.item.itemPrice[0].price * this.productDiscount[item.item.id]) / 100;

        else if (item.categoryName in this.categoryDiscount && this.categoryDiscountType[item.categoryName] == "DOLLAR_OFF_ORDER") 
            item.discount = item.item.itemPrice[0].price - this.categoryDiscount[item.categoryName];
        else if(item.categoryName in this.categoryDiscount && this.categoryDiscountType[item.categoryName] == "PERCENT_OFF")
            item.discount = item.item.itemPrice[0].price-(item.item.itemPrice[0].price * this.categoryDiscount[item.categoryName]) / 100;
      }, this);
      return items;
    }
  });

  return ProductCategoryDiscountList;
});
