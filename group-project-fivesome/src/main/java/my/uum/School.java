package my.uum;

public class School {
    static String school_id, school_name, building_location, office_no;

    public static void setSchoolId(String school_id) {
        switch (school_id) {
            case "1":
                school_id = "SOC";
                break;
            case "2":
                school_id = "SMMTC";
                break;
            case "3":
                school_id = "SAPSP";
                break;
            case "4":
                school_id = "SLCP";
                break;
            case "5":
                school_id = "SCIMPA";
                break;
            case "6":
                school_id = "SQS";
                break;
        }
        School.school_id = school_id;
    }

    public static String getSchoolId() {
        return School.school_id;
    }

    public static void setSchoolName(String school_name) {
        switch (school_name) {
            case "1":
                school_name = "School of Computing";
                break;
            case "2":
                school_name = "School of Multimedia Technology and Communication";
                break;
            case "3":
                school_name = "School of Applied Psychology, Social Work and Policy";
                break;
            case "4":
                school_name = "School of Languages, Civilisation and Philosophy";
                break;
            case "5":
                school_name = "School of Creative Industry Management and Performing Arts";
                break;
            case "6":
                school_name = "School of Quantitative Sciences";
                break;
        }
        School.school_name = school_name;
    }

    public static String getSchoolName() {
        return School.school_name;
    }

    public static void setBuildingLocation(String building_location) {
        School.building_location = building_location;
    }

    public static String getBuildingLocation() {
        return School.building_location;
    }

    public static void setOfficeNo(String office_no) {
        School.office_no = office_no;
    }

    public static String getOfficeNo() {
        return School.office_no;
    }
}
