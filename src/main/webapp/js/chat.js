angular.module("ChatApp", ["ChatServices"])
.controller("ChatController",
  ["$scope", "ChatService",
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