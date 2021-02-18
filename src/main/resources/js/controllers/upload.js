const myApp = angular.module('myApp');
const url = 'http://localhost:8080/api/';
myApp.controller("uploadC", function ($scope, $http) {
    $scope.book = {};
    $scope.genre = {};
    $scope.isLoadBook = true;

    $scope.addBook = function () {
        console.log($scope.book);
        $http.post(url + 'book/add', $scope.book);
        $scope.book = {};
    };

    $scope.addGenre = function () {
        console.log($scope.genre);
        $http.post(url + 'genre/add', $scope.genre);
        $scope.genre = {};
    };
});
