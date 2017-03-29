
package com.github.naferx.configuration

import com.typesafe.config.{Config, ConfigFactory}

object CommonConfig {
  val defaultConfig: Config = ConfigFactory.load("messages.conf")
}
