name := "akka-remote-study"

version := "1.0"

scalaVersion := "2.11.8"

val akkaVersion = "2.4.14"

libraryDependencies ++= Seq(
  // akka
  "com.typesafe.akka"    %%   "akka-slf4j"          %   akkaVersion,
  "com.typesafe.akka"    %%   "akka-actor"          %   akkaVersion,
  "com.typesafe.akka"    %%   "akka-remote"         %   akkaVersion,
  "com.typesafe"         %    "config"              %   "1.3.1",
  // utils
  "ch.qos.logback"       %    "logback-classic"     %   "1.1.7",

  "org.scalatest"        %%   "scalatest"           %   "3.0.0"
)

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")
