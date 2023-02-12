package my.uum;

import java.util.ArrayList;
import java.util.List;

public class CarArrayList {

    public static void main(String[] args) {
        Car car1 = new Car("KCL1000", "Honda", 2000);
        Car car2 = new Car("PJY2000", "Toyota", 2011);
        Car car3 = new Car("VCN3000", "Yamaha", 2019);

        List<Car> carList = new ArrayList<>();
        carList.add(car1);
        carList.add(car2);
        carList.add(car3);
        System.out.println(carList);

        System.out.println(carList.get(1));

        carList.remove(car2);
        System.out.println(carList);

        for (Car myCar : carList) {
            System.out.println(myCar);
        }
    }
}
