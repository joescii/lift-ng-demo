package net.liftmodules.ng.demo.snippet

import net.liftweb.http.{LiftRules, S}
import net.liftweb.util.Helpers._

import scala.xml.NodeSeq

object Code {
  private def src = (for {
    name <- S.attr("src")
    code <- LiftRules.loadResourceAsString(name)
  } yield { code })
    .openOr("// Source not found!!!")

  private def lang = S.attr("src").map(_.split("\\.").last match {
    case "js" => "javascript"
    case other => other
  }).openOr("")

  def render(in:NodeSeq):NodeSeq =
    <pre>
      <code class={lang}>
        <div>{src}</div>
      </code>
    </pre>
}