package my.uum;
import java.util.Scanner;

/**
 * This class is for after the game end
 * @author H'ng Zi Ling 281895
 */
public class C extends B {

    static int[] d = new int[11];   // Final score
    static String e = "";           // Selection "Yes" or "No" before exit the game
    static int x = 0;               // The total number of rounds played by the player
    static A c1 = new A();          // Object

    /**
     * This method is for calculating the total score
     * @return Score for each round
     */
    public static String sum() {
        if (B.sum().equals("4A0B"))
            d[x] = 100 - (c[x] * 10) + 10;
        else
            d[x] = 0;
        return String.valueOf(d[x]);
    }

    /**
     * This method is for displaying player's win or loss and score
     */
    public static void disp_e() {
        sum();
        if (B.sum().equals("4A0B"))
            System.out.println("Congratulation! You win within " + c[x] + " chances got " + d[x] + "%");
        else {
            System.out.println("Game over! You fail within 10 chances got 0%");
            System.out.println("The secret number is " + a);
        }
    }

    /**
     * This method is for getting the menu selection
     * @return menu selection
     */
    public static String get_d() {
        c1.disp_a();
        do {
            Scanner response = new Scanner(System.in);
            a = response.nextLine();
            if (!a.equals("1") && !a.equals("2") && !a.equals("3") && !a.equals("4"))
                System.out.print("Enter 1-4 only: ");
        } while (!a.equals("1") && !a.equals("2") && !a.equals("3") && !a.equals("4"));
        return a;
    }

    /**
     * This method is for displaying the total rounds, chances and scores
     */
    public static void sum1() {
        sum();
        if ((x) == 0)
            System.out.println("You have no record! Let start a game first!");
        else {
            for (int i = 0; i < x; i++) {
                if (i < 9)
                    System.out.print("Round 0" + (i + 1));
                else
                    System.out.print("Round " + (i + 1));

                if (c[i] != 10)
                    System.out.println(" | 0" + c[i] + " chances | " + d[i] + "%");
                else
                    System.out.println(" | " + c[i] + " chances | " + d[i] + "%");
            }
        }
    }

    /**
     * This method is for deciding whether to exit the game
     */
    public static void get_e() {
        System.out.print("Are you sure to exit the game? (Yes/No) : ");
        Scanner exit = new Scanner(System.in);
        do {
            e = exit.next().toUpperCase();

            if (e.equals("NO"))
                break;
            else if (e.equals("YES"))
                System.exit(0);
            else
                System.out.println("Please answer Yes/No only");
        } while (!e.equals("NO") || !e.equals("YES"));
    }
}
