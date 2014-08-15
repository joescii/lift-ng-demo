angular.module('DemoApp', []).

factory('TimeService', function() {
  return {
    time: function(){ return (new Date()).toTimeString(); }
  }
}).

factory('CounterService', ['$timeout', '$rootScope', function($timeout, $rootScope) {
  count = 0;
  increment = function() {
    $rootScope.$emit('count', count);
    count++;
    $timeout(function() { increment() }, 1000);
  };
  return {
    start: function() { increment(); }
  };
}]).

controller('Iceberg', ['$scope', '$rootScope', 'TimeService', 'CounterService', function($scope, $rootScope, timeSvc, countSvc) {
  $scope.getTime = function() {
    $scope.theTime = timeSvc.time();
  };

  $rootScope.$on('count', function(e, count) {
    $scope.theCount = count;
  });

  $scope.startCounter = countSvc.start;
}]);