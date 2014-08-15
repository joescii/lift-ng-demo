angular.module('DemoApp', ['LiftNgClock'])

.factory('ClockService', ['$timeout', '$rootScope', function($timeout, $rootScope) {
  next = function() {
    $rootScope.$emit('tick', (new Date()).toTimeString());
    $timeout(function() { next() }, 1000);
  };
  return {
    start: function() { next(); },
    time: function(){ return (new Date()).toTimeString(); }
  };
}])

.controller('Iceberg', ['$scope', '$rootScope', 'ClockService', function($scope, $rootScope, clock) {
  $scope.timeAtLoad = (new Date()).toTimeString();

  $scope.getTime = function() {
    $scope.theTime = clock.time();
  };

  $rootScope.$on('tick', function(e, time) {
    $scope.theClock = time;
  });

  $scope.startClock = clock.start;
}])

.controller('LiftNgTime', ['$scope', 'LiftNgClockService', function($scope, clock) {
  $scope.getTime = function() {
    clock.time().then(function(time) {
      $scope.theTime = time;
    });
  };

  $scope.startClock = clock.start;

  $scope.$on('lift-ng-tick', function(e, time) {
    $scope.theClock = time;
  });
}])
;