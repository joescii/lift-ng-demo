package net.liftmodules.ng.demo.snippet

import net.liftweb.http.{LiftRules, S}
import net.liftweb.util.Helpers._

import scala.util.Try
import scala.xml.NodeSeq

object Code {
  private val newline = System.getProperty("line.separator")

  private def src = (for {
    name <- S.attr("src")
    code <- LiftRules.loadResourceAsString(name)
  } yield {
    firstLine.map { first =>
      val last = lastLine.getOrElse(first)
      val lines = code.split("(?s)\r?\n")
      lines
        .drop(first - 1)
        .take(last - first + 1)
        .mkString(newline)
    }.getOrElse(code)
  }).openOr("// Source not found!!!")

  private def lang = S.attr("src").map(_.split("\\.").last match {
    case "js" => "javascript"
    case other => other
  }).openOr("")

  private def lineParse(which:Array[String] => String) = S.attr("l").toOption.flatMap( l =>
    Try(which(l.split("-")).toInt).toOption
  )
  private def firstLine = lineParse(_.head)
  private def lastLine  = lineParse(_.last)

  def render(in:NodeSeq):NodeSeq =
    <pre>
      <code class={lang}>
        <div>{src}</div>
      </code>
    </pre>
}