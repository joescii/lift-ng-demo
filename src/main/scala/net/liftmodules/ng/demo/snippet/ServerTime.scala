package net.liftmodules.ng.demo.snippet

import java.text.DateFormat
import java.util.Date

import net.liftmodules.ng.Angular._
import net.liftweb.common.Full

import scala.xml.NodeSeq

object ServerTime {
  private def dateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL)
  private def timestamp =
    dateFormat.format(new Date())

  def atPageLoad(template:NodeSeq) =
    <div class="timestamp">
      {timestamp}
    </div>

  // lift-ng magic!!
  def service = renderIfNotAlreadyDefined(
    angular.module("ServerTimeServices")
    .factory("ServerTimeService", jsObjFactory()
      .jsonCall("currentTime", Full(timestamp))
  ))
}
