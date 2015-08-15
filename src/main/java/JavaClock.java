import java.io.Serializable;

import akka.actor.UntypedActor;

public class JavaClock extends UntypedActor {
    private Long step = 0l;
    private Long duration = 0l;
    private Long startTime = 0l;

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Start) {
            this.duration = ((Start) message).getDuration();
            startTime = System.currentTimeMillis();
            getSender().tell(Ping.getInstance(), getSelf());
        } else if (message instanceof Stop) {
            Long stopTime = System.currentTimeMillis();
            System.out.println("Finished after " + (stopTime - startTime) + " milliseconds.");
            System.exit(0);
        } else if (message instanceof Ping) {
            if (step < duration) {
                step++;
                getSender().tell(Tick.getInstance(), getSelf());
                getSender().tell(Pong.getInstance(), getSelf());
            } else {
                getSender().tell(Stop.getInstance(), getSelf());
            }
        } else if (message instanceof Pong) {
            getSender().tell(Ping.getInstance(), getSelf());
        } else if (message instanceof Tick) {
            // TODO
        } else {
            unhandled(message);
        }
    }

    public static final class Ping implements Serializable {
        private static final long serialVersionUID = 5592624326581846277L;
        private static final Ping instance = new Ping();

        private Ping() {
        }

        public static Ping getInstance() {
            return instance;
        }
    }

    public static final class Pong implements Serializable {
        private static final long serialVersionUID = -3249837482565376870L;
        private static final Pong instance = new Pong();

        private Pong() {
        }

        public static Pong getInstance() {
            return instance;
        }
    }

    public static final class Start implements Serializable {
        private static final long serialVersionUID = -5750159585853846166L;
        Long duration;

        Start(Long duration) {
            setDuration(duration);
        }

        public Long getDuration() {
            return duration;
        }

        public void setDuration(Long duration) {
            this.duration = duration;
        }
    }

    public static final class Stop implements Serializable {
        private static final long serialVersionUID = 5860804743274500349L;
        private static final Stop instance = new Stop();

        private Stop() {
        }

        public static Stop getInstance() {
            return instance;
        }
    }

    public static final class Tick implements Serializable {
        private static final long serialVersionUID = 3408513431293936766L;
        private static final Tick instance = new Tick();

        private Tick() {
        }

        public static Tick getInstance() {
            return instance;
        }
    }
}
