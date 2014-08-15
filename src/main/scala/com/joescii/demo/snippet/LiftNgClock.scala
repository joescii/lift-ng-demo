package com.joescii.demo.snippet

import net.liftmodules.ng.Angular._
import java.util.Date
import net.liftweb.common.{Empty, Box, Full}
import net.liftweb.http.S

class LiftNgClock {
  def render = renderIfNotAlreadyDefined(
    angular.module("LiftNgClock")
    .factory("LiftNgClockService", jsObjFactory()
      .jsonCall("time", Full(new Date().toString) )
      .jsonCall("start", {
      for {
        session <- S.session
        comet <- session.findComet("LiftNgClock")
      } { comet ! "start" }
      Empty
    })
  ))
}
