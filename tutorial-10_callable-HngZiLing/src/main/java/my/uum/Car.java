package my.uum;

public class Car {

    private String plateNo;
    private String model;
    private int year;

    public Car(String plateNo, String model, int year) {
        this.plateNo = plateNo;
        this.model = model;
        this.year = year;
    }

    public String toString() {
        return plateNo + " " + model + " " + year;
    }
}
