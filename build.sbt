import sbt._
import sbt.Keys._

name := "sbt-study"

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
  organization := "com.github.naferx",
  version := "1.0",
  scalaVersion := "2.11.8"
)

lazy val root = (project in file(".")).
  aggregate(remote, local)

lazy val remote = (project in file("remote")).
  settings(commonSettings: _*).
  settings(
    libraryDependencies ++= dependencies
  )

lazy val local = (project in file("local")).
  settings(commonSettings: _*).
  settings(
    libraryDependencies ++= dependencies
  ).
  aggregate(remote).
  dependsOn(remote)
