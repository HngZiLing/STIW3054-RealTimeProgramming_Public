package my.uum;

import org.apache.commons.lang3.time.StopWatch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;
import java.util.concurrent.*;

public class MyCallable implements Callable<Integer> {
    static Scanner scan = new Scanner(System.in);
    static String keyWord;

    @Override
    public Integer call() throws Exception {
        System.out.print("Input word: ");
        keyWord = scan.nextLine();
        System.out.println();

        File file = new File("RossBeresford.txt");
        String[] word;
        FileReader fileReader = new FileReader(file);
        BufferedReader br = new BufferedReader(fileReader);
        String current;
        int count = 0;

        while ((current = br.readLine()) != null) {
            word = current.split("[\\s,\\,,\\(,\\)]+");
            for (String found : word) {
                if (found.toLowerCase().equals(keyWord.toLowerCase())) {
                    count++;
                }
            }
        }
        fileReader.close();
        return count;
    }

    public static void main(String[] args) {
        ExecutorService ex = Executors.newCachedThreadPool();
        Callable<Integer> myCallable = new MyCallable();
        Future<Integer> future = ex.submit(myCallable);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            Integer count = future.get();
            if (count > 0)
                System.out.println(keyWord + " - " + count);
            else
                System.out.println("This word is not in the file");
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e);
        }
        System.out.println();
        stopWatch.stop();
        NumberFormat numberFormat = new DecimalFormat("#0.000");
        String time = String.valueOf(stopWatch.getTime(TimeUnit.MILLISECONDS));
        double duration = Double.valueOf(time) / 1000;
        System.out.println("Execution time: " + duration + " seconds");
        ex.shutdown();
    }
}
