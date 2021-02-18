const myApp = angular.module('myApp');
const url = 'http://localhost:8080/api/';
myApp.controller("mainC", function ($scope, $http) {
    $scope.books = [];
    $scope.genres = [];

    $scope.init = function() {
        $scope.loadAll();
    }

    $scope.loadAll = function () {
        $http.get(url + '/book/findAll')
            .then(data => {
                $scope.books = data.data;
            });
        $http.get(url + '/genre/findAll')
            .then(data => {
                $scope.genres = data.data;
            });
    }
});


