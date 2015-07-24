package net.liftmodules.ng.demo.comet

import net.liftmodules.ng.AngularActor
import net.liftweb.actor.LiftActor
import net.liftweb.http.{CometListener, ListenerManager}

object ChatServer extends LiftActor
  with ListenerManager {
  override def lowPriority = {
    case msg:String =>
      sendListenersMessage(msg)
  }
  override def createUpdate = ""
}

class ChatComet extends AngularActor
  with CometListener {
  override def registerWith = ChatServer
  override def lowPriority = {
    case msg:String =>
      scope.emit("new-message", msg)
  }
}