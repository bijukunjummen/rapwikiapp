var bandsApp = angular.module("bandsApp", ["ui.router"]);

bandsApp.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("home");

    $stateProvider
        .state('home', {
            url: '/home',
            templateUrl: URLS.partialsHome,
            controller: 'HomeCtrl'
        })
        .state('vcap', {
            url: '/vcap',
            templateUrl: URLS.partialsVcap,
            controller: 'MiscCtrl'
        });
});


bandsApp.factory("miscFactory", function ($http) {
    var factory = {};
    factory.getVcapProperties = function () {
        return $http.get(URLS.vcapProperties);
    };

    return factory;
});

bandsApp.factory("bandsFactory", function ($http) {
    var factory = {};
    factory.getBandsList = function () {
        return $http.get(URLS.getBandsList);
    };

    return factory;
});


bandsApp.controller("HomeCtrl", function ($scope, bandsFactory) {
    function init() {
        $scope.statusmessage = "";
        $scope.errormessage = '';

        bandsFactory.getBandsList().success(function (data) {
            $scope.bands = data;
        }).error(function(data, status, errors, config) {
            $scope.setErrorMessage("Could not load list of bands! "  + errors) ;
        });
    }

    $scope.setErrorMessage = function (message) {
        $scope.errormessage = message;
        $scope.statusmessage = '';
    };

    $scope.setStatusMessage = function (message) {
        $scope.statusmessage = message;
        $scope.errormessage = '';
    };

    init();
});

bandsApp.controller("MiscCtrl", function ($scope, miscFactory) {
    miscFactory.getVcapProperties().success(function (data) {
        $scope.vcapProperties = data;
    });
});