define(function(require) {
	var Backbone = require('Backbone');

	var Session = Backbone.Model.extend({
      
        defaults: {
            token: '',
            isLogged: 'false'
        },
        
    });
	return Session;
});

