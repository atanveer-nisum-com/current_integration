define(function (require) {
    var $ = require('jQuery');
    var Bootstrap = require('Bootstrap');
    var ModelFactory = require('./factories/ModelFactory');
    var ViewFactory = require('./factories/ViewFactory');
    var BodyView = require('./views/BodyView');
    var ProductDetailsPageView = require('./product-details/views/ProductDetailsPageView');
    var LoginSignupView = require('./login-signup/views/LoginSignupView');
    var UserProfileView = require('./user-profile/views/UserProfileView');
    var PaymentView = require('./user-profile/views/PaymentView');
    var OrderHistoryView = require('./user-profile/views/OrderHistoryView');
    var AdressBookView = require('./user-profile/views/AddressBookView');
    var CategoryListPage = require('./category/category-list/views/CategoryListPage');
    var WishListView = require('./wishlist/views/WishListView');

    var models = Object.create(null),
        views = Object.create(null),
        bodyView = {};
    var renderPage = function (headerView, bodyView, footerView) {
        if ($('#app').children('#header').length > 0) $('#header').after(bodyView.render().el);
        else {
            $('#app').html(headerView.el);
            $('#app').append(bodyView.render().el);
            $('#app').append(footerView.el);
        }
    };
    var filterViews = function (view) {
        if (view instanceof Backbone.View) {
            if (view.options) {
                for (var key in view.options) {
                    if (view.options.hasOwnProperty(key)) {
                        key = null;
                    }
                }
            }
            view.remove();
            view = null;
        }
    };
    return {
        home: function () {
            models = ModelFactory.makeCategoryCollection()
                .makeFeaturedItem()
                .makeProductCategoryDiscountCollection()
                .getAllModels();
            views = ViewFactory.makeHeaderView()
                .makeFooterView()
                .makeCategoryView(models.categoryCollection)
                .makeFeaturedItemView(models.featuredItem, models.productCategoryDiscountCollection)
                .makeRecommendedItemsView(models.featuredItem, models.productCategoryDiscountCollection)
                .getAllViews();
            filterViews(bodyView);
            bodyView = new BodyView({
                categoryView: views.categoryView,
                featuredItemView: views.featuredItemView,
                recommendedItemsView: views.recommendedItemsView
            });
            renderPage(views.headerView, bodyView, views.footerView);
        },
        productDetails: function (productId) {
            models = ModelFactory.makeCategoryCollection()
                .makeFeaturedItem()
                .makeProductDetails(productId)
                .makeProductCategoryDiscountCollection()
                .getAllModels();
            views = ViewFactory.makeHeaderView()
                .makeFooterView()
                .makeCategoryView(models.categoryCollection)
                .makeProductDetailsView(models.productDetails)
                .makeRecommendedItemsView(models.featuredItem, models.productCategoryDiscountCollection)
                .getAllViews();
            filterViews(bodyView);
            bodyView = new ProductDetailsPageView({
                categoryView: views.categoryView,
                productDetailsView: views.productDetailsView,
                recommendedItemsView: views.recommendedItemsView
            });
            renderPage(views.headerView, bodyView, views.footerView);
        },
        loginSignup: function () {
            models = ModelFactory.makeLogin()
                .makeSignup()
                .makeCountryCollection()
                .makeStateCollection()
                .getAllModels();
            views = ViewFactory.makeHeaderView()
                .makeFooterView()
                .makeLoginView(models.login)
                .makeSignupView(models.signup, models.countryCollection, models.stateCollection)
                .getAllViews();
            filterViews(bodyView);
            bodyView = new LoginSignupView({
                loginView: views.loginView,
                signupView: views.signupView
            });
            renderPage(views.headerView, bodyView, views.footerView);
        },
        forgetPassword: function () {
            models = ModelFactory.makeForgetPassword()
                .getAllModels();
            views = ViewFactory.makeHeaderView()
                .makeFooterView()
                .makeForgetPasswordView(models.forgetPassword)
                .getAllViews();
            filterViews(bodyView);
            bodyView = views.forgetPasswordView;

            renderPage(views.headerView, bodyView, views.footerView);
        },
        resetPassword: function (resetToken) {
            models = ModelFactory.makeResetPassword(resetToken)
                .getAllModels();
            views = ViewFactory.makeHeaderView()
                .makeFooterView()
                .makeResetPasswordView(models.resetPassword)
                .getAllViews();
            filterViews(bodyView);
            bodyView = views.resetPasswordView;

            renderPage(views.headerView, bodyView, views.footerView);
        },
        shoppingcart: function () {
            models = ModelFactory.makeShoppingCart()
                .getAllModels();
            views = ViewFactory.makeHeaderView()
                .makeFooterView()
                .makeShoppingCartView(models.shoppingCart)
                .getAllViews();
            filterViews(bodyView);
            bodyView = views.shoppingCartView;

            renderPage(views.headerView, bodyView, views.footerView);
        },

        checkout: function () {
            models = ModelFactory.makeCheckout()
                .getAllModels();
            views = ViewFactory.makeHeaderView()
                .makeFooterView()
                .makeCheckoutView(models.checkout)
                .getAllViews();
            filterViews(bodyView);
            bodyView = views.checkoutView;

            renderPage(views.headerView, bodyView, views.footerView);
        },
        userProfile: function () {
            if (localStorage.getItem('isLogged') == 'true') {
                models = ModelFactory.makeStateCollection().makeCountryCollection().makeProfile().getAllModels();
                views = ViewFactory.makeHeaderView()
                    .makeFooterView().makeStateListView(models.stateCollection).makeCountryListView(models.countryCollection).getAllViews();
                filterViews(bodyView);
                bodyView = new UserProfileView({
                    model: models.profile,
                    countryListView: views.countryListView,
                    stateListView: views.stateListView
                });
                renderPage(views.headerView, bodyView, views.footerView);
            } else
                window.Router.navigate('/', {
                    trigger: true
                });
        },
        ProfilePaymentOptions: function () {
            if (localStorage.getItem('isLogged') == 'true') {
                models = ModelFactory.makePayment().getAllModels();
                views = ViewFactory.makeHeaderView()
                    .makeFooterView().getAllViews();
                filterViews(bodyView);
                bodyView = new PaymentView({
                    model: models.payment,
                });
                renderPage(views.headerView, bodyView, views.footerView);
            } else
                window.Router.navigate('/', {
                    trigger: true
                });
        }, ProfileOrderHistory: function () {
            if (localStorage.getItem('isLogged') == 'true') {
                models = ModelFactory.makeOrderHistory().getAllModels();
                views = ViewFactory.makeHeaderView()
                    .makeFooterView().getAllViews();
                filterViews(bodyView);
                bodyView = new OrderHistoryView({
                    model: models.orderHistory,
                });
                renderPage(views.headerView, bodyView, views.footerView);
            } else
                window.Router.navigate('/', {
                    trigger: true
                });
        }, ProfileAddressBook: function () {
            if (localStorage.getItem('isLogged') == 'true') {
                models = ModelFactory.makeStateCollection().makeCountryCollection().makeAdressBook().getAllModels();
                views = ViewFactory.makeHeaderView()
                    .makeFooterView().getAllViews();
                filterViews(bodyView);
                bodyView = new AdressBookView({
                    model: models.adressBook,
                    collection: {
                        countryCollection: models.countryCollection,
                        stateCollection: models.stateCollection,
                    }
                });
                renderPage(views.headerView, bodyView, views.footerView);
            } else
                window.Router.navigate('/', {
                    trigger: true
                });
        },
        getProductByCategory: function (categoryName) {
            models = ModelFactory.makeCategoryCollection()
                .makeCategoryListModel(categoryName)
                .getAllModels();
            views = ViewFactory.makeHeaderView()
                .makeFooterView()
                .makeCategoryView(models.categoryCollection)
                .makeCategoryListView(models.categoryListing)
                .getAllViews();
            filterViews(bodyView);
            bodyView = new CategoryListPage({
                categoryView: views.categoryView,
                categoryListView: views.categoryListView
            });
            renderPage(views.headerView, bodyView, views.footerView);

        },
        wishList: function () {
            if (localStorage.getItem('isLogged') == 'true') {
                models = ModelFactory.makeWishListModel().makeProductCategoryDiscountCollection().getAllModels();
                views = ViewFactory.makeHeaderView()
                    .makeFooterView().getAllViews();
                filterViews(bodyView);
                bodyView = new WishListView({
                    model: models.wishList, collection: models.productCategoryDiscountCollection
                });
                renderPage(views.headerView, bodyView, views.footerView);
            } else
                window.Router.navigate('/', {
                    trigger: true
                });
        },
    };
});
