angular.module("OssScoreApp",
  ["GitHubModule"]
)
.controller("OssScoreController",
  ["$scope", "$q", "GitHub",
    function($scope, $q, github){
      $scope.enteredId = "";

      $scope.submit = function() {
        $scope.id = "";
        $scope.avatar = "";
        $scope.followers = "";
        $scope.repos = "";
        $scope.stars = "";
        $scope.forks = "";
        $scope.total = "";

        github.get($scope.enteredId).then(function(profile){
          $scope.id = profile.id;
          profile.avatar.then(function(avatar){
            $scope.avatar = avatar });
          profile.followers.then(function(count){
            $scope.followers = count });
          profile.repos.then(function(count){
            $scope.repos = count });
          profile.stars.then(function(count){
            $scope.stars = count });
          profile.forks.then(function(count){
            $scope.forks = count });

          $q.all([profile.followers, profile.stars, profile.forks]).then(function(all){
            $scope.total = all[0] + all[1] + all[2];
          })
        });
        $scope.enteredId = "";
      };

      $scope.onKeypress = function(e) {
        if(e.keyCode === 13) $scope.submit();
      }
    }])
;