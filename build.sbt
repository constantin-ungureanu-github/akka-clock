name := "akka-clock"
version := "0.1"
scalaVersion := "2.11.7"
compileOrder := CompileOrder.JavaThenScala
EclipseKeys.withSource := true

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.12"
)

enablePlugins(JavaAppPackaging)
mainClass in Compile := Some("JavaMain")
//mainClass in Compile := Some("ScalaMain")
