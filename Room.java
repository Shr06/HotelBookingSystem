package models;

public class Room {
    private int roomNumber;
    private String roomType;
    private boolean isAvailable;
    private double pricePerNight;

    public Room(int roomNumber, String roomType, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.isAvailable = true;
    }

    public int getRoomNumber() { return roomNumber; }
    public String getRoomType() { return roomType; }
    public boolean isAvailable() { return isAvailable; }
    public double getPricePerNight() { return pricePerNight; }

    public void bookRoom() { this.isAvailable = false; }
    public void freeRoom() { this.isAvailable = true; }

    @Override
    public String toString() {
        return "Room " + roomNumber + " | Type: " + roomType + " | ₹" + pricePerNight + " | Available: " + isAvailable;
    }
}
