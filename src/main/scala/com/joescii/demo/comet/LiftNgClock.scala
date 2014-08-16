package com.joescii.demo.comet

import net.liftmodules.ng.{SimpleBindingActor, AngularActor}
import net.liftweb.util.Schedule
import net.liftweb.util.Helpers._
import java.util.Date
import net.liftmodules.ng.Angular.NgModel
import net.liftweb.http.S

class LiftNgClock extends AngularActor {
  override def lowPriority = {
    case "start" => tick
    case "tock" => for {
      session <- S.session
      actor <- session.findComet("ClockBinder")
    } { actor ! Timestamp(new Date().toString) }
  }

  def tick:Unit = {
    this ! "tock"
    Schedule(() => tick, 1 second )
  }

}

case class Timestamp(time:String) extends NgModel

class ClockBinder extends SimpleBindingActor[Timestamp] (
  "theClock",
  Timestamp(new Date().toString)
)