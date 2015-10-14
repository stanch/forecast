scalaVersion := "2.11.7"

name := "forecast"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http-experimental" % "1.0",
  "de.heikoseeberger" %% "akka-http-play-json" % "1.1.0",
  "com.softwaremill.quicklens" %% "quicklens" % "1.4.1",
  "com.lihaoyi" %% "scalatags" % "0.5.2",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "com.github.alexarchambault" %% "scalacheck-shapeless_1.12" % "0.3.1" % "test"
)
