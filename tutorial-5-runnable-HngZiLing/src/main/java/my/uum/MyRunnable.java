package my.uum;

import java.util.Scanner;

public class MyRunnable implements Runnable {
    static int x, y;

    public static void main(String[] args) {

        do {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Please input x: ");
            x = scanner.nextInt();
            System.out.print("Please input y: ");
            y = scanner.nextInt();
            if (y < x)
                System.out.println("y cannot less than x");
        } while (y < x);
        System.out.println();

        MyRunnable myRunnable = new MyRunnable();
        Thread odd = new Thread(myRunnable);
        Thread even = new Thread(myRunnable);
        odd.start();
        even.start();
    }

    public void run() {
        if (Thread.currentThread().getName().equals("Thread-0")) {
            for (int i = x; i <= y; i++) {
                if (i % 2 != 0)
                    System.out.println(i);
            }
        }
        if (Thread.currentThread().getName().equals("Thread-1")) {
            for (int i = x; i <= y; i++) {
                if (i % 2 == 0)
                    System.out.println(i);
            }
        }
    }
}
