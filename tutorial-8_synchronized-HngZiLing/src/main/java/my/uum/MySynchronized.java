package my.uum;

import org.apache.commons.lang3.time.StopWatch;

import java.util.Scanner;

public class MySynchronized {
    static int x;

    public static void main(String[] args) throws InterruptedException {

        System.out.print("Please input x: ");
        Scanner scan = new Scanner(System.in);
        x = scan.nextInt();

        Counter counter = new Counter();
        MyIncrement myIncrement = new MyIncrement(counter);
        MyDecrement myDecrement = new MyDecrement(counter);

        myIncrement.nonIncrement.start();
        myDecrement.nonDecrement.start();
        myIncrement.nonIncrement.join();
        myDecrement.nonDecrement.join();
        System.out.println("\nResult (non-synchronized method): " + counter.getNonCounter());

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        stopWatch.stop();
        System.out.println("Execution Time: " + stopWatch.getTime() + " millisedonds");

        myIncrement.myIncrement.start();
        myIncrement.myIncrement.join();
        myDecrement.myDecrement.start();
        myDecrement.myDecrement.join();

        stopWatch.reset();
        stopWatch.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        stopWatch.stop();
        counter.getCounter();
        System.out.println("Execution Time: " + stopWatch.getTime() + " millisedonds");
    }
}
