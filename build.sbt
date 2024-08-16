name := "PekkoWebApi"

version := "1.0"

scalaVersion := "2.13.12"

libraryDependencies ++= Seq(
  "org.apache.pekko" %% "pekko-actor" % "1.0.1",
  "org.apache.pekko" %% "pekko-http" % "1.0.1",
  "org.apache.pekko" %% "pekko-stream" % "1.0.1"
)