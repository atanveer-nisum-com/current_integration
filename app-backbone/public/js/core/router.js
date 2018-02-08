define(function (require) {
    var Backbone = require("Backbone");

    var Router = Backbone.Router.extend({
        routes: {
            "": "home",
            "products/category/:name": "getProductByCategory",
            "products/:param": "productDetails",
            "userprofile": "userProfile",
            "userprofile/paymentoptions": "paymentOptions",
            "userprofile/orderhistory": "orderHistory",
            "userprofile/addressbook": "addressBook",
            "loginsignup": "loginSignup",
            "wishlists": "wishList",
            "forgetpassword": "forgetPassword",
            "resetPassword/:resetToken": "resetPassword",
            "shoppingcart": "shoppingcart",
            "checkout": "checkout"
        },

        home: function () {
            require("./../apps/app").home();
        },
        productDetails: function (param) {
            require("./../apps/app").productDetails(param);
        },
        getProductByCategory: function (name) {
            require("./../apps/app").getProductByCategory(name);
        },
        userProfile: function () {
            require("./../apps/app").userProfile();
        },
        paymentOptions: function () {
            require("./../apps/app").ProfilePaymentOptions();
        },
        orderHistory: function () {
            require("./../apps/app").ProfileOrderHistory();
        },
        addressBook: function () {
            require("./../apps/app").ProfileAddressBook();
        },
        loginSignup: function () {
            require("./../apps/app").loginSignup();
        },
        wishList: function () {
            require("./../apps/app").wishList();
        },
        forgetPassword: function () {
            require("./../apps/app").forgetPassword();
        },
        resetPassword: function (resetToken) {
            require("./../apps/app").resetPassword(resetToken);
        },
        shoppingcart: function () {
            require("./../apps/app").shoppingcart();
        },
        checkout: function () {
            require("./../apps/app").checkout();
        }
    });

    return Router;
});
