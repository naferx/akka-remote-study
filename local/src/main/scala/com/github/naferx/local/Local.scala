
package com.github.naferx.local

import akka.actor._
import akka.routing.FromConfig
import com.typesafe.config.ConfigFactory
import com.github.naferx.remote.RemoteActor

import com.github.naferx.messages.Messages._
import com.github.naferx.configuration.CommonConfig

object Local extends App {

  val commonConfig = CommonConfig.defaultConfig
  val config = ConfigFactory.load("local").withFallback(commonConfig).resolve()
  implicit val system = ActorSystem("LocalSystem", config)
  val localActor = system.actorOf(Props[LocalActor], name = "LocalActor")  // the local actor
  localActor ! "START"                                                     // start the action

  val localActor2 = system.actorOf(Props[LocalActor])  // the local actor


}

class LocalActor extends Actor {

  //val remote = context.actorSelection("akka.tcp://remoteSystem@192.168.1.52:5150/user/RemoteActor")
  val remote = context.actorOf(FromConfig.props(Props[RemoteActor]), "remoteManager")
  var counter = 0

  def receive = {
    case "START" =>
        remote ! Greeting("Hello from the LocalActor")
    case Greeting(msg) =>
        println(s"LocalActor received message: '$msg'")
        if (counter < 5) {
            sender ! Greeting("Hello back to you")
            counter += 1
        }
  }
}
