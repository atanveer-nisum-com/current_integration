/**
 * dependents
 * 
 * @author Touseef
 */

require.config({
	paths : {
		jquery :       '../assets/libs/jquery/dist/jquery.min',
		underscore :   '../assets/libs/underscore-amd/underscore-min',
		backbone :     '../assets/libs/backbone-amd/backbone-min',

        templates:     '../templates',
	}
});

require(['app'], function(app){
	app.initialize();
})