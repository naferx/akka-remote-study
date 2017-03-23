
package com.github.naferx.serializer

import java.io.NotSerializableException

import akka.actor.ExtendedActorSystem
import akka.event.Logging
import akka.serialization.SerializerWithStringManifest
import com.github.naferx.messages.Messages
import com.github.naferx.messages.GreetingMessage

final class MessageSerializer(actorSystem: ExtendedActorSystem) extends SerializerWithStringManifest {
  import Messages._

  private val logger = Logging.getLogger(actorSystem, this)

  private val GreetingManifest = "G"

  override def identifier: Int = 2587

  override def manifest(obj: AnyRef): String = obj match {
    case _: Greeting ⇒ GreetingManifest
    case _ ⇒
      throw new IllegalArgumentException(s"Can't serialize object of type ${obj.getClass} in [${getClass.getName}]")
  }

  override def toBinary(obj: AnyRef): Array[Byte] = obj match {
    case g: Greeting ⇒
      logger.debug("Serializing.....")
      GreetingMessage(g.message).toByteArray
    case _ ⇒
      throw new IllegalArgumentException(s"Can't serialize object of type ${obj.getClass} in [${getClass.getName}]")
  }

  override def fromBinary(bytes: Array[Byte], manifest: String): AnyRef = {
    manifest match {
      case GreetingManifest ⇒
        logger.debug("Deserializing.....")
      val message = GreetingMessage.parseFrom(bytes)
      Greeting(message.msg)
      case _ ⇒ throw new NotSerializableException(
        s"Unimplemented deserialization of message with manifest [$manifest] in [${getClass.getName}")
    }
  }

}
