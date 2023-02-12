public class Counter {

    private int counter;

    public void increment(){
        counter++;
    }

    public void decrement(){
        counter--;
    }

    synchronized public void myIncrement(){
        counter++;
    }

    synchronized public void myDecrement(){
        counter--;
    }
}
