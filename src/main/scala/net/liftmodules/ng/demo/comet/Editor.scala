package net.liftmodules.ng.demo.comet

import net.liftmodules.ng.Angular.NgModel
import net.liftmodules.ng._
import net.liftweb.common.Empty
import net.liftweb.http.S
import net.liftweb.util.MarkdownParser

import scala.xml.NodeSeq

case class Input(mdtext:String) extends NgModel
case class Output(dom:String) extends NgModel
object Output {
  def apply(html:NodeSeq):Output = Output(html.toString)
}

class InputBinder
  extends SimpleNgModelBinder(
    "input",   // Bind to $scope.input
    Input("")  // Initial value
  ) with BindingToServer {
  override val onClientUpdate =
  { input:Input =>
    for {
      session <- S.session
      content <- MarkdownParser.parse(input.mdtext)
    } {
      session.sendCometActorMessage(
        "OutputBinder",
        Empty, // Comet actors optionally have names
        Output(content)
      )
    }
    input
  }
}

class OutputBinder
  extends SimpleNgModelBinder(
    "output",  // Bind to $scope.output
    Output(<div></div>)
  ) with BindingToClient with SessionScope