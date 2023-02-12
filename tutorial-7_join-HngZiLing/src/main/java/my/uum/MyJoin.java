package my.uum;

import java.util.Scanner;

public class MyJoin {
    static int total = 0;
    static int x;

    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        System.out.print("Please input x: ");
        x = scan.nextInt();
        System.out.println();

        Thread t0 = new Thread(() -> {
            int count;
            for (int i = x; i <= x + 5; i++) {
                count = 0;
                for (int j = 2; j <= i / 2; j++) {
                    if (i % j == 0) {
                        count++;
                        break;
                    }
                }
                if (count == 0) {
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                    total += i;
                }
            }
        });

        Thread t1 = new Thread(() -> {
            int count;
            for (int i = x + 5; i <= x + 10; i++) {
                count = 0;
                for (int j = 2; j <= i / 2; j++) {
                    if (i % j == 0) {
                        count++;
                        break;
                    }
                }
                if (count == 0) {
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                    total += i;
                }
            }
        });

        Thread t2 = new Thread(() -> {
            int count;
            for (int i = x + 10; i <= x + 15; i++) {
                count = 0;
                for (int j = 2; j <= i / 2; j++) {
                    if (i % j == 0) {
                        count++;
                        break;
                    }
                }
                if (count == 0) {
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                    total += i;
                }
            }
        });

        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t0.start();
        t0.join();
        t1.start();
        System.out.println("Total: " + total);
    }
}
