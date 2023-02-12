package my.uum;

import static my.uum.MySynchronized.x;

public class MyIncrement {
    private Counter counter;

    public MyIncrement(Counter counter) {
        this.counter = counter;
    }

    Thread nonIncrement = new Thread() {
        public void run() {
            for (int i = 0; i < x; i++) {
                Counter.nonIncrement();
            }
        }
    };

    Thread myIncrement = new Thread() {
        public void run() {
            for (int i = 0; i < x; i++) {
                Counter.increment();
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                }
            }
        }
    };
}
