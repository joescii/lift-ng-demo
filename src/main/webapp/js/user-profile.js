angular.module("UserProfileApp",
  ["UserServices"]
)
.controller("UserProfileController",
  ["$scope", "UserService",
    function($scope, service) {
      $scope.fetch = function() {
        service.profile().then(function(profile){
          $scope.github = { id: profile.github.id };
          profile.github.avatar.then(function(avatar){ $scope.github.avatar = avatar });
          profile.github.followers.then(function(count){ $scope.github.followers = count });
          profile.github.stars.then(function(count){ $scope.github.stars = count });
        });
      };
    }
  ]
)
;