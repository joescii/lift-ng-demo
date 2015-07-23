angular.module("UserProfileApp",
  ["UserServices"]
)
.controller("UserProfileController",
  ["$scope", "UserService",
    function($scope, service) {
      $scope.fetch = function() {
        service.profile().then(function(profile){
          profile.github.then(function(github){ $scope.github = github })
        });
      };
    }
  ]
)
;