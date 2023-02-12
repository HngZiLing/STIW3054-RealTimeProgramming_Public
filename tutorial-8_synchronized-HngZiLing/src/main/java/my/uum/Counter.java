package my.uum;

public class Counter {
    private static int count = 0;

    public static void nonIncrement() {
        count++;
    }

    public static void nonDecrement() {
        count--;
    }

    public int getNonCounter() {
        return count;
    }

    public static void increment() {
        count++;
    }

    public static void decrement() {
        count--;
    }

    public void getCounter() {
        System.out.println("\nResult (synchronized method): " + count);
    }
}
