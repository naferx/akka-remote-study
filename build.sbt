import sbt._
import sbt.Keys._


val akkaVersion = "2.4.16"

lazy val dependencies = Seq(
  // akka
  "com.typesafe.akka"    %%   "akka-slf4j"          %   akkaVersion,
  "com.typesafe.akka"    %%   "akka-actor"          %   akkaVersion,
  "com.typesafe.akka"    %%   "akka-remote"         %   akkaVersion,
  "com.typesafe"         %    "config"              %   "1.3.1",
  // utils
  "ch.qos.logback"       %    "logback-classic"     %   "1.1.7",

  "org.scalatest"        %%   "scalatest"           %   "3.0.0" % "test"
)

lazy val commonSettings = Seq(
  name := "akka-remote-study",
  version := "1.0",
  scalaVersion := "2.11.8",
  //resolvers := allResolvers,
  libraryDependencies := dependencies
)

lazy val remote = (project in file("remote")).
  settings(commonSettings: _*)


lazy val local = (project in file("local")).
  settings(commonSettings: _*)
  .dependsOn(remote)


lazy val root = (project in file("."))
  .settings(commonSettings.settings: _*)
  .aggregate(remote, local)
  .dependsOn(remote, local)

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")
