package net.liftmodules.ng.demo
package snippet

import net.liftmodules.ng.Angular. {renderIfNotAlreadyDefined, angular, jsObjFactory }
import net.liftweb.common.{ Full }

//import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object GitHub {
  def service = renderIfNotAlreadyDefined(
    angular.module("GitHubServices")
      .factory("GitHubService", jsObjFactory()
        .jsonCall("get", (github:String) => Full(lib.GitHub.accountFor(github)) )
      )
  )
}
