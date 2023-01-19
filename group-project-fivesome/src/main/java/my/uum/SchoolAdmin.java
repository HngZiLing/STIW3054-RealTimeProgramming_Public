package my.uum;

public class SchoolAdmin {
    static String staff_no, name, mobile_tel_no, email, status;

    public static void setStaffNo(String staff_no) {
        SchoolAdmin.staff_no = staff_no;
    }

    public static String getStaffNo() {
        return SchoolAdmin.staff_no;
    }

    public static void setStaffName(String name) {
        SchoolAdmin.name = name;
    }

    public static String getName() {
        return SchoolAdmin.name;
    }

    public static void setMobileTelNo(String mobile_tel_no) {
        if (mobile_tel_no.length() == 10 || mobile_tel_no.length() == 11) {
            SchoolAdmin.mobile_tel_no = mobile_tel_no;
        }
    }

    public static String getMobileTelNo() {
        return SchoolAdmin.mobile_tel_no;
    }

    public static void setEmail(String email) {
        SchoolAdmin.email = email;
    }

    public static String getEmail() {
        return SchoolAdmin.email;
    }

    public static void setStatus(String status) {
        SchoolAdmin.status = status;
    }

    public static String getStatus() {
        return SchoolAdmin.status;
    }
}
