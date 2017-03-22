
package com.github.naferx.configuration

import com.typesafe.config.ConfigFactory

object CommonConfig {
  lazy val defaultConfig = ConfigFactory.load("messages.conf")
}
