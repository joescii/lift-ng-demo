package net.liftmodules.ng.demo.lib

import dispatch._, Defaults._

import rapture.json.jsonBackends.lift._
import rapture.json._
import rapture.core.modes.returnTry._

import FutureHelpers._

object GitHub {
  def accountFor(id:String):GitHub = {
    val json = Http(url(s"https://api.github.com/users/$id") OK as.String).flatMap(str => Json parse str)
    val avatar:Future[String] = json.flatMap(_.avatar_url.as[String])

    GitHub(id, avatar)
  }
}

case class GitHub(id:String, avatar:Future[String])