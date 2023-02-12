package my.uum;
import java.util.Scanner;
public class LabTest1 implements Calculate {
    public static void main(String[] args) {
        int length;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Enter the length of an array: ");
            length = scanner.nextInt();

            if (length < 5 || length > 7)
                System.out.println("The length of the array cannot less than 5 or more than 7");

        } while (length < 5 || length > 7);

        int array[] = new int[length];
        for (int i = 0; i < length; i++) {
            System.out.print("Please enter num[" + i + "]: ");
            int input = scanner.nextInt();
            array[i] = input;
        }

        System.out.println("\nMinimum: " + Calculate.minimum(array));
        System.out.println("Maximum: " + Calculate.maximum(array));
        System.out.println("Average: " + Calculate.average(array));
    }
}

interface Calculate {
    public static int minimum(int arr[]) {
        int min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min)
                min = arr[i];
        }
        return min;
    }

    public static int maximum(int arr[]) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
        }
        return max;
    }

    public static int average(int arr[]) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
        }
        int average = sum / arr.length;
        return average;
    }
}
