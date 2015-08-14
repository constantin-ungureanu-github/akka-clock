import akka.actor.{ ActorRef, Actor }
import akka.actor.Actor.Receive
import akka.event.LoggingAdapter
import akka.actor.ActorLogging

class Clock extends Actor with ActorLogging {
  import Clock.{ Start, Stop, Tick, Ping, Pong }

  private var step = 0L
  private var duration = 0L
  private var startTime = 0L
  private var stopTime = 0L

  def receive = {
    case Start(duration) => {
      this.duration = duration
      startTime = System.currentTimeMillis
      self ! Ping
    }

    case Stop => {
      stopTime = System.currentTimeMillis()
      println("Finished after "+ (stopTime - startTime) +" milliseconds.")
      context.system.shutdown()
    }

    case Ping => {
      if (step < duration) {
        step +=1
        self ! Tick
        self ! Pong
      } else
        self ! Stop
    }

    case Pong => {
      self ! Ping
    }

    case Tick => {
      // TODO
    }
  }
}

object Clock {
  case class Start(duration: Long)
  case object Stop
  case object Tick
  case object Ping
  case object Pong
}
