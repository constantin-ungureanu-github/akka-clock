import java.io.Serializable;

import akka.actor.UntypedActor;

public class JavaClock extends UntypedActor {
    private Long step = 0l;
    private Long duration = 0l;
    private Long startTime = 0l;
    private Long stopTime = 0l;

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Start) {
            this.duration = ((Start) message).getDuration();
            startTime = System.currentTimeMillis();
            getSender().tell(Ping.getInstance(), getSelf());
        } else if (message instanceof Stop) {
            stopTime = System.currentTimeMillis();
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
        private static final long serialVersionUID = 5531177271424888266L;
        private static final Ping instance = new Ping();

        private Ping() {
        }

        public static Ping getInstance() {
            return instance;
        }
    }

    public static final class Pong implements Serializable {
        private static final long serialVersionUID = 6080438337105207187L;
        private static final Pong instance = new Pong();

        private Pong() {
        }

        public static Pong getInstance() {
            return instance;
        }
    }

    public static final class Start implements Serializable {
        private static final long serialVersionUID = -7862663889125155306L;
        Long duration;

        Start(Long duration) {
            this.duration = duration;
        }

        public Long getDuration() {
            return duration;
        }

        public void setDuration(Long duration) {
            this.duration = duration;
        }
    }

    public static final class Stop implements Serializable {
        private static final long serialVersionUID = 1195537540409043952L;
        private static final Stop instance = new Stop();

        private Stop() {
        }

        public static Stop getInstance() {
            return instance;
        }
    }

    public static final class Tick implements Serializable {
        private static final long serialVersionUID = -7976881343629301887L;
        private static final Tick instance = new Tick();

        private Tick() {
        }

        public static Tick getInstance() {
            return instance;
        }
    }
}
