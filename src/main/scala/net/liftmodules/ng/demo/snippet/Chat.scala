package net.liftmodules.ng.demo.snippet

import net.liftmodules.ng.Angular._
import net.liftmodules.ng.demo.comet.ChatServer
import net.liftweb.common.Empty

object Chat {
  def service = renderIfNotAlreadyDefined(
    angular.module("ChatModule")
    .factory("ChatServer", jsObjFactory()
      .jsonCall("send", (chat:String) => {
        ChatServer ! chat
        Empty
      })
    )
  )
}
