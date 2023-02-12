package my.uum;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class WriteObjects {

    public static void main(String[] args) throws IOException {
        Course c1 = new Course("STIA1113", "Programming1", 3);
        Course c2 = new Course("STIA1123", "Programming2", 3);
        Course c3 = new Course("STIW3054", "Realtime Programming", 4);

        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(c1);
        courseList.add(c2);
        courseList.add(c3);
        System.out.println(courseList);

        FileOutputStream fos = new FileOutputStream("Course.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(courseList);

        System.out.println("Finish");

        oos.close();
        fos.close();
    }
}
