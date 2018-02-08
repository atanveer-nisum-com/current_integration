require.config({
    shim: {
        'jQuery': {
            exports: '$'
        },
        'Underscore': {
            exports: '_'
        },
        'Backbone': {
            deps: ['Underscore', 'jQuery'],
            exports: 'Backbone'
        },
        'Handlebars': {
            exports: 'Handlebars'
        },
        'Bootstrap': {
            deps: ['jQuery'],
            exports: 'Bootstrap'
        },
        'Common': {
            exports: 'Common'
        },
        'ApplicationRouter': {
            deps: ['jQuery', 'Underscore', 'Backbone', 'Bootstrap']
        },
        'Text': {
            exports: 'text'
        },
        'Validation': {
            deps: ['Backbone'],
            exports: 'Validation'
        },
        'SlickCarousel': {
            deps: ['jQuery'],
            exports: 'SlickCarousel'
        },
        'Serializer': {
            exports: 'Serializer'
        },
        'payform': {

            exports: 'payform'
        }
    },
    paths: {
        jQuery: './../components/jquery/dist/jquery',
        Bootstrap: './../components/bootstrap/dist/js/bootstrap',
        Underscore: './../components/underscore/underscore',
        Backbone: './../components/backbone/backbone',
        Handlebars: './../components/handlebars/handlebars',
        text: './../components/text/text',
        Common: './core/common',
        Validation: './../components/backbone.validation/dist/backbone-validation',
        SlickCarousel: '../components/slick-carousel/slick/slick',
        Serializer: './../components/jquery-serialize-object/dist/jquery.serialize-object.min',
        payform: './../components/payform/dist/payform.min'
    }
});

require(['core/router', 'core/client', 'apps/session/models/Session', 'Backbone'], function (Router, client, Session, Backbone) {
    var app = {
        root: '/'
    };

    window.Router = new Router();
    client.setup(window, app);
    window.session = new Session();
    Backbone.eventAgg = _.extend({}, Backbone.Events);
    Backbone.history.start({
        pushState: true
    });
});
