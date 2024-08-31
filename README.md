# Hotel-Management-System
# Hotel Booking System UI class:
A Java-based graphical user interface for a hotel booking system.

Description
This Java application provides a user-friendly interface for customers to book hotel rooms. It allows users to select a room, enter their name, check-in and check-out dates, and calculate the total amount due. The system also enables users to make a reservation and displays a success message upon completion.

Features
Room selection from a list of available rooms
Customer name input
Check-in and check-out date input
Total amount calculation based on room price and stay duration
Reservation functionality with success message
Technical Details
Built using Java and the Swing library for GUI components
Utilizes JDBC for database interactions (not included in this repository)
Implements event listeners for button clicks and user input
Getting Started
Clone the repository to your local machine.
Compile the Java class using your preferred IDE or command-line compiler.
Run the HotelBookingSystemUI class to launch the application.
Note
This repository only includes the GUI component of the hotel booking system. The database interactions and room management logic are not included. You will need to implement these components separately to complete the system.
# Reservation Management class:
A Java class for managing hotel reservations.

Description
This class provides a method for making a hotel reservation, which involves inserting a new record into the reservations table in the database. It also updates the room availability by marking the reserved room as unavailable.

Features
Creates a new reservation in the database
Updates room availability to mark the reserved room as unavailable
Technical Details
Built using Java and JDBC for database interactions
Utilizes a PreparedStatement to execute an SQL INSERT statement
Calls the updateRoomAvailability method from the RoomManagement class to update the room status
Getting Started
Ensure you have a database connection established through the DatabaseConnection class.
Use the makeReservation method to create a new reservation, passing in the required parameters (room ID, customer name, check-in date, check-out date, and total amount).
Note
This class assumes a database connection is established and the reservations table exists in the database. You will need to create the database schema and implement the DatabaseConnection class separately.
# Database Connection class:
A Java class for establishing a database connection.

Description
This class provides a method for establishing a connection to a MySQL database, which is used by the hotel booking system.

Features
Establishes a connection to a MySQL database
Returns a Connection object for use in database interactions
Technical Details
Built using Java and JDBC for database interactions
Utilizes the DriverManager class to establish a connection to the database
Hardcoded database URL, username, and password (change as per your DB credentials)
Getting Started
Ensure you have a MySQL database set up with the correct URL, username, and password.
Use the getConnection method to establish a connection to the database.
Note
This class assumes a MySQL database is set up and the credentials are correct. You will need to change the hardcoded values to match your database setup.
# Room Management class:
A Java class for managing hotel rooms.

Description
This class provides methods for retrieving available rooms and updating room availability.

Features
Retrieves a list of available rooms from the database
Updates the availability status of a room in the database
Technical Details
Built using Java and JDBC for database interactions
Utilizes PreparedStatement objects to execute SQL queries
Calls the getConnection method from the DatabaseConnection class to establish a database connection
Getting Started
Ensure you have a database connection established through the DatabaseConnection class.
Use the getAvailableRooms method to retrieve a list of available rooms.
Use the updateRoomAvailability method to update the availability status of a room.
Note
This class assumes a database connection is established and the rooms table exists in the database with an is_available column. You will need to create the database schema and implement the DatabaseConnection class separately.
