
package com.github.naferx

import akka.actor._
import com.typesafe.config.ConfigFactory


object Local extends App {
  val config = ConfigFactory.load("local")
  implicit val system = ActorSystem("LocalSystem", config)
  val localActor = system.actorOf(Props[LocalActor], name = "LocalActor")  // the local actor
  localActor ! "START"                                                     // start the action

}

class LocalActor extends Actor {

  //val remote = context.actorSelection("akka.tcp://HelloRemoteSystem@127.0.0.1:5150/user/RemoteActor")
  val remote = context.actorSelection("akka.tcp://HelloRemoteSystem@192.168.1.56:5150/user/RemoteActor")
  var counter = 0

  def receive = {
    case "START" =>
        remote ! "Hello from the LocalActor"
    case msg: String =>
        println(s"LocalActor received message: '$msg'")
        if (counter < 5) {
            sender ! "Hello back to you"
            counter += 1
        }
  }
}
