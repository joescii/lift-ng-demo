package net.liftmodules.ng.demo.snippet

import net.liftmodules.ng.Angular. {renderIfNotAlreadyDefined, angular, NgModel, jsObjFactory }
import net.liftweb.common.Empty
import dispatch._, Defaults._

case class User(twitter:String, github:String) extends NgModel

object User {
  def service = renderIfNotAlreadyDefined(
    angular.module("UserServices")
      .factory("UserService", jsObjFactory()
        .jsonCall("signup", (u:User) => {
          githubAvatar(u.github).foreach(println)
          Empty
        })
      )
  )

  def githubAvatar(id:String) = Http(
    url(s"https://api.github.com/users/$id") OK as.String
  )
}
