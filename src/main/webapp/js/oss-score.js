angular.module("OssScoreApp",
  ["GitHubServices"]
)
.controller("OssScoreController",
  ["$scope", "GitHubService",
    function($scope, service){
      $scope.enteredId = "";

      $scope.submit = function() {
        service.get($scope.enteredId).then(function(profile){
          $scope.id = profile.id;
          profile.avatar.then(function(avatar){ $scope.avatar = avatar });
          profile.followers.then(function(count){ $scope.followers = count });
          profile.stars.then(function(count){ $scope.stars = count });
        });
        $scope.enteredId = "";
      };

      $scope.onKeypress = function(e) {
        if(e.keyCode === 13) $scope.submit();
      }
    }])
;