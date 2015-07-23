package net.liftmodules.ng.demo.snippet

import net.liftmodules.ng.demo.lib._
import net.liftmodules.ng.Angular. {renderIfNotAlreadyDefined, angular, NgModel, jsObjFactory }
import net.liftweb.common.Empty
import net.liftweb.http.SessionVar

//import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

case class User(twitter:String, github:String) extends NgModel
case class UserProfile(github:GitHub)

object UserVar extends SessionVar[Option[User]](None)

object User {
  def service = renderIfNotAlreadyDefined(
    angular.module("UserServices")
      .factory("UserService", jsObjFactory()
        .jsonCall("signup", (u:User) => {
          UserVar(Some(u))
          Empty
        })
        .jsonCall("profile", UserVar.get.map( u => UserProfile(GitHub.accountFor(u.github)) ))
      )
  )
}
