import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReservationManagement {

    public static void makeReservation(int roomId, String customerName, String checkInDate, String checkOutDate, double totalAmount) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "INSERT INTO reservations (room_id, customer_name, check_in_date, check_out_date, total_amount) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, roomId);
        statement.setString(2, customerName);
        statement.setString(3, checkInDate);
        statement.setString(4, checkOutDate);
        statement.setDouble(5, totalAmount);
        statement.executeUpdate();

        RoomManagement.updateRoomAvailability(roomId, false); // Mark room as unavailable
    }
}

