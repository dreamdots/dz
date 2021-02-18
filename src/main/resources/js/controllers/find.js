const myApp = angular.module('myApp');
const url = 'http://localhost:8080/api/';
myApp.controller("findC", function ($scope, $http) {
    $scope.books = [];
    $scope.genres = [];
    $scope.isFindBook = true;

    $scope.filterOptions = {};

    $scope.findBook = function () {
        $http.post(url + '/book/find', $scope.filterOptions)
            .then(data => {
                $scope.books = data.data;
            });
        $scope.filterOptions = {};
    };

    $scope.findGenre = function () {
        $http.post(url + '/genre/find', $scope.filterOptions)
            .then(data => {
                $scope.genres = data.data;
            });
        $scope.filterOptions = {};
    };
});
