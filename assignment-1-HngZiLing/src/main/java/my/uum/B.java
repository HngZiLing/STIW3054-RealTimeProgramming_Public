package my.uum;
import java.util.Scanner;

/**
 * This class is for during the game dan display rule of game
 *
 * @author H'ng Zi Ling 281895
 */
public class B extends A {
    static B nil = new B();         // Object
    static String b = "0000";       // Number guessed by player
    static int[] c = new int[11];   // Chances used to guess the number

    /**
     * This method is for guessing the number
     * @return number guessed by player
     */
    public static String get_b() {
        boolean numeric;
        Scanner guess = new Scanner(System.in);
        nil.b = guess.nextLine();

        try {
            Integer.parseInt(b);
            numeric = true;
        } catch (NumberFormatException error) {
            numeric = false;
        }

        if (b.length() != 4 || !numeric)
            System.out.println("Only accept 4 digit number");
        else
            System.out.println("-> " + sum());
        return b;
    }

    /**
     * This method is for guessing number
     *
     * @return total chances used by player
     */
    public static int get_c() {
        boolean numeric;
        for (int i = 1; i <= 10; i++) {
            if (sum().equals("4A0B")) {
                c[C.x] = i - 1;
                break;
            } else {
                do {
                    if ((i) > 10)
                        break;
                    else {
                        System.out.print(i + " times: ");
                        get_b();
                        try {
                            Integer.parseInt(b);
                            numeric = true;
                        } catch (NumberFormatException error) {
                            numeric = false;
                        }
                        c[C.x] = i;
                    }
                } while (b.length() != 4 || !numeric);
            }
        }
        return c[C.x];
    }

    /**
     * This method is for counting the result of the guess
     *
     * @return Result of the guess
     */
    public static String sum() { // Check ?A?B
        int resultA = 0, resultB = 0;
        for (int i = 0; i < b.length(); i++) {
            for (int k = 0; k < a.length(); k++) {
                if (b.charAt(i) == a.charAt(k)) {
                    if (i == k)
                        resultA++;
                    else
                        resultB++;
                }
            }
        }
        return resultA + "A" + resultB + "B";
    }

    /**
     * This method is for displaying rule of the game
     */
    @Override
    public void disp_a() {
        System.out.println("\n4A0B is a number guessing game, also known as 'Bulls and Cows'\n" +
                "A = the digit is a correct number at correct position\n" +
                "B = the digit is a correct number at incorrect position\n" +
                "You need to guess 4 digit secret number until 4A0B within the 10 chances\n" +
                "Tips: There are no duplicate digit");
    }
}
