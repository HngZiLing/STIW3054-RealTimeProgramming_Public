package my.uum;

import java.util.Scanner;

public class MyMethod {
    public static void main(String[] args) {
        int character = count();
        int age = birth();
        System.out.println("Total Characters: " + character);
        System.out.println("Age: " + age);
    }

    public static int count() {
        Scanner scan = new Scanner(System.in);
        String name;
        int character;
        do {
            System.out.print("Please enter your name: ");
            name = scan.nextLine();
            character = 0;
            for (int i = 0; i < name.length(); i++)
                if (name.charAt(i) != ' ')
                    character++;
            if (character < 5)
                System.out.println("The length of the name cannot less than 5 characters");
        } while (character < 5);
        return character;
    }

    public static int birth() {
        Scanner scan = new Scanner(System.in);
        int year;
        do {
            System.out.print("Please enter your year of birth: ");
            year = scan.nextInt();
            if (year > 2021)
                System.out.println("The year cannot greater than 2021");
        } while (year > 2021);
        return (2022 - year);
    }
}
