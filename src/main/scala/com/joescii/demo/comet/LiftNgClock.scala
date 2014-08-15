package com.joescii.demo.comet

import net.liftmodules.ng.AngularActor
import net.liftweb.util.Schedule
import net.liftweb.util.Helpers._
import java.util.Date

class LiftNgClock extends AngularActor {
  override def lowPriority = {
    case "start" => tick
    case "tock" => scope.emit("lift-ng-tick", new Date().toString)
  }

  def tick:Unit = {
    this ! "tock"
    Schedule(() => tick, 1 second )
  }

}
