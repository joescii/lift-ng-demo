package net.liftmodules.ng.demo.snippet

import net.liftmodules.ng.Angular._
import net.liftweb.common.Empty

case class User(name:String, twitter:String, bday:String) extends NgModel

object User {
  def service = renderIfNotAlreadyDefined(
    angular.module("UserServices")
      .factory("UserService", jsObjFactory()
        .jsonCall("signup", (u:User) => {
          println(u)
          Empty
        })
      )
  )

}
