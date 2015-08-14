angular.module("TimeApp", [
  "ClientTimeModule",
  "ServerTimeModule"
])

.controller("TimeController", [
  "$scope",
  "ClientTime",
  "ServerTime",
    function($scope, clientTime, serverTime) {
      // ng-bind="client"
      $scope.client = "???";
      // ng-bind="server"
      $scope.server = "???";
      // ng-click="getClient()"
      $scope.getClient = function() {
        $scope.client = clientTime.currentTime()
      };
      // ng-click="getServer()"
      $scope.getServer = function() {
        serverTime.currentTime() // promise from server
          .then(function(timestamp) {
            $scope.server = timestamp;
          });
      };
}])
;