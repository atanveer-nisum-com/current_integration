define(function (require) {

    // require all models and collections here
    var Category = require('../category/models/Category');
    var CategoryCollection = require('../category/collections/CategoryCollection');
    var FeaturedItem = require('../featured-items/models/FeaturedItem');
    var ProductDetails = require('../product-details/models/ProductDetails');
    var Signup = require('../login-signup/signup/models/Signup');
    var Login = require('../login-signup/login/models/Login');
    var forgetPassword = require('../forget-password/models/forgetPassword');
    var resetPassword = require('../reset-password/models/resetPassword');
    var shoppingCart = require('../shopping-cart/models/shoppingCart');
    var checkout = require('../checkout/models/checkout');
    var CountryCollection = require('../login-signup/signup/collections/CountryCollection');
    var StateCollection = require('../login-signup/signup/collections/StateCollection');
    var Profile = require('../user-profile/models/Profile');
    var Payment = require('../user-profile/models/Payment');
    var OrderHistory = require('../user-profile/models/OrderHistory');
    var AdressBook = require('../user-profile/models/AddressBook');
    var CategoryListing = require('../category/category-list/models/CategoryList');
    var WishList = require('../wishlist/models/WishList');
    var CategoryDiscountList = require('../discount/category-discount/collections/CategoryDiscountList');
    var ProductDiscountList = require('../discount/product-discount/collections/ProductDiscountList');
    var ProductCategoryDiscountList = require('../discount/product-category-discount/collections/ProductCategoryDiscountList');

    // empty objects to persist models
    var models = Object.create(null),
        temp = Object.create(null);

    var ModelFactory = (function () {
        return {
            makeCategoryCollection: function () {
                if (!('categoryCollection' in models)) {
                    temp.categoryCollection = new CategoryCollection();
                } else temp.categoryCollection = true;
                return this;
            },
            makeFeaturedItem: function () {
                if (!('featuredItem' in models)) {
                    temp.featuredItem = new FeaturedItem();
                } else temp.featuredItem = true;
                return this;
            },
            makeProductDetails: function (productId) {
                if (!('productDetails' in models) || models['productDetails'].productId !== productId) {
                    models['productDetails'] = null;
                    delete models['productDetails'];
                    temp.productDetails = new ProductDetails({productId: productId});
                }
                else temp.productDetails = true;
                return this;
            },
            makeLogin: function () {
                if (!('login' in models)) {
                    temp.login = new Login();
                } else temp.login = true;
                return this;
            },
            makeForgetPassword: function () {
                if (!('forgetPassword' in models)) {
                    temp.forgetPassword = new forgetPassword();
                } else temp.forgetPassword = true;
                return this;
            },
            makeResetPassword: function (resetToken) {
                if (!('resetPassword' in models)) {
                    temp.resetPassword = new resetPassword({
                        resetToken: resetToken
                    });
                } else temp.resetPassword = true;
                return this;
            },
            makeShoppingCart: function () {
                if (!('shoppingCart' in models)) {
                    temp.shoppingCart = new shoppingCart();
                } else temp.shoppingCart = true;
                return this;
            },
            makeCheckout: function () {
                if (!('checkout' in models)) {
                    temp.checkout = new checkout();
                } else temp.checkout = true;
                return this;
            },
            makeSignup: function () {
                if (!('signup' in models)) {
                    temp.signup = new Signup();
                } else temp.signup = true;
                return this;
            },
            makeCountryCollection: function () {
                if (!('countryCollection' in models)) {
                    temp.countryCollection = new CountryCollection();
                } else temp.countryCollection = true;
                return this;
            },
            makeStateCollection: function () {
                if (!('stateCollection' in models)) {
                    temp.stateCollection = new StateCollection();
                } else temp.stateCollection = true;
                return this;
            },
            makeProfile: function () {
                if (!('profile' in models)) {
                    temp.profile = new Profile();
                } else temp.profile = true;
                return this;
            },
            makePayment: function () {
                if (!('payment' in models)) {
                    temp.payment = new Payment();
                } else temp.payment = true;
                return this;
            },
            makeOrderHistory: function () {
                if (!('orderHistory' in models)) {
                    temp.orderHistory = new OrderHistory();
                } else temp.orderHistory = true;
                return this;
            },
            makeAdressBook: function () {
                if (!('adressBook' in models)) {
                    temp.adressBook = new AdressBook();
                } else temp.adressBook = true;
                return this;
            },
            makeCategoryListModel: function (categoryName) {
                if (!('categoryListing' in models) || models['categoryListing'].categoryName !== categoryName) {
                    models['categoryListing'] = null;
                    delete models['categoryListing'];
                    temp.categoryListing = new CategoryListing({categoryName: categoryName});
                }
                else temp.categoryListing = true;
                return this;

            },
            makeWishListModel: function () {
                if (!('wishList' in models)) {
                    temp.wishList = new WishList();
                } else temp.wishList = true;
                return this;
            },
            makeCategoryDiscountCollection: function () {
                if (!('categoryDiscountCollection' in models)) {
                    temp.categoryDiscountCollection = new CategoryDiscountList();
                } else temp.categoryDiscountCollection = true;
                return this;
            },
            makeProductDiscountCollection: function () {
                if (!('productDiscountCollection' in models)) temp.productDiscountCollection = new ProductDiscountList();
                else temp.productDiscountCollection = true;
                return this;
            },
            makeProductCategoryDiscountCollection: function () {
                if (!('productCategoryDiscountCollection' in models)) temp.productCategoryDiscountCollection = new ProductCategoryDiscountList();
                else temp.productCategoryDiscountCollection = true;
                return this;
            },
            /**
             * Define all make{ModelName} functions above this function to increase readability and consistency.
             */
            getAllModels: function () {
                for (var model in models) {
                    if (!(model in temp)) {
                        if (model instanceof Backbone.Collection) {
                            var m;
                            while (m = model.pop()) m = null;
                        }
                        models[model] = null;
                        delete models[model];
                    } else delete temp[model];
                }
                for (model in temp) models[model] = temp[model];
                if (!temp) models = temp;
                temp = Object.create(null);
                return models;
            }
        };
    })();
    return ModelFactory;
});