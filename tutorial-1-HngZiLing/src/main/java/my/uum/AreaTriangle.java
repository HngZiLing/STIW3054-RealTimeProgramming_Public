package my.uum;

import java.util.Scanner;

public class AreaTriangle {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int width, height, area;

        do {
            System.out.print("Please enter the width: ");
            width = scan.nextInt();
            if (width < 2 || width > 10)
                System.out.println("Cannot less than 2 or more than 10");
        } while (width < 2 || width > 10);

        do {
            System.out.print("Please enter the height: ");
            height = scan.nextInt();
            if (height < 2 || height > 10)
                System.out.println("Cannot less than 2 or more than 10");
        } while (height < 2 || height > 10);

        area = (width * height) / 2;
        System.out.print("The area of triangle: " + area);
    }
}
