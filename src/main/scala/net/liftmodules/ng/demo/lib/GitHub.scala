package net.liftmodules.ng.demo.lib

import dispatch._, Defaults._

import rapture.json.jsonBackends.lift._
import rapture.json._
import rapture.core.modes.returnTry._

import FutureHelpers._

object GitHub {
  def accountFor(id:String):Future[GitHub] = {
    Http(url(s"https://api.github.com/users/$id") OK as.String).flatMap { str =>
      Json.parse(str).map { json =>
        GitHub(
          id,
          json.avatar_url.as[String].toOption
        )
      }
    }
  }
}

case class GitHub(id:String, avatar:Option[String])