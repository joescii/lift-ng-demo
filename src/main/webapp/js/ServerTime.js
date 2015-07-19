angular.module("ServerTimeApp", ["ServerTimeServices"])
.controller("ServerTimeController", ["$scope", "ServerTimeService", function($scope, service){
  $scope.fetch = function() {
    service.currentTime().then(function(timestamp) {
      $scope.latest = timestamp;
    });
  };
}])
;