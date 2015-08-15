import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

class JavaMain {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage run <ticks>");
            return;
        }

        ActorSystem system = ActorSystem.create("system");
        ActorRef clock = system.actorOf(Props.create(JavaClock.class), "clock");

        clock.tell(new JavaClock.Start(Long.parseLong(args[0])), clock);
    }
}
