angular.module("UserSignupApp",
  ["UserServices"]
)
.controller("UserSignupController",
  ["$scope", "UserService",
    function($scope, service){
      $scope.submit = function() {
        service.signup($scope.github);
      };
}])
;