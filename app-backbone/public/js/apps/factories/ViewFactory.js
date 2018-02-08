define(function (require) {

    // require all views here
    var HeaderView = require('../views/HeaderView');
    var FooterView = require('../views/FooterView');
    var CategoryView = require('../category/views/CategoryView');
    var FeaturedItemView = require('../featured-items/views/FeaturedItemView');
    var RecommendedItemsView = require('../recommended-items/views/RecommendedItemsView');
    var ProductDetailsView = require('../product-details/views/ProductDetailsView');
    var LoginView = require('../login-signup/login/views/LoginView');
    var forgetPasswordView = require('../forget-password/views/forgetPassword');
    var resetPasswordView = require('../reset-password/views/resetPassword');
    var shoppingCartView = require('../shopping-cart/views/shoppingCartView');
    var checkoutView = require('../checkout/views/checkoutView');
    var SignupView = require('../login-signup/signup/views/SignupView');
    var CountryListView = require('../user-profile/views/CountryListView');
    var StateListView = require('../user-profile/views/StateListView');
    var CategoryListView = require('../category/category-list/views/CategoryListView');

    // two empty objects to persist views
    var views = Object.create(null);
    var temp = Object.create(null);

    var ViewFactory = (function () {
        return {
            makeHeaderView: function () {
                if (!('headerView' in views)) {
                    temp.headerView = new HeaderView().render();
                } else temp.headerView = true;
                return this;
            },
            makeFooterView: function () {
                if (!('footerView' in views)) {
                    temp.footerView = new FooterView().render();
                } else temp.footerView = true;
                return this;
            },
            makeCategoryView: function (categoryCollection) {
                if (!('categoryView' in views)) {
                    temp.categoryView = new CategoryView({
                        collection: categoryCollection
                    }).render();
                } else temp.categoryView = true;
                return this;
            },
            makeRecommendedItemsView: function (featuredItem, productCategoryDiscount) {
                if (!('recommendedItemsView' in views)) {
                    temp.recommendedItemsView = new RecommendedItemsView({
                        model: featuredItem,
                        collection: productCategoryDiscount
                    }).render();
                } else temp.recommendedItemsView = true;
                return this;
            },
            makeFeaturedItemView: function (featuredItem, productCategoryDiscount) {
                if (!('featuredItemView' in views)) {
                    temp.featuredItemView = new FeaturedItemView({
                        model: featuredItem,
                        collection: productCategoryDiscount
                    }).render();
                } else temp.featuredItemView = true;
                return this;
            },
            makeProductDetailsView: function (productDetails) {
                if (!('productDetailsView' in views)) {
                    temp.productDetailsView = new ProductDetailsView({model: productDetails}).render();
                }
                else if (views['productDetailsView'].model !== productDetails) {

                    views['productDetailsView'].model = productDetails;
                    views['productDetailsView'].render();
                    temp.productDetailsView = true;
                }
                else temp.productDetailsView = true;
                return this;
            },
            makeLoginView: function (login) {
                if (!('loginView' in views)) {
                    temp.loginView = new LoginView({model: login}).render();
                } else temp.loginView = true;
                return this;
            },
            makeForgetPasswordView: function (ForgetPassword) {
                if (!('forgetPasswordView' in views)) {
                    temp.forgetPasswordView = new forgetPasswordView({model: ForgetPassword}).render();
                } else temp.forgetPasswordView = true;
                return this;
            },
            makeResetPasswordView: function (ResetPassword) {
                if (!('resetPasswordView' in views)) {
                    temp.resetPasswordView = new resetPasswordView({model: ResetPassword}).render();
                } else temp.resetPasswordView = true;
                return this;
            },
            makeShoppingCartView: function (ShoppingCart) {
                if (!('resetPasswordView' in views)) {
                    temp.shoppingCartView = new shoppingCartView({model: ShoppingCart}).render();
                } else temp.shoppingCartView = true;
                return this;
            },
            makeCheckoutView: function (checkout) {
                if (!('checkoutView' in views)) {
                    temp.checkoutView = new checkoutView({model: checkout}).render();
                } else temp.checkoutView = true;
                return this;
            },
            makeSignupView: function (signup, countryCollection, stateCollection) {
                if (!('signupView' in views)) {
                    temp.signupView = new SignupView({
                        model: signup, collection: {
                            countryCollection: countryCollection,
                            stateCollection: stateCollection
                        }
                    }).render();
                } else temp.signupView = true;
                return this;
            },
            makeStateListView: function (stateListCollection) {
                if (!('stateListView' in views)) {
                    temp.stateListView = new StateListView({collection: stateListCollection}).render();
                } else temp.stateListView = true;
                return this;
            },
            makeCountryListView: function (countryListCollection) {
                if (!('countryListView' in views)) {
                    temp.countryListView = new CountryListView({collection: countryListCollection}).render();
                } else temp.countryListView = true;
                return this;
            },
            makeCategoryListView: function (categoryListing) {
                if (!('categoryListView' in views)) {
                    temp.categoryListView = new CategoryListView({model: categoryListing}).render();
                }
                else if (views['categoryListView'].model !== categoryListing) {

                    views['categoryListView'].model = categoryListing;
                    views['categoryListView'].render();
                    temp.categoryListView = true;
                }
                else temp.categoryListView = true;
                return this;
            },

            /**
             * Define all make{ViewName} functions above this function to increase readability and consistency.
             */
            getAllViews: function () {
                for (var view in views) {
                    if (!(view in temp)) {
                        views[view].remove();
                        views[view] = null;
                        delete views[view];
                    } else delete temp[view];
                }
                for (view in temp) views[view] = temp[view];
                if (!temp) views = temp;
                temp = Object.create(null);
                return views;
            }
        };
    })();
    return ViewFactory;
});