package net.liftmodules.ng.demo.lib

object FutureHelpers {
  import scala.concurrent._
  import scala.util._

  implicit def tryToFuture[T](t:Try[T]):Future[T] = {
    t match{
      case Success(s) => Future.successful(s)
      case Failure(ex) => Future.failed(ex)
    }
  }
}