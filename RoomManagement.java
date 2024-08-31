import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomManagement {

    public static ResultSet getAvailableRooms() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM rooms WHERE is_available = TRUE";
        PreparedStatement statement = conn.prepareStatement(sql);
        return statement.executeQuery();
    }

    public static void updateRoomAvailability(int roomId, boolean isAvailable) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE rooms SET is_available = ? WHERE room_id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setBoolean(1, isAvailable);
        statement.setInt(2, roomId);
        statement.executeUpdate();
    }
}
