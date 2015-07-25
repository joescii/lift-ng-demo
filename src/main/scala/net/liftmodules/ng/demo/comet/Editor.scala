package net.liftmodules.ng.demo.comet

import net.liftmodules.ng.Angular.NgModel
import net.liftmodules.ng._
import net.liftweb.common.Empty
import net.liftweb.http.S
import net.liftweb.util.MarkdownParser

case class Input(input:String) extends NgModel
case class Output(dom:String) extends NgModel

class InputBinder extends SimpleNgModelBinder("input", Input("")) with BindingToServer {
  override val onClientUpdate = { input:Input =>
    for {
      session <- S.session
      content <- MarkdownParser.parse(input.input)
    } {
      session.sendCometActorMessage("OutputBinder", Empty, Output(content.toString))
    }
    input
  }
}

class OutputBinder extends SimpleNgModelBinder("output", Output(<div>blah</div>.toString)) with BindingToClient with SessionScope