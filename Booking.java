package models;
import java.util.Date;

public class Booking {
    private Room room;
    private Guest guest;
    private Date checkInDate;
    private Date checkOutDate;
    private double totalCost;

    public Booking(Room room, Guest guest, Date checkInDate, Date checkOutDate) {
        this.room = room;
        this.guest = guest;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalCost = calculateCost();
        room.bookRoom(); // Mark room as booked
    }

    private double calculateCost() {
        long diff = checkOutDate.getTime() - checkInDate.getTime();
        int days = (int) (diff / (1000 * 60 * 60 * 24));
        return days * room.getPricePerNight();
    }

    public Room getRoom() { return room; }
    public Guest getGuest() { return guest; }
    public Date getCheckInDate() { return checkInDate; }
    public Date getCheckOutDate() { return checkOutDate; }
    public double getTotalCost() { return totalCost; }

    @Override
    public String toString() {
        return guest.getName() + " | Room: " + room.getRoomNumber() + " | ₹" + totalCost;
    }
}
