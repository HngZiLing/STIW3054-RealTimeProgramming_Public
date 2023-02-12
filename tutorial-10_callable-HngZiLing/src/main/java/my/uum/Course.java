package my.uum;

import java.io.Serializable;

public class Course implements Serializable {

    private String code;
    private String name;
    private int credirHours;

    public Course(String code, String name, int credirHours) {
        this.code = code;
        this.name = name;
        this.credirHours = credirHours;
    }

    public String toString() {
        return code + " " + name + " " + credirHours;
    }
}
