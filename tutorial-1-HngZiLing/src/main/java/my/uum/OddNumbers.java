package my.uum;

import java.util.Scanner;

public class OddNumbers {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int x, y, difference;

        do {
            System.out.print("Please enter X: ");
            x = scan.nextInt();
            System.out.print("Please enter Y: ");
            y = scan.nextInt();

            difference = Math.abs(y - x);
            System.out.println("Difference: " + difference);

            if (difference < 5 || difference > 10)
                System.out.println("Difference cannot less than 5 or more than 10\n");
        } while (difference < 5 || difference > 10);

        int small, big;
        if (x < y) {
            small = x;
            big = y;
        } else {
            small = y;
            big = x;
        }

        for (int num = small; num <= big; num++) {
            if (num % 2 != 0)
                System.out.println(num);
        }
    }
}
