package my.uum;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class MyAtomic {
    static int x;
    static int total = 0;
    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        System.out.print("Please input x: ");
        x = scan.nextInt();
        System.out.println();

        Thread t1 = new Thread(() -> {
            count();
        });
        Thread t2 = new Thread(() -> {
            count();
        });
        Thread t3 = new Thread(() -> {
            count();
        });

        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        t3.join();
        System.out.println("Total = " + total);
    }

    public static void count() {
        AtomicInteger count = new AtomicInteger();
        for (int i = x; i < x + 10; i++) {
            count.incrementAndGet();
            System.out.println(Thread.currentThread().getName() + ": " + i);
            total = total + i;
        }
    }
}