package my.uum;

import java.io.File;
import java.sql.*;
import java.util.Date;

/**
 * This class is for SQLite database to select, insert, and delete the data
 */
public class SQLite extends STIW3054_Fivesome_bot {

    /**
     * This method is to connect the telegram bot with SQLite database
     * @return tbl_user and tbl_room in the database
     */
    public static Connection connect() {

        Connection conn = null;
        try {
            File file = new File("database.db");
            if (!file.exists()) {
                file.createNewFile();
            }

            String url = "jdbc:sqlite:" + file.getPath();
            conn = DriverManager.getConnection(url);

//            Statement stmt = conn.createStatement();
//            String sql = "CREATE TABLE IF NOT EXISTS tbl_booking(" +
//                    "booking_id TEXT not null constraint booking_id primary key, " +
//                    "purpose TEXT not null," +
//                    "booking_date TEXT not null," +
//                    "booking_start_time TEXT not null," +
//                    "booking_end_time   TEXT not null,\n" +
//                    "    currentTime        TEXT not null,\n" +
//                    "    constraint email\n" +
//                    "        foreign key (email) references tbl_user (email),\n" +
//                    "    constraint room_id\n" +
//                    "        foreign key (room_id) references tbl_room (room_id)\n" +
//                    ");";
//            stmt.execute(sql);
//            sql = "CREATE TABLE IF NOT EXISTS tbl_user(email TEXT PRIMARY KEY , ic_no TEXT NOT NULL, name TEXT NOT NULL, mobile_telNo Text NOT NULL, password TEXT NOT NULL)";
//            stmt.execute(sql);
//            sql = "CREATE TABLE IF NOT EXISTS tbl_room (room_id Text PRIMARY KEY, description TEXT NOT NULL, capacity INT NOT NULL, room_type_id TEXT NOT NULL,school_id TEXT NOT NULL)";
//            stmt.execute(sql);
//            sql = "CREATE TABLE IF NOT EXISTS tbl_roomType (room_type_id TEXT PRIMARY KEY,room_type TEXT NOT NULL )";
//            stmt.execute(sql);
//            sql = "CREATE TABLE IF NOT EXISTS tbl_school (school_id TEXT PRIMARY KEY,school_name TEXT NOT NULL, building_location Text NOT NULL,office_no Text NOT NULL  )";
//            stmt.execute(sql);

            return conn;

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * This method is for insert booking info
     * @param purpose
     * @param booking_date
     * @param booking_startTime
     * @param booking_endTime
     * @param roomId
     */

    public static void insertBooking(String purpose, String booking_date, String booking_startTime, String booking_endTime, String roomId) {
        Date date = new Date();
        try {
            Connection conn = SQLite.connect();
            String sql = "INSERT INTO tbl_booking(booking_id, purpose, booking_date, booking_start_time,booking_end_time,currentTime,email,room_id) VALUES (?,?,?,?,?,?,?,?)";
            if (conn != null) {
                PreparedStatement pstmt;
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,null );
                pstmt.setString(2, purpose);
                pstmt.setString(3, (booking_date));
                pstmt.setString(4, (booking_startTime));
                pstmt.setString(5, (booking_endTime));
                pstmt.setString(6, date.toString());
                pstmt.setString(7,User_list.getEmail());
                pstmt.setString(8, Booking_info.getRoomId());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is for insert user info
     * @param email
     * @param icno
     * @param name
     * @param mobile_telNo
     * @param password
     */
    public static void insert_User_Info(String email, String icno, String name, String mobile_telNo, String password) {
        String mobile = mobile_telNo;
        try {
            Connection conn = SQLite.connect();
            String sql = "INSERT INTO tbl_user(email, ic_no, name, mobile_telNo, password) VALUES (?,?,?,?,?)";
            if (conn != null) {
                PreparedStatement pstmt;
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, (email));
                pstmt.setString(2, (icno));
                pstmt.setString(3, name);
                pstmt.setString(4, (mobile_telNo));
                pstmt.setString(5, (password));

                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is for check email exist in database or not while log in
     * @param email1
     * @return
     */
    public static String select1(String email1){
    String response = "";
    String sql = "SELECT *FROM tbl_user WHERE email = '"+email1 +"'";
    try {
        Connection conn = SQLite.connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.getString("email")!=null) {
            response = "Available";
        } else {
            response = "No";
        }
        conn.close();
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return  response;
}

    /**
     * This method is to check password correct or not while log in
     * @param password
     * @return
     */
    public static String selectPass(String password) {
        String data = "";
        String sql = "SELECT * FROM tbl_user WHERE password ='" + password + "'";
        String sql1 = "SELECT * FROM tbl_booking WHERE email='" + User_list.getEmail() + "'";
        Connection conn = SQLite.connect();
        try {
            if (conn != null) {
                Statement stmt;
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                stmt = conn.createStatement();
                ResultSet rs1 = stmt.executeQuery(sql1);

                if (rs.getString("password")!=null) {
                    while (rs1.next()) {
                        String roomId = rs1.getString("room_id");
                        String sql3 = "SELECT *FROM tbl_room WHERE room_id='" + roomId + "'";
                        stmt = conn.createStatement();
                        ResultSet rs3 = stmt.executeQuery(sql3);

                        int bookingId = rs1.getInt("booking_id");
                        String purpose = rs1.getString("purpose");
                        String booking_date = rs1.getString("booking_date");
                        String booking_stime = rs1.getString("booking_start_time");
                        String booking_etime = rs1.getString("booking_end_time");
                        String room_id = rs1.getString("room_id");
                        String description = rs3.getString("description");
                        String capacity = rs3.getString("capacity");

                        data = data + "\nBooking ID: " + bookingId +
                                "\nPurpose: " + purpose +
                                "\nDate: " + booking_date +
                                "\nStart_Time: " + booking_stime + "\nEnd_Time: " + booking_etime +
                                "\nRoom ID: " + room_id +
                                "\nCapacity: " + capacity +
                                "\nDescription: " + description +
                                "\n";
                    }
                } else {
                    data = "No";
                }
                conn.close();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }


    /**
     * This method for cancel booking
     */
    public static void delete() {
        try {
            String sql = "DELETE FROM tbl_user WHERE email='"+User_list.getEmail()+"'";
            Connection conn = SQLite.connect();

            if (conn != null) {
                Statement stmt;
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                String sql1 = "DELETE FROM tbl_booking WHERE email='" + User_list.getEmail() + "'";
                stmt.executeUpdate(sql1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is to display all the user info and the meeting room info
     * @return All the info from tbl_user and tbl_booking and tbl_room in database
     */
    public static String list() {
        Connection conn = SQLite.connect();
        String list = null;
        try {
            if (conn != null) {
                String sql1 = "SELECT *FROM tbl_user";
                Statement stmt;
                stmt = conn.createStatement();
                ResultSet rs1 = stmt.executeQuery(sql1);

                String sql2 = "SELECT *FROM tbl_booking";
                stmt = conn.createStatement();
                ResultSet rs2 = stmt.executeQuery(sql2);

                list = "";
                while (rs1.next() && rs2.next()) {
                    String bookingId = rs2.getString("booking_id");
                   // String icNo = rs1.getString("ic_no");
                    String name = rs1.getString("name");
                    String telNo = rs1.getString("mobile_telNo");
                   // String email = rs1.getString("email");
                    String purpose = rs2.getString("purpose");

                    String roomId = rs2.getString("room_id");

                    String sql3= "SELECT *FROM tbl_room WHERE room_id='"+roomId+"'";
                    stmt = conn.createStatement();
                    ResultSet rs3 = stmt.executeQuery(sql3);

                    String school_id= rs3.getString("school_id");

                    String sql4 = "SELECT *FROM tbl_school  WHERE school_id='"+school_id+"'";
                    stmt = conn.createStatement();
                    ResultSet rs4 = stmt.executeQuery(sql4);


                    String description = rs3.getString("description");
                    String capacity = rs3.getString("capacity");

                    String date = rs2.getString("booking_date");
                    String stime = rs2.getString("booking_start_time");
                    String etime = rs2.getString("booking_end_time");


                    list = list + "\nBooking ID: " + bookingId +
                            "\nName: " + name +
                            "\nTel No: " + telNo +
                            "\nRoom ID: " + roomId +
                            "\nRoom description: " + description +
                            "\nMaximum capacity: " + capacity +
                            "\nSchool Name: " + rs4.getString("school_name") +
                            "\nBuilding Location: " + rs4.getString("building_location") +
                            "\nOffice Number: " + rs4.getString("office_no") +
                            "\nPurpose: " + purpose + "\nRoom Id: " + roomId +
                            "\nBooking date: " + date +
                            "\nBooking time: " + stime + "\nBooking time: " + etime +

                            "\n";
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    /**
     * This method is to return info while edit
     * @param password
     * @return
     */
    public static String editDisplay(String password) {

        String data = "";
        String sql = "SELECT * FROM tbl_user WHERE password ='"+password +"'";
        String sql1 = "SELECT * FROM tbl_booking WHERE email='"+User_list.getEmail()+"'";
        Connection conn = SQLite.connect();
        try {
            if (conn != null) {
                Statement stmt;
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                stmt = conn.createStatement();
                ResultSet rs1 = stmt.executeQuery(sql1);

                if (rs.getString("password")!=null){

                    String roomId = rs1.getString("room_id");

                    String sql3= "SELECT *FROM tbl_room WHERE room_id='"+roomId+"'";
                    stmt = conn.createStatement();
                    ResultSet rs3 = stmt.executeQuery(sql3);

                    String school_id= rs3.getString("school_id");

                    String sql2 = "SELECT *FROM tbl_school  WHERE school_id='"+school_id+"'";
                    stmt = conn.createStatement();
                    ResultSet rs2 = stmt.executeQuery(sql2);


                    while (rs1.next()&& rs.next()) {
                        int bookingId = rs1.getInt("booking_id");
                        String purpose = rs1.getString("purpose");
                        String booking_date = rs1.getString("booking_date");
                        String booking_stime = rs1.getString("booking_start_time");
                        String booking_etime = rs1.getString("booking_end_time");
                        String room_id = rs1.getString("room_id");
                        String description = rs3.getString("description");
                        String capacity = rs3.getString("capacity");

                        String icNo = rs.getString("ic_no");
                        String name = rs.getString("name");
                        String telNo = rs.getString("mobile_telNo");
                        String email = rs.getString("email");
                        String pass = rs.getString("password");

                        data= data +
                                "\nReply 1 : Booking Info: " +
                                "\n\nBooking ID: " + bookingId +
                                "\nRoom Id: " + room_id +
                                "\nRoom description: " + description +
                                "\nMaximum capacity: " + capacity +
                                "\nSchool Name: " + rs2.getString("school_name") +
                                "\nBuilding Location: " + rs2.getString("building_location") +
                                "\nOffice Number: " + rs2.getString("office_no") +
                                "\nBooking date: " + booking_date +
                                "\nBooking start time: " + booking_stime +
                                "\nBooking end time: " + booking_etime +
                                "\nPurpose: " + purpose + "" +
                                "\n\nUser Info: " +
                                "\nReply 2 :Ic number: " + icNo +
                                "\nReply 3 :Name: " + name +
                                "\nReply 4 :Tel No: " + telNo +
                                "\nReply 5:Password: " + pass +
                                "\n";
                    }
                } else {
                    data = "No";
                }
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    /**
     * This method is for update ic
     * @param icNo
     */
    public static void editIC(String icNo) {

        String sql="UPDATE tbl_user SET ic_no='"+icNo+"'"  + " WHERE email='"+User_list.getEmail()+"'";
        Connection conn = SQLite.connect();

        try{
            if (conn != null) {
                Statement stmt;
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is for update name
     * @param name
     */

    public static void editName(String name) {
        String sql="UPDATE tbl_user SET name='"+name+"'"  + " WHERE email='"+User_list.getEmail()+"'";
        Connection conn = SQLite.connect();

        try{
            if (conn != null) {
                Statement stmt;
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is for update telephone number
     */

    public static void editTelNo(String telNo) {
        String sql="UPDATE tbl_user SET mobile_telNo='"+telNo+"'"  + " WHERE email='"+User_list.getEmail()+"'";
        Connection conn = SQLite.connect();

        try{
            if (conn != null) {
                Statement stmt;
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * This method is for update password
     */

    public static void editPass(String pass) {
        String sql="UPDATE tbl_user SET password='"+pass+"'"  + " WHERE email='"+User_list.getEmail()+"'";
        Connection conn = SQLite.connect();

        try{
            if (conn != null) {
                Statement stmt;
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is for return available room in school
     * @param schoolID
     * @return
     */
    public static String displayAvailableRoom(String schoolID) {

        /*
if(  (rs.getString("school_id").equals(schoolID)&& date.equals(Booking_info.getBooking_Date())&&endTime>Time.getStartTime()&&startTime<=Time.getEndTime()){
    System.out.println("Overlapp");
}*/
        String sql = "SELECT *FROM tbl_room  WHERE school_id ='"+schoolID+"'";
        Statement stmt;
        StringBuilder stringBuilder=new StringBuilder();
        String responseFinal=null;
        String response = null;
        try {
            Connection conn = SQLite.connect();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.getString("school_id") != null) {
                String sql5 = "SELECT * FROM tbl_room WHERE school_id='" + schoolID + "'AND room_id NOT IN (Select room_id FROM tbl_booking Where booking_date='" + Booking_info.getBooking_Date() + "'AND booking_end_time>='" + Booking_info.getBooking_StartTime() + "'AND booking_start_time<='" + Booking_info.getBooking_EndTime() + "')";
                //  String sql5="SELECT * from tbl_room WHERE school_id='S12' AND room_id NOT IN (Select room_id FROM tbl_booking Where booking_date='11.11.1111' AND booking_end_time>='09:30' AND booking_start_time<='13:50' )";
                stmt = conn.createStatement();
                ResultSet rs5 = stmt.executeQuery(sql5);

                while (rs5.next()) {
                    String type_id = rs.getString("room_type_id");
                    String sql1 = "SELECT *FROM tbl_roomType  WHERE room_type_id ='" + type_id + "'";
                    stmt = conn.createStatement();
                    ResultSet rs1 = stmt.executeQuery(sql1);

                    response = ("\n\n" +
                            "RoomID:  " + rs5.getString("room_id") + "\n" +
                            "Room_Description:  " + rs5.getString("description") + "\n" +
                            "Maximum_capacity:  " + rs5.getInt("capacity") + "\n" +
                            "Room Type:  " + rs1.getString("room_type") + "\n");

                    if (response == null)
                        response = "\nRoom Not Available";
                    stringBuilder.append(response);
                }
                responseFinal = stringBuilder.toString();
            } else {
                responseFinal = "Invalid school ID";
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return responseFinal;
    }

    /**
     * This method is return school
     * @return
     */
    public static String displaySchool() {

        StringBuilder stringBuilder=new StringBuilder();
        String responseFinal=null;
        String response = null;
        String sql = "SELECT * FROM tbl_school ";

        try {
            Connection conn = SQLite.connect();
            if (conn != null) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    response = ("\n" +
                            "School ID:  " + rs.getString("school_id") + "\n" +
                            "School Name:  " + rs.getString("school_name") + "\n" +
                            "Bulding Location:  " + rs.getString("building_location") + "\n" +
                            "\n");
                    if (response == null)
                        response = "\n School Not Available";
                    stringBuilder.append(response);
                }
                responseFinal=stringBuilder.toString();
            }
            conn.close();;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return  responseFinal;
    }

    /**
     * This method is to return room Id selected is avvailable or not
     * @param roomID
     * @return
     */
    public static String selectRoom(String roomID) {

        String sql = "SELECT *FROM tbl_room  WHERE room_id ='"+roomID+"'";
        String response = null;

        try {
            Connection conn = SQLite.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.getString("room_id") != null) {
                if (response == null)
                    response = "\nRoom Not Available";
            } else {
                response = "Invalid room ID";
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return response;
    }

    /**
     * This method is for update Booking info
     * @param booking_date
     * @param booking_startTime
     * @param booking_endTime
     * @param purpose
     * @param roomId
     */
    public static void editBooking(String booking_date, String booking_startTime, String booking_endTime, String purpose, String roomId) {
        String sql = "UPDATE tbl_booking SET booking_date='" + booking_date + "',booking_start_time='" + booking_startTime + "',booking_end_time='" + booking_endTime + "',purpose='" + purpose + "',room_id='" + roomId + "'" + " WHERE email='" + User_list.getEmail() + "'";

        Connection conn = SQLite.connect();

        try{
            if (conn != null) {
                Statement stmt;
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is return to room Info
     * @param roomId
     * @return
     */
    public static String roomInfo(String roomId) {

        String sql = "SELECT *FROM tbl_room  WHERE room_id ='"+roomId+"'";
        String response = null;

        try {
            Connection conn = SQLite.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if(rs.getString("room_id")!=null) {

                String type_id= rs.getString("room_type_id");

                String sql1 = "SELECT *FROM tbl_roomType  WHERE room_type_id='"+type_id+"'";
                stmt = conn.createStatement();
                ResultSet rs1 = stmt.executeQuery(sql1);

                String school_id= rs.getString("school_id");

                String sql2 = "SELECT *FROM tbl_school  WHERE school_id='"+school_id+"'";
                stmt = conn.createStatement();
                ResultSet rs2 = stmt.executeQuery(sql2);

                response = "\nRoom description: " + rs.getString("description") +
                        "\nCapacity: " + rs.getString("capacity") +
                        "\nRoom Type: " + rs1.getString("room_type") +
                        "\nSchool Name: " + rs2.getString("school_name") +
                        "\nBuilding Location: " + rs2.getString("building_location") +
                        "\nOffice Number: " + rs2.getString("office_no");
                if (response == null)
                    response = "\nRoom Not Available";
            } else {
                response = "Invalid room ID";
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return response;
    }

    public static void insertSchoolAdmin() {
        try {
            Connection conn = SQLite.connect();
            PreparedStatement pstmt;
            String sql;
            if (conn != null) {
                sql = "INSERT INTO tbl_school_admin(staff_no, name, mobile_tel_no, email, school_id, status) VALUES (?,?,?,?,?,?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, SchoolAdmin.getStaffNo());
                pstmt.setString(2, SchoolAdmin.getName());
                pstmt.setString(3, SchoolAdmin.getMobileTelNo());
                pstmt.setString(4, SchoolAdmin.getEmail());
                pstmt.setString(5, School.getSchoolId());
                pstmt.setString(6, SchoolAdmin.getStatus());
                pstmt.executeUpdate();

                sql = "INSERT INTO tbl_school(school_id, school_name, building_location, office_no) VALUES (?,?,?,?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, School.getSchoolId());
                pstmt.setString(2, School.getSchoolName());
                pstmt.setString(3, School.getBuildingLocation());
                pstmt.setString(4, School.getOfficeNo());
                pstmt.executeUpdate();

                sql = "INSERT INTO tbl_room(room_id, description, capacity, room_type_id, school_id) VALUES (?,?,?,?,?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, Room.getRoomId());
                pstmt.setString(2, Room.getDescription());
                pstmt.setInt(3, Room.getCapacity());
                pstmt.setString(4, RoomType.getRoom_type_id());
                pstmt.setString(5, School.getSchoolId());
                pstmt.executeUpdate();

                sql = "INSERT INTO tbl_roomType(room_type_id, room_type) VALUES (?,?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, RoomType.getRoom_type_id());
                pstmt.setString(2, RoomType.getRoomType());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String checkSchoolAdminAvailable(String school_id) {
        String status = "available";
        String sql = "SELECT 1 FROM tbl_school_admin WHERE school_id ='" + School.getSchoolId() + "'";
        Connection conn = SQLite.connect();
        try {
            if (conn != null) {
                Statement stmt;
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                if (rs == null) {
                    status = "existed";
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return status;
    }

    public static String checkApplication(String staffNo) {
        String status = null;
        String sql = "SELECT status FROM tbl_school_admin WHERE staff_no = '" + staffNo + "'";
        Connection conn = SQLite.connect();
        try {
            if (conn != null) {
                Statement stmt;
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                status = rs.getString("status");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

    public static String searchSchoolId(String staff_no){
        String school_id = null;
        String sql = "SELECT school_id FROM tbl_school_admin WHERE staff_no ='"+ SchoolAdmin.getStaffNo() +"'";
        try {
            Connection conn = SQLite.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()){
                school_id = rs.getString("school_id");
            }
            conn.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } return school_id;
    }

    public static void insertRoom() {
        try {
            Connection conn = SQLite.connect();
            String sql = "INSERT INTO tbl_room(room_id, description, capacity, school_id, room_type_id) VALUES (?,?,?,?,?)";

            if (conn != null) {
                PreparedStatement pstmt;
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, Room.getRoomId());
                pstmt.setString(2, Room.getDescription());
                pstmt.setInt(3, Room.getCapacity());
                pstmt.setString(4, School.getSchoolId());
                pstmt.setString(5, RoomType.getRoom_type_id());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String listMeetingRoom(String school_id) {
        String data = "";
        String school = "";
        Connection conn = SQLite.connect();
        try {
            if (conn != null) {
                String sql = "SELECT * FROM tbl_room WHERE school_id = '" + school_id +"'";
                Statement stmt;
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    String room_id = rs.getString("room_id");
                    String description = rs.getString("description");
                    String capacity = rs.getString("capacity");
                    String room_type = rs.getString("room_type");

                    school = "SCHOOL ID: " + school_id;
                    data = data + "\n\nYOUR MEETING ROOM" +
                            "\nRoom ID: " + room_id +
                            "\nDescription: " + description +
                            "\nMax. Capacity: " + capacity +
                            "\nRoom Type: " + room_type;
                }
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return school+data;
    }

//    public static String selectMeetingRoom(String roomId) {
//        String data = "";
//        Connection conn = SQLite.connect();
//        try {
//            if (conn != null) {
//                String sql = "SELECT * FROM tbl_room WHERE room_id = '" + roomId +"'";
//                Statement stmt;
//                stmt = conn.createStatement();
//                ResultSet rs = stmt.executeQuery(sql);
//                while (rs.next()) {
//                    String room_id = rs.getString("room_id");
//                    String description = rs.getString("description");
//                    String capacity = rs.getString("capacity");
//                    String room_type = rs.getString("room_type");
//
//                    data = "1. Room ID: " + room_id +
//                            "\n2. Description: " + description +
//                            "\n3. Max. Capacity: " + capacity +
//                            "\n4. Room Type: " + room_type;
//                }
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return data;
//    }

//    public static String searchRoom(String room_id){
//        String response = "";
//        String sql = "SELECT * FROM tbl_room WHERE room_id = '"+ room_id +"'";
//        try {
//            Connection conn = SQLite.connect();
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            if (rs.getString("room_id")!=null) {
//                response = "Available";
//            } else {
//                response = "No";
//            }
//            conn.close();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return  response;
//    }



//    public static String updateDisplay(String roomId) {
//        String data = "";
//        String sql = "SELECT * FROM tbl_room WHERE school_id='"+ School.getSchoolId() +"'";
//        Connection conn = SQLite.connect();
//        try {
//            if (conn != null) {
//                Statement stmt;
//                stmt = conn.createStatement();
//                ResultSet rs = stmt.executeQuery(sql);
//
//                while (rs.next()) {
//                    String room_id = rs.getString("room_id");
//                    String description = rs.getString("description");
//                    String capacity = rs.getString("capacity");
//                    String room_type_id = rs.getString("room_type_id");
//
//                    data = data +
//                            "\nReply 1 : Room ID" + room_id +
//                            "\nReply 2 : Description: " + description +
//                            "\nReply 3 : Capacity: " + capacity +
//                            "\nReply 4 : Room Type ID: " + room_type_id;
//                }
//                conn.close();
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return data;
//    }
}