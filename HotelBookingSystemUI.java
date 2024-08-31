import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HotelBookingSystemUI {
    private JFrame frame;
    private JComboBox<String> roomComboBox;
    private JTextField customerNameField;
    private JTextField checkInDateField;
    private JTextField checkOutDateField;
    private JLabel totalAmountLabel;

    public static void main(String[] args) {
        HotelBookingSystemUI app = new HotelBookingSystemUI();
        app.createUI();
    }

    public void createUI() {
        frame = new JFrame("Hotel Booking System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel roomLabel = new JLabel("Select Room:");
        roomLabel.setBounds(10, 20, 100, 25);
        panel.add(roomLabel);

        roomComboBox = new JComboBox<>();
        loadAvailableRooms();
        roomComboBox.setBounds(120, 20, 160, 25);
        panel.add(roomComboBox);

        JLabel customerNameLabel = new JLabel("Customer Name:");
        customerNameLabel.setBounds(10, 50, 100, 25);
        panel.add(customerNameLabel);

        customerNameField = new JTextField(20);
        customerNameField.setBounds(120, 50, 160, 25);
        panel.add(customerNameField);

        JLabel checkInLabel = new JLabel("Check-in Date:");
        checkInLabel.setBounds(10, 80, 100, 25);
        panel.add(checkInLabel);

        checkInDateField = new JTextField(20);
        checkInDateField.setBounds(120, 80, 160, 25);
        panel.add(checkInDateField);

        JLabel checkOutLabel = new JLabel("Check-out Date:");
        checkOutLabel.setBounds(10, 110, 100, 25);
        panel.add(checkOutLabel);

        checkOutDateField = new JTextField(20);
        checkOutDateField.setBounds(120, 110, 160, 25);
        panel.add(checkOutDateField);

        JButton calculateButton = new JButton("Calculate Total");
        calculateButton.setBounds(10, 140, 150, 25);
        panel.add(calculateButton);

        totalAmountLabel = new JLabel("Total: $0.00");
        totalAmountLabel.setBounds(10, 170, 150, 25);
        panel.add(totalAmountLabel);

        JButton reserveButton = new JButton("Reserve");
        reserveButton.setBounds(10, 200, 150, 25);
        panel.add(reserveButton);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTotal();
            }
        });

        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    makeReservation();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
                }
            }
        });
    }

    private void loadAvailableRooms() {
        try {
            ResultSet rs = RoomManagement.getAvailableRooms();
            while (rs.next()) {
                roomComboBox.addItem(rs.getString("room_number") + " - " + rs.getString("room_type") + " - $" + rs.getDouble("price_per_night"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error loading rooms: " + e.getMessage());
        }
    }

    private void calculateTotal() {
        String roomInfo = (String) roomComboBox.getSelectedItem();
        if (roomInfo != null) {
            String[] parts = roomInfo.split(" - ");
            double pricePerNight = Double.parseDouble(parts[2].substring(1));

            // For simplicity, we are not handling date parsing. Assume 1 day stay.
            totalAmountLabel.setText("Total: $" + pricePerNight);
        }
    }

    private void makeReservation() throws SQLException {
        String roomInfo = (String) roomComboBox.getSelectedItem();
        if (roomInfo != null) {
            String[] parts = roomInfo.split(" - ");
            int roomNumber = Integer.parseInt(parts[0]);

            String customerName = customerNameField.getText();
            String checkInDate = checkInDateField.getText();
            String checkOutDate = checkOutDateField.getText();
            double totalAmount = Double.parseDouble(totalAmountLabel.getText().substring(8));

            // Fetch room ID from room number
            ResultSet rs = RoomManagement.getAvailableRooms();
            int roomId = -1;
            while (rs.next()) {
                if (rs.getInt("room_number") == roomNumber) {
                    roomId = rs.getInt("room_id");
                    break;
                }
            }

            if (roomId != -1) {
                ReservationManagement.makeReservation(roomId, customerName, checkInDate, checkOutDate, totalAmount);
                JOptionPane.showMessageDialog(frame, "Reservation successful!");
            } else {
                JOptionPane.showMessageDialog(frame, "Room not found!");
            }
        }
    }
}
