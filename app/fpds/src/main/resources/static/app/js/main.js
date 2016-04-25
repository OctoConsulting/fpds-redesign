(function () {

  'use strict';

  require('angular');
  require('angular-route');
  require('angular-animate');
  require('angular-ui-bootstrap');
  
  var mainCtrl = require('./controllers/mainctrl');
  var searchFactory = require('../../services/SearchFactory');

  angular.module('FpdsApp', ['ngRoute', 'ngAnimate','ui.bootstrap','ui.grid','ui.grid.resizeColumns', 'ui.grid.moveColumns'])

  .config([
    '$routeProvider',
    function($routeProvider){
      // routes
      $routeProvider
        .when("/", {
          templateUrl: "./partials/partial1.html",
          controller: "MainController"
        })
        .otherwise({
           redirectTo: '/'
        });
    }
  ])

  .factory('SearchFactory',['$http',searchFactory])
    //Load controller
  .controller('MainController', ['$scope','$http','SearchFactory', mainCtrl]);

  


}());