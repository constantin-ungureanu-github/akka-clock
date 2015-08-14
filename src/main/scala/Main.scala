import akka.actor.{ ActorSystem, Props }

object Main {
  def main(args: Array[String]): Unit = {
    if (args.isEmpty) {
      println("Usage run <ticks>")
    } else {
      ActorSystem("system").actorOf(Props[Clock], "clock") ! Clock.Start(args(0).toInt)
    }
  }
}
