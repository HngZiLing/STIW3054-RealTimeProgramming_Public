package my.uum;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ReadObjects {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("Course.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);

        ArrayList<Course> courseList = new ArrayList<>();
        courseList = (ArrayList<Course>) ois.readObject();

        for (Course myCourse : courseList) {
            System.out.println(myCourse);
        }

        ois.close();
        fis.close();
    }
}
