angular.module("ClientTimeModule", [])
.factory("ClientTime", function() {
  return {
    currentTime: function() {
      return new Date()
    }
  }
})
;