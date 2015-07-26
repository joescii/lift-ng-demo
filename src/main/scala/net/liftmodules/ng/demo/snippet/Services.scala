package net.liftmodules.ng.demo.snippet

object Services {
  def all =
    GitHubSvc.service ++
    Chat.service
}
