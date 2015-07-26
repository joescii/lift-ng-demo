angular.module("ClientTimeServices", [])
.factory("ClientTimeService", function() {
  return {
    currentTime: function() {
      return new Date()
    }
  }
})
;