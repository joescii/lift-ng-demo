angular.module("ServerTimeApp",
  ["ServerTimeServices"]
)

.controller("ServerTimeController",
  ["$scope", "ServerTimeService",
    function($scope, service) {
      // ng-click for our button:
      $scope.fetch = function() {
        service.currentTime() // promise from server
          .then(function(timestamp) {
            $scope.latest = timestamp;
          }
        );
      };
}])
;