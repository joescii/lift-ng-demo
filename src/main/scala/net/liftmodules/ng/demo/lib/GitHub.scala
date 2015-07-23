package net.liftmodules.ng.demo.lib

import dispatch._, Defaults._

import rapture.json.jsonBackends.lift._
import rapture.json._
import rapture.core.modes.returnTry._

import FutureHelpers._

object GitHub {
  def accountFor(id:String):GitHub = {
    val user = Http(url(s"https://api.github.com/users/$id") OK as.String).flatMap(str => Json parse str)
    val avatar = user.flatMap(_.avatar_url.as[String])
    val followers = user.flatMap(_.followers.as[Int])

    val repoList:Future[List[Json]] = for {
      json <- user
      reposUrl <- tryToFuture(json.repos_url.as[String])
      response <- Http(url(reposUrl) OK as.String)
      reposJson <- Json.parse(response)
      repos <- reposJson.as[List[Json]]
    } yield { repos }
    val repos = repoList.map(_.size)
    val stars = repoList.map(_.foldLeft(0){ case (acc, repo) =>
      repo.stargazers_count.as[Int].toOption.getOrElse(0) + acc
    })
    val forks = repoList.map(_.foldLeft(0){ case (acc, repo) =>
      repo.forks.as[Int].toOption.getOrElse(0) + acc
    })

    GitHub(id, avatar, repos, followers, stars, forks)
  }
}

case class GitHub(
  id:String,
  avatar:Future[String],
  followers:Future[Int],
  repos:Future[Int],
  stars:Future[Int],
  forks:Future[Int]
)