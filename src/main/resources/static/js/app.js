var bandsApp = angular.module("bandsApp", ["ui.router","angular.markdown"]);


bandsApp.config(function ($stateProvider, $urlRouterProvider, $sceDelegateProvider) {
    $urlRouterProvider.otherwise("home");

    $stateProvider
        .state('home', {
            url: '/home',
            templateUrl: URLS.partialsHome,
            controller: 'BandCtrl'
        })
        .state('bandSummary', {
            url: '/bandSummary/:bandId',
            templateUrl: URLS.partialsSummary,
            controller: 'BandViewCtrl'
        })
        .state('vcap', {
            url: '/vcap',
            templateUrl: URLS.partialsVcap,
            controller: 'MiscCtrl'
        })
        .state('about', {
            url: '/about',
            templateUrl: URLS.partialsAbout,
            controller: 'MiscCtrl'
        });

        $sceDelegateProvider.resourceUrlWhitelist([
            'self',
            "http://www.youtube.com/embed/**"
        ]);
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

    factory.getSummary = function (bandId) {
        return $http({
            url: URLS.getSummary,
            method: "GET",
            params: {bandId: bandId}
        });
    }

    factory.topPageView = function() {
        return $http.get(URLS.topPageView);
    }

    return factory;
});


bandsApp.controller("BandCtrl", function ($scope, bandsFactory) {
    function init() {
        $scope.statusmessage = "";
        $scope.errormessage = '';

        bandsFactory.getBandsList().success(function (data) {
            $scope.bands = data;
        }).error(function (data, status, errors, config) {
            $scope.setErrorMessage("Could not load list of bands!");
        });

        bandsFactory.topPageView().success(function(data) {
           $scope.pageviews = data;
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

    $scope.notify = function(message) {
        $scope.$apply(function() {
            $scope.pageviews = angular.fromJson(message.body);
        });
    };

    $scope.reconnect = function() {
        setTimeout($scope.initSockets, 10000);
    };

    $scope.initSockets = function() {
        $scope.socket={};
        $scope.socket.client = new SockJS('/pageviewsep');
        $scope.socket.stomp = Stomp.over($scope.socket.client);
        $scope.socket.stomp.connect({}, function() {
            $scope.socket.stomp.subscribe("/topic/pageview.all", $scope.notify);
        });
        $scope.socket.client.onclose = $scope.reconnect;
    };

    init();
    $scope.initSockets();
});

bandsApp.controller("MiscCtrl", function ($scope, miscFactory) {
    miscFactory.getVcapProperties().success(function (data) {
        $scope.vcapProperties = data;
    });
});

bandsApp.controller("BandViewCtrl", function ($scope, bandsFactory, $state, $stateParams) {

    function init() {
        $scope.loadBandSummary($stateParams.bandId);
        $scope.statusmessage = "";
        $scope.errormessage = '';
    }
    $scope.setErrorMessage = function (message) {
        $scope.errormessage = message;
        $scope.statusmessage = '';
    };

    $scope.setStatusMessage = function (message) {
        $scope.statusmessage = message;
        $scope.errormessage = '';
    };
    $scope.loadBandSummary = function (bandId) {
        bandsFactory.getSummary(bandId).success(function (data) {
            $scope.bandSummary = data;
        }).error(function (data, status, errors, config) {
            $scope.setErrorMessage("Could not load Summary of bands!");
        });
    };

    init();
});