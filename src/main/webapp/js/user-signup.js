angular.module("UserSignupApp",
  ["UserServices"]
)
.controller("UserSignupController",
  ["$scope", "UserService",
    function($scope, service){
      $scope.submit = function() {
        service.signup($scope.github);
      };

      $scope.onKeypress = function(e) {
        if(e.keyCode === 13) $scope.submit();
      }
}])
;