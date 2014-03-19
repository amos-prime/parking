var Parking = angular.module('Parking',['ui.bootstrap'])

Parking.controller("TableController",["$http","$scope","$modal", "$log",function($http ,$scope,$modal, $log){

    $scope.update = function(){
        $http({
            method: 'GET',
            url: '/data',
            params: {timestamp : new Date().getTime()} //It's an ugly hack. IE need some unique param
        }).then(function(response){
            $scope.parkingInformation = response.data;
        });
    }

    $scope.booking = function(parking){
        $http({
            method: 'GET',
            url: '/booking/'+ parking.id + '/' + getName(),
            params: {timestamp : new Date().getTime()} //It's an ugly hack. IE need some unique param
        }).then($scope.update)

    }

    $scope.update();

    function getName() {
       return window.prompt("Enter temp parking holder name","Unknown")
    }

     $scope.holder = {
        name: 'Enter those lucky guy\'s name here',
     };

     $scope.open = function () {

        $modal.open({
            templateUrl: 'holder',
            backdrop: true,
            windowClass: 'modal',
            controller: function ($scope, $modalInstance, $log, holder) {
                $scope.holder = holder;
                $scope.submit = function () {
                    $log.log('Submitting');
                    $log.log(holder);
                    $modalInstance.dismiss('cancel');
                }
                $scope.cancel = function () {
                    $modalInstance.dismiss('cancel');
                };
            },
            resolve: {
                holder: function () {
                        return $scope.holder;
                    }
            }
        });
     };
}])