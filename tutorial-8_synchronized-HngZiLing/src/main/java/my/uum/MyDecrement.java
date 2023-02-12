package my.uum;

import static my.uum.MySynchronized.x;

public class MyDecrement {
    private Counter counter;

    public MyDecrement(Counter counter) {
        this.counter = counter;
    }

    Thread nonDecrement = new Thread() {
        public void run() {
            for (int i = 0; i < x; i++) {
                Counter.nonDecrement();
            }
        }
    };

    Thread myDecrement = new Thread() {
        public void run() {
            for (int i = 0; i < x; i++) {
                Counter.decrement();
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                }
            }
        }
    };
}
