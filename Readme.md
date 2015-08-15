sbt clean

sbt "run 1000000"

sbt eclipse:clean eclipse

sbt gen-idea:clean gen-idea

sbt stage universal:packageBin

./target/universal/stage/bin/akka-clock 1000000
