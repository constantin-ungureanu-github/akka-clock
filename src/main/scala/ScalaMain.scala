import akka.actor.{ ActorSystem, Props }

object ScalaMain {
	def main(args: Array[String]): Unit = {
		if (args.isEmpty) {
			println("Usage run <ticks>")
		} else {
			ActorSystem("system").actorOf(Props[ScalaClock], "clock") ! ScalaClock.Start(args(0).toLong)
		}
	}
}
