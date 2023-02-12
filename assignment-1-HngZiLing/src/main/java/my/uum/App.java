package my.uum;

/**
 * This class if for running the main method, which is display menu
 */
public class App {
    /**
     * This method is for choosing the menu selection.
     *
     * @param args Menu.
     */

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        C c = new C();
        do {
            switch (c.get_d()) {
                case "1":
                    if (c.x < 10) {
                        System.out.printf("Round " + (c.x + 1) + ": You hava 10 chances to guess the secret number!\n");
                        a.get_a();
                        b.get_c();
                        c.disp_e();
                        c.x++;
                    } else
                        System.out.println("You have run out the chances");
                    break;

                case "2":
                    b.disp_a();
                    break;

                case "3":
                    c.sum1();
                    break;

                case "4":
                    c.get_e();
            }
        } while (!c.e.equals("YES"));
    }
}
