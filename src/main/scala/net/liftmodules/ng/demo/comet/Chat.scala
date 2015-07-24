package net.liftmodules.ng.demo.comet

import net.liftmodules.ng.AngularActor
import net.liftweb.actor.LiftActor
import net.liftweb.http.{CometListener, ListenerManager}

case class Messages(messages:List[String])

object ChatServer extends LiftActor with ListenerManager {
  private var messages = List.empty[String]
  override def createUpdate = Messages(messages)
  override def lowPriority = {
    case msg:String =>
      messages = (messages :+ msg) takeRight 15
      sendListenersMessage(msg)
  }
}

class ChatComet extends AngularActor with CometListener {
  override def registerWith = ChatServer
  override def lowPriority = {
    case Messages(msgs) => scope.assign("messages", msgs) // TODO: Doesn't seem to work??
    case msg:String     => scope.emit("new-message", msg)
  }
}