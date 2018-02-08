define(function (require) {
    var Backbone = require('Backbone');
    var Handlebars = require('Handlebars');
    var common = require('Common');
    var ItemView = require('./../../item/views/ItemView');
    var AddItem = require('./../../item/models/AddItem');
    var subViews = [];
    var pids = [];
    var items = [];
    var productsToSHow = 9;
    var number = 0;

    var FeaturedItemView = Backbone.View.extend({
        className: 'features_items',
        template: '<h2 class="title text-center">Featured Items</h2><div id="singleItem"></div>',

        detectScroll: function () {
            var that = this;
            $(document).scroll(function () {
                var $el = $(this);
                var offset = 500;
                var scrollPosition = parseInt($el.scrollTop(), 10);
                var windowHeight = parseInt($(window).height(), 10);
                var realScrollPosition = parseInt(scrollPosition + windowHeight, 10);
                var elementToScroll = parseInt(that.$el.find('#singleItem .col-sm-4:last-child').offset().top, 10);
                var products = JSON.parse(localStorage.getItem('featured-products'));
                var newProducts = [];

                if ((realScrollPosition - offset) > elementToScroll && number <= products.length) {
                    number = number + productsToSHow;
                    newProducts = products.slice(number, number + number);
                    if (number >= products.length) {
                        number = products.length;
                    }
                    that.renderItems(newProducts);
                }
            });
        },
        render: function () {
            subViews.length = 0;
            this.fetchProducts().done(function () {
                var products = JSON.parse(this.getProducts());
                this.renderItems(products.slice(0, productsToSHow));
                this.detectScroll();
            }.bind(this));


            return this;
        },

        initialize: function () {
            number = 0;
            _.bindAll(this, 'detectScroll');

        },
        fetchProducts() {
            var response = this.model.fetch({
                type: 'GET',
                contentType: 'application/json',
                success: function (data) {
                    this.$el.empty();
                    this.$el.html(this.template);
                    data.toJSON().content.forEach(function (element) {
                        items.push(element);
                        if ((element.item.id !== null && !(element.item.id in this.collection.productDiscount)) || this.collection.productDiscount[element.item.id] === 0) {
                            pids.push(element.item.id);
                            this.collection.productDiscount[element.item.id] = 0;
                        }
                    }, this);
                    var collection = this.collection;
                    var renderItems = this.renderItems;

                    if (pids.length > 0) {
                        collection.updateUrl(pids);
                        collection.fetch({
                            type: 'GET',
                            contentType: 'application/json',
                            async: false,
                            success: function (model, response) {
                                if (response.length > 0) {
                                    localStorage.setItem('featured-products', JSON.stringify(collection.setDiscountInItems(response, items)));
                                }
                                items.length = 0;
                            },
                            error: function (model, response) {
                                localStorage.setItem('featured-products', JSON.stringify(items));
                                items.length = 0;
                            }
                        });
                    } else localStorage.setItem('featured-products', JSON.stringify(collection.setDiscount(items)));
                    pids.length = 0;
                }.bind(this),
                error: function () {
                    this.$('#singleItem').append('No items to display');
                }
            });
            return response;
        },
        getProducts: function () {
            return localStorage.getItem('featured-products');
        },
        remove: function () {
            $(document).off('scroll');
            subViews.forEach(function (subView) {

                subView.remove();
                subView = null;
            });
            Backbone.View.prototype.remove.apply(this, arguments);
        },
        renderItems: function (items) {
            items.forEach(function (item) {
                var addItem = new AddItem({itemId: item.item.id});
                var itemView = new ItemView({model: addItem});
                subViews.push(itemView);
                this.$('#singleItem').append(itemView.render(item).el);
            });
        }
    });


    return FeaturedItemView;
});
