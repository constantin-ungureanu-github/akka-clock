name := "akka-clock"
version := "1.0"
scalaVersion := "2.11.7"
EclipseKeys.withSource := true

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.12"
)

enablePlugins(JavaAppPackaging)
mainClass in Compile := Some("Main")
