package com.github.naferx

import akka.actor._
import com.typesafe.config.ConfigFactory

object HelloRemote extends App  {
  val config = ConfigFactory.load("hello-remote")
  val system = ActorSystem("HelloRemoteSystem", config)
  val remoteActor = system.actorOf(Props[RemoteActor], name = "RemoteActor")
  remoteActor ! "The RemoteActor is alive"
}

class RemoteActor extends Actor {
  def receive = {
    case msg: String =>
        println(s"RemoteActor received message '$msg'")
        sender ! "Hello from the RemoteActor"
  }
}