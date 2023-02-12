package my.uum;

public class MyFirstThread extends Thread {

    public void run() {
        System.out.println("My First Thread " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getId());

        MyFirstThread thread1 = new MyFirstThread();
        MyFirstThread thread2 = new MyFirstThread();
        MyFirstThread thread3 = new MyFirstThread();
        thread3.setName("Malaysia");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
