package net.liftmodules.ng.demo
package snippet

import lib._

import net.liftmodules.ng.Angular. {renderIfNotAlreadyDefined, angular, jsObjFactory }
import net.liftweb.common.{ Full }

object GitHubSvc {
  def service = renderIfNotAlreadyDefined(
    angular.module("GitHubModule")
      .factory("GitHub", jsObjFactory()
        .jsonCall("get", (github:String) => {
          val gh:GitHub = GitHub.accountFor(github)
          Full(gh)
        })
      )
    )
}
