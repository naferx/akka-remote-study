package com.github.naferx.remote

import akka.actor._
import com.typesafe.config.ConfigFactory
import com.github.naferx.messages.Messages._
import com.github.naferx.configuration.CommonConfig

object HelloRemote extends App  {
  val commonConfig = CommonConfig.defaultConfig
  val config = ConfigFactory.load("remote").withFallback(commonConfig).resolve()
  val system = ActorSystem("remoteSystem", config)
  //val remoteActor = system.actorOf(Props[RemoteActor], name = "remoteManager")
  //remoteActor ! "The RemoteActor is alive"


}

class RemoteActor extends Actor {
  def receive = {
    case Greeting(msg) =>
      val seq = Seq(1, 2, 3)
        println(s"RemoteActor received message '$msg'")
        sender ! Greeting("Hello from the RemoteActor")
  }
}
