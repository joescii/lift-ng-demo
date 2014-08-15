package com.joescii.demo.comet

import net.liftweb._
import http.CometActor
import util._
import Helpers._
import scala.xml.NodeSeq
import net.liftweb.http.js.JsCmds.SetHtml
import java.util.Date

class ClockComet extends CometActor {

  def render = NodeSeq.Empty

  override def lowPriority = {
    case "start" => tick
    case "tock" => partialUpdate(SetHtml("lift-clock", <span>{new Date().toString}</span>))
  }

  def tick:Unit = {
    this ! "tock"
    Schedule(() => tick, 1 second )
  }
}
