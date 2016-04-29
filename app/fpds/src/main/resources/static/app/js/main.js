(function () {

  'use strict';

  require('angular');
  require('angular-route');
  require('angular-animate');
  require('angular-ui-bootstrap');
  
  var mainCtrl = require('./controllers/mainctrl');
  var searchCtrl = require('./controllers/searchResultsCtrl');
  var searchDetailsCtrl = require('./controllers/searchDetailsCtrl');
  var searchFactory = require('../../services/SearchFactory');
  var splitFilter = require('./controllers/splitFilter');

  angular.module('FpdsApp', ['ngRoute', 'ngAnimate','ui.bootstrap','ui.grid','ui.grid.resizeColumns', 'ui.grid.moveColumns','ui.grid.selection', 'ui.grid.exporter'])

  .config([
    '$routeProvider',
    function($routeProvider){
      // routes
      $routeProvider
        .when("/", {
          templateUrl: "./partials/partial1.html",
          controller: "MainController"
        })
        .when("/search/:vendor",{
          templateUrl: "./partials/partial2.html",
          controller: "SearchController",
        })
        .when("/searchDetails/:tranid",{
          templateUrl: "./partials/partial3.html",
          controller:"SearchDetailsController",
        })
        .otherwise({
           redirectTo: '/'
        });
    }
  ])

  .factory('SearchFactory',['$http',searchFactory])
  .filter('split',[splitFilter])
    //Load controller
  .controller('SearchDetailsController',['$scope','$http','$routeParams','SearchFactory',searchDetailsCtrl])  
  .controller('SearchController',['$scope','$http','$routeParams','SearchFactory',searchCtrl])
  .controller('MainController', ['$scope','$http','$location','SearchFactory', mainCtrl]);

}());