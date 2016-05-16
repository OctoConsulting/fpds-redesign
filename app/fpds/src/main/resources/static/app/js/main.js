(function () {

  'use strict';

  require('angular');
  require('angular-route');
  require('angular-animate');
  require('angular-ui-bootstrap');
  
  var mainCtrl = require('./controllers/mainctrl');
  var searchCtrl = require('./controllers/searchResultsCtrl');
  var searchDetailsCtrl = require('./controllers/searchDetailsCtrl');
  var viewContractsCtrl = require('./controllers/viewContractsCtrl');
  var viewIdvCtrl = require('./controllers/viewIdvCtrl');
  var searchFactory = require('../../services/SearchFactory');
  var splitFilter = require('./controllers/splitFilter');
  var enterDirective = require('./controllers/enterDirective');
  var tabsCtrl = require('./controllers/tabsCtrl');

  angular.module('FpdsApp', ['ngRoute', 'ngAnimate','ui.bootstrap','ui.grid','ui.grid.resizeColumns', 'ui.grid.moveColumns','ui.grid.selection', 'ui.grid.exporter'])

  .config([
    '$routeProvider',
    function($routeProvider){
      // routes
      $routeProvider
        .when("/", {
          templateUrl: "./partials/landingPage1.html",
          controller: "MainController"
        })
        .when("/search/:vendor",{
          templateUrl: "./partials/partial2.html",
          controller: "SearchController",
        })
        .when("/searchDetails/:tranid/:pageNo/:query",{
          templateUrl: "./partials/partial3.html",
          controller:"SearchDetailsController",
        })
        .when("/viewContracts/:contractId",{
          templateUrl: "./partials/partial4.html",
          controller:"ViewContractsController", 
        })
        .when("/viewIdv/:idvId",{
          templateUrl: "./partials/partial5.html",
          controller:"ViewIdvController",
        })
        .when("/reports/",{
          templateUrl: "./partials/partial-reports.html",
          controller:"TabsController",
        })
        .otherwise({
           redirectTo: '/'
        });
    }
  ])

  .factory('SearchFactory',['$http',searchFactory])
  .filter('split',[splitFilter])
  .directive('ngEnter',[enterDirective])
    //Load controller
  .controller('SearchDetailsController',['$scope','$http','$routeParams','$location','$anchorScroll','SearchFactory',searchDetailsCtrl])  
  .controller('SearchController',['$scope','$http','$location','$routeParams','$anchorScroll','SearchFactory',searchCtrl])
  .controller('ViewContractsController',['$scope','$http','$location','$routeParams','$anchorScroll','SearchFactory',viewContractsCtrl])
  .controller('ViewIdvController',['$scope','$http','$location','$routeParams','$anchorScroll','SearchFactory',viewIdvCtrl])
  .controller('TabsController', ['$scope', tabsCtrl])
  .controller('MainController', ['$scope','$http','$location','SearchFactory', mainCtrl]);

}());