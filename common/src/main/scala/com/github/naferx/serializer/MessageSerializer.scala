
package com.github.naferx.serializer

import akka.serialization.SerializerWithStringManifest
import com.github.naferx.messages.Messages
import com.github.naferx.messages.GreetingMessage

final class MessageSerializer extends SerializerWithStringManifest {
  import Messages._

  private val GreetingManifest = classOf[Greeting].getName

  def identifier: Int = 2587

  def manifest(o: AnyRef): String = o.getClass.getName


  def toBinary(o: AnyRef): Array[Byte] = o match {
    case g: Greeting => GreetingMessage(g.message).toByteArray
    case _ => throw new IllegalArgumentException(s"Unable to serialize to bytes, clazz was: ${o.getClass}!")
  }

  def fromBinary(bytes: Array[Byte], manifest: String): AnyRef = {
    manifest match {
      case GreetingManifest =>
      val message = GreetingMessage.parseFrom(bytes)
      Greeting(message.msg)
      case _ => throw new IllegalArgumentException(
          s"Unable to deserialize from bytes, manifest was: $manifest! Bytes length: " + bytes.length)
    }
  }

}
