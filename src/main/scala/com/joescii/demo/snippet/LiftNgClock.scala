package com.joescii.demo.snippet

import net.liftmodules.ng.Angular._
import java.util.Date
import net.liftweb.common.{Empty, Box, Full}
import net.liftweb.http.S
import com.joescii.demo.comet.Timestamp
import net.liftweb.util.Schedule
import net.liftweb.util.Helpers._

class LiftNgClock {
  def render = renderIfNotAlreadyDefined(
    angular.module("LiftNgClock")
    .factory("LiftNgClockService", jsObjFactory()
      .jsonCall("time", Full(new Date().toString) )
      .jsonCall("start", start)
  ))

  def start = S.session map { session =>

    def tock:Unit = {
      for {
        actor <- session.findComet("ClockBinder")
      } { actor ! Timestamp(new Date().toString) }

      Schedule(() => tock, 1 second)
    }

    tock
    Empty
  }
}
