package net.liftmodules.ng.demo.snippet

object Services {
  def all =
    ServerTime.service ++
    GitHubSvc.service ++
    Chat.service
}
