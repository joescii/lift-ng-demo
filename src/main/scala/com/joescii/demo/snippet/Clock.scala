package com.joescii.demo.snippet

import net.liftweb._
import util._
import http._
import js.JsCmds._
import Helpers._
import java.util.Date

class Clock {

  def time() = "div *" #> (new Date()).toString

  def getTime() = "button" #> SHtml.ajaxButton("Get Time", () => SetHtml("lift-time", <span>{new Date().toString}</span>) )

  def start() = "button" #> SHtml.ajaxButton("Start Clock", () => {
    for {
      session <- S.session
      actor <- session.findComet("ClockComet")
    } {
      actor ! "start"
    }

    Noop
  } )
}
