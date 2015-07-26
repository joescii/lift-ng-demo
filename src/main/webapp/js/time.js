angular.module("TimeApp", [
  "ServerTimeServices",
  "ClientTimeServices"
])

.controller("TimeController", [
  "$scope",
  "ClientTimeService",
  "ServerTimeService",
    function($scope, clientSvc, serverSvc) {
      // ng-bind="client"
      $scope.client = "???";
      // ng-bind="server"
      $scope.server = "???";
      // ng-click="getClient()"
      $scope.getClient = function() {
        $scope.client = clientSvc.currentTime()
      };
      // ng-click="getServer()"
      $scope.getServer = function() {
        serverSvc.currentTime() // promise from server
          .then(function(timestamp) {
            $scope.server = timestamp;
          });
      };
}])
;