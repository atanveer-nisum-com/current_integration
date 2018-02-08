/**
 * client-side ETA
 * 
 * @author Touseef
 */
define(['jquery', 
        'underscore', 
        'backbone'], 
function($,  _, Backbone) {

    return {
        initialize : function() {
            Backbone.history.start();
        }
    };

});