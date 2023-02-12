package my.uum;
import java.sql.*;

/**
 * This class is for SQLite database to select, insert, and delete the data
 */
public class SQLite extends s281895_A221_bot {

    /**
     * This method is to connect the telegram bot with SQLite database
     * @return tbl_user and tbl_room in the database
     */
    public static Connection connect() {
        Connection conn = null;
        String url = "jdbc:sqlite:C:\\Users\\hngzi\\IdeaProjects\\assignment-2-HngZiLing\\db\\telegramBot.db";
        try {
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS tbl_user (booking_id INTEGER PRIMARY KEY AUTOINCREMENT, ic_no TEXT NOT NULL, staff_id TEXT NOT NULL, name TEXT NOT NULL, mobile_telNo TEXT NOT NULL, email TEXT NOT NULL, purpose TEXT NOT NULL, booking_date TEXT NOT NULL, booking_time TEXT NOT NULL)";
            stmt.execute(sql);
            sql = "CREATE TABLE IF NOT EXISTS tbl_room (room_id INTEGER PRIMARY KEY AUTOINCREMENT, staff_id TEXT NOT NULL, description TEXT NOT NULL, capacity INT NOT NULL, date TEXT NOT NULL, time TEXT NOT NULL)";
            stmt.execute(sql);
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * This method is to insert the data including ic, staffId, fullName, telNo, email, purpose, bookingDate and bookingTime to the tbl_user in database
     *
     * @param ic          User IC number with 14 digit including '-'
     * @param staffId     User 6 digit identity number
     * @param fullName    User full name include first name and last name
     * @param telNo       User 10 or 11 digit telephone number
     * @param email       User email address
     * @param purpose     User purpose for booking meeting room
     * @param bookingDate The date booked by the user
     * @param bookingTime The time booked by the user
     */
    public static void insert(String ic, String staffId, String fullName, String telNo, String email, String purpose, String bookingDate, String bookingTime) {
        try {
            Connection conn = SQLite.connect();
            String sql = "INSERT INTO tbl_user(booking_id, ic_no, staff_id, name, mobile_telNo, email, purpose, booking_date, booking_time) VALUES (?,?,?,?,?,?,?,?,?)";
            if (conn != null) {
                PreparedStatement pstmt;
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, null);
                pstmt.setString(2, ic);
                pstmt.setString(3, staffId);
                pstmt.setString(4, fullName);
                pstmt.setString(5, telNo);
                pstmt.setString(6, email);
                pstmt.setString(7, purpose);
                pstmt.setString(8, bookingDate);
                pstmt.setString(9, bookingTime);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is to insert the data including staffId, fullName, description, bookingDate and bookingTime to the tbl_room in database
     *
     * @param staffId The staffId is using to store into tbl_room
     * @param date    The date is using to store into tbl_room
     * @param time    The time is using to store into tbl_room
     */
    public static void insertList(String staffId, String date, String time) {
        try {
            Connection conn = SQLite.connect();
            String sql = "INSERT INTO tbl_room(room_id, staff_id, description, capacity, date, time) VALUES (?,?,?,?,?,?)";
            if (conn != null) {
                String description = "The meeting room provides WiFi, whiteboard, projector, table, chairs, etc.";
                PreparedStatement pstmt;
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, null);
                pstmt.setString(2, staffId);
                pstmt.setString(3, description);
                pstmt.setString(4, "35");
                pstmt.setString(5, date);
                pstmt.setString(6, time);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is to search the data in the database and retrieve it
     *
     * @param staffId User staff id to search the record
     * @return The booking record by using the staff id
     */
    public static String select(String staffId) {
        String data = "";
        String sql = "SELECT * FROM tbl_user WHERE staff_id = " + staffId;
        Connection conn = SQLite.connect();
        try {
            if (conn != null) {
                Statement stmt;
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    int bookingId = rs.getInt("booking_id");
                    String ic_no = rs.getString("ic_no");
                    String staff_id = rs.getString("staff_id");
                    String name = rs.getString("name");
                    String mobile_telNo = rs.getString("mobile_telNo");
                    String email = rs.getString("email");
                    String purpose = rs.getString("purpose");
                    String booking_date = rs.getString("booking_date");
                    String booking_time = rs.getString("booking_time");
                    data = data + "\nBooking ID: " + bookingId +
                            "\nIC no: " + ic_no +
                            "\nStaff ID: " + staff_id +
                            "\nName: " + name +
                            "\nTel no: " + mobile_telNo +
                            "\nEmail: " + email +
                            "\nPurpose: " + purpose +
                            "\nDate: " + booking_date +
                            "\nTime: " + booking_time + "\n";
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    static String selectStaffId;

    /**
     * This method is to select the record using the staff id when the user want to cancel booking record
     *
     * @param bookingId The booking id is using to select the specific record
     * @return the all data relevant with this booking id
     */
    public static String selectRecord(String bookingId) {
        String data = "";
        String sql = "SELECT * FROM tbl_user WHERE booking_id = " + bookingId;
        Connection conn = SQLite.connect();
        try {
            if (conn != null) {
                Statement stmt;
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    bookingId = rs.getString("booking_id");
                    String ic_no = rs.getString("ic_no");
                    selectStaffId = rs.getString("staff_id");
                    String name = rs.getString("name");
                    String mobile_telNo = rs.getString("mobile_telNo");
                    String email = rs.getString("email");
                    String purpose = rs.getString("purpose");
                    String booking_date = rs.getString("booking_date");
                    String booking_time = rs.getString("booking_time");
                    data = data + "\nBooking ID: " + bookingId +
                            "\nIC no: " + ic_no +
                            "\nStaff ID: " + selectStaffId +
                            "\nName: " + name +
                            "\nTel no: " + mobile_telNo +
                            "\nEmail: " + email +
                            "\nPurpose: " + purpose +
                            "\nDate: " + booking_date +
                            "\nTime: " + booking_time + "\n";
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    /**
     * This method is to remove the data in the tbl_user and tbl_room from the database
     * @param bookingId The bookingId is using wo specific a record when there are multiple record using same staffId
     */
    public static void delete(String bookingId) {
        try {
            String sql = "DELETE FROM tbl_user WHERE booking_id = " + bookingId;
            Connection conn = SQLite.connect();
            if (conn != null) {
                Statement stmt;
                stmt = connect().createStatement();
                stmt.executeUpdate(sql);
                sql = "DELETE FROM tbl_room WHERE staff_id = " + selectStaffId;
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is to display all the user info and the meeting room info
     * @return All the info from tbl_user and tbl_room in database
     */
    public static String list() {
        Connection conn = SQLite.connect();
        String list = null;
        try {
            if (conn != null) {
                String sql1 = "SELECT booking_id, ic_no, staff_id, name, mobile_telNo, email, purpose FROM tbl_user";
                Statement stmt;
                stmt = conn.createStatement();
                ResultSet rs1 = stmt.executeQuery(sql1);
                String sql2 = "SELECT room_id, description, capacity, date, time FROM tbl_room";
                stmt = conn.createStatement();
                ResultSet rs2 = stmt.executeQuery(sql2);
                list = "";
                while (rs1.next() && rs2.next()) {
                    String bookingId = rs1.getString("booking_id");
                    String icNo = rs1.getString("ic_no");
                    staffId = rs1.getString("staff_id");
                    String name = rs1.getString("name");
                    String telNo = rs1.getString("mobile_telNo");
                    String email = rs1.getString("email");
                    String purpose = rs1.getString("purpose");
                    list = list + "\nBooking ID: " + bookingId +
                            "\nIc number: " + icNo +
                            "\nStaff ID: " + staffId +
                            "\nStaff name: " + name +
                            "\nTel No: " + telNo +
                            "\nEmail: " + email +
                            "\nPurpose: " + purpose;
                    String roomId = rs2.getString("room_id");
                    String description = rs2.getString("description");
                    String capacity = rs2.getString("capacity");
                    String date = rs2.getString("date");
                    String time = rs2.getString("time");
                    list = list + "\nRoom Id: " + roomId +
                            "\nRoom description: " + description +
                            "\nMaximum capacity: " + capacity +
                            "\nBooking date: " + date +
                            "\nBooking time: " + time + "\n";
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}