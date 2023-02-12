package my.uum;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyExecuter {
    static int total = 0;
    public static void main(String[] args) {
        System.out.print("Please input i: ");
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        System.out.println();

        for (int i = 1; i <= num; i++) {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(() -> {
                for (int j = 1; j <= num; j++) {
                    System.out.println(Thread.currentThread().getName() + ": " + j);
                    total++;
                }
            });
            executorService.shutdown();
            while (!executorService.isTerminated()) {
            }
        }
        System.out.println("Total = " + total);
    }
}

