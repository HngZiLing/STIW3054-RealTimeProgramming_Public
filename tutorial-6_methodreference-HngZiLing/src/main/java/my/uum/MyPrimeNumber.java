package my.uum;
import java.util.Scanner;
public class MyPrimeNumber {
    static int sum;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Please input x: ");
        int x = scan.nextInt();
        System.out.println();

        Thread t1 = new Thread(() -> t1print(x));
        Thread t2 = new Thread(() -> t2print(x));
        t1.start();
        t2.start();

        while (t1.isAlive() || t2.isAlive()) {
        }
        System.out.println("Total: " + sum);
    }

    synchronized private static void total(int num) {
        sum += num;
    }

    public static void t1print(int x) {
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
                total(i);
            }
        }
    }

    public static void t2print(int x) {
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
                total(i);
            }
        }
    }
}
