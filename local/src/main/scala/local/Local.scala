
package local

import akka.actor._
import akka.routing.FromConfig
import com.typesafe.config.ConfigFactory


object Local extends App {
  val config = ConfigFactory.load("local")
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
        remote ! "Hello from the LocalActor"
    case msg: String =>
        println(s"LocalActor received message: '$msg'")
        if (counter < 5) {
            sender ! "Hello back to you"
            counter += 1
        }
  }
}
