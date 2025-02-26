package main;
import models.*;
import java.util.*;

public class HotelApp {
    private static List<Room> rooms = new ArrayList<>();
    private static List<Booking> bookings = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeRooms(); // Adding sample rooms

        while (true) {
            System.out.println("\n1. View Rooms | 2. Book Room | 3. Cancel Booking | 4. View Bookings | 5. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> viewRooms();
                case 2 -> bookRoom();
                case 3 -> cancelBooking();
                case 4 -> viewBookings();
                case 5 -> { scanner.close(); return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void initializeRooms() {
        rooms.add(new Room(101, "Deluxe", 2500));
        rooms.add(new Room(102, "Suite", 5000));
        rooms.add(new Room(103, "Standard", 1500));
    }

    private static void viewRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println(room);
            }
        }
    }

    private static void bookRoom() {
        System.out.print("Enter Guest Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter Room Number to Book: ");
        int roomNum = scanner.nextInt();
        scanner.nextLine();

        Room room = findRoom(roomNum);
        if (room == null || !room.isAvailable()) {
            System.out.println("Room not available.");
            return;
        }

        System.out.print("Enter Check-In Date (yyyy-mm-dd): ");
        String checkInStr = scanner.nextLine();
        System.out.print("Enter Check-Out Date (yyyy-mm-dd): ");
        String checkOutStr = scanner.nextLine();

        try {
            Date checkIn = new Date(checkInStr);
            Date checkOut = new Date(checkOutStr);
            Booking booking = new Booking(room, new Guest(name, phone, email), checkIn, checkOut);
            bookings.add(booking);
            System.out.println("Booking successful: " + booking);
        } catch (Exception e) {
            System.out.println("Invalid date format.");
        }
    }

    private static void cancelBooking() {
        System.out.print("Enter Guest Name to Cancel Booking: ");
        String guestName = scanner.nextLine();

        Booking foundBooking = null;
        for (Booking booking : bookings) {
            if (booking.getGuest().getName().equalsIgnoreCase(guestName)) {
                foundBooking = booking;
                break;
            }
        }

        if (foundBooking != null) {
            foundBooking.getRoom().freeRoom();
            bookings.remove(foundBooking);
            System.out.println("Booking cancelled.");
        } else {
            System.out.println("Booking not found.");
        }
    }

    private static void viewBookings() {
        System.out.println("\nBookings:");
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }

    private static Room findRoom(int roomNum) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNum) {
                return room;
            }
        }
        return null;
    }
}
