angular.module("ChatApp", ["ChatModule"])
.controller("ChatController",
  ["$scope", "ChatServer",
    function($scope, service){
      $scope.messages = [];
      $scope.$on("new-message",
        function(e, msg){
          $scope.messages.push(msg);
        }
      );

      $scope.sendChat = function() {
        service.send($scope.message);
        $scope.message = "";
      };
      $scope.onKeypress = function(e) {
        if(e.keyCode === 13) $scope.sendChat();
      }
    }
  ])
;