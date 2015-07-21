angular.module("UserSignupApp",
  ["UserServices"]
)
.controller("UserSignupController",
  ["$scope", "UserService",
    function($scope, service){
      $scope.submit = function() {
        service.signup({
          twitter: $scope.twitter,
          github:  $scope.github
        });
      };
}])
;