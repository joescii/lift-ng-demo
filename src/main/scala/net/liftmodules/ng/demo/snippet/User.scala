package net.liftmodules.ng.demo.snippet

import net.liftmodules.ng.demo.lib._
import net.liftmodules.ng.Angular. {renderIfNotAlreadyDefined, angular, NgModel, jsObjFactory }
import net.liftweb.common.Empty

import scala.concurrent.ExecutionContext.Implicits.global

case class User(twitter:String, github:String) extends NgModel

object User {
  def service = renderIfNotAlreadyDefined(
    angular.module("UserServices")
      .factory("UserService", jsObjFactory()
        .jsonCall("signup", (u:User) => {
          GitHub.accountFor(u.github).foreach(println)
          Empty
        })
      )
  )
}
