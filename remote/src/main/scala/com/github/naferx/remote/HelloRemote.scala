package com.github.naferx.remote

import akka.actor._
import com.typesafe.config.ConfigFactory

object HelloRemote extends App  {
  val config = ConfigFactory.load("remote")
  val system = ActorSystem("remoteSystem", config)
  //val remoteActor = system.actorOf(Props[RemoteActor], name = "remoteManager")
  //remoteActor ! "The RemoteActor is alive"


}

class RemoteActor extends Actor {
  def receive = {
    case msg: String =>
      val seq = Seq(1, 2, 3)
        println(s"RemoteActor received message '$msg'")
        sender ! "Hello from the RemoteActor"
  }
}
