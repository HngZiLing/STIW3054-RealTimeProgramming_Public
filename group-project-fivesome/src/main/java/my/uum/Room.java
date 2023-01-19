package my.uum;

public class Room {
    static String room_id, description;
    static int capacity;

    public static void setRoomId(String room_id) {
        Room.room_id = room_id;
    }

    public static String getRoomId() {
        return Room.room_id;
    }

    public static void setDescription(String description) {
        Room.description = description;
    }

    public static String getDescription() {
        return Room.description;
    }

    public static void setCapacity(int capacity) {
        Room.capacity = capacity;
    }

    public static int getCapacity() {
        return Room.capacity;
    }
}
