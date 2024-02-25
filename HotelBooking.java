import java.util.*;

class Room {
    int roomNumber;
    String roomType;
    double price;
    boolean availability;

    public Room(String roomType, int roomNumber, int price, boolean availability) {
        this.roomType = roomType;
        this.roomNumber = roomNumber;
        this.price = price;
        this.availability = availability;
    }
}

class Guest {
    String name;
    String email;
    int contact;
    int noOfPersons;
    String Checkin;
    String Checkout;
    
    public Guest(String name, String email, int contact, int noOfPersons,String Checkin,String Checkout) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.noOfPersons = noOfPersons;
        this.Checkin = Checkin;
        this.Checkout = Checkout;
    }
}

class Reservation {
    Room room;
    Guest guest;
    

    public Reservation(Room room, Guest guest) {
        this.room = room;
        this.guest = guest;
    }
}

class HotelBooking {
    private List<Reservation> reservations = new ArrayList<>();

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public void printInvoice(String customerName) {
        System.out.println("INVOICE*");
        System.out.println("*Hotel ABC*\n");

        boolean found = false;

        for (Reservation reservation : reservations) {
            if (reservation.guest.name.equals(customerName)) {
                found = true;
                System.out.println("customer name:" + reservation.guest.name);
                System.out.println("customer email:" + reservation.guest.email);
                System.out.println("customer phone number:" + reservation.guest.contact);
                System.out.println("Booked Room :" + reservation.room.roomType);
                System.out.println("Room price:" + reservation.room.price);
                System.out.println("No of persons:" + reservation.guest.noOfPersons);
                System.out.println("Check In date :" + reservation.guest.Checkin);
                System.out.println("Check Out date :" + reservation.guest.Checkout);
                System.out.println("-----------------------------------------------");
            }
        }

        if (!found) {
            System.out.println("No reservations found for the customer: " + customerName);
        }
    }

    public void cancelReservation(String customerName) {
        for (Reservation reservation : reservations) {
            if (reservation.guest.name.equals(customerName)) {
                reservation.room.availability = true; // Mark the room as available again
                reservations.remove(reservation); // Remove the reservation from the list
                System.out.println("Reservation canceled successfully for " + customerName);
                return;
            }
        }
        System.out.println("No reservations found for the customer: " + customerName);
    }

    private static Room findAvailableRoom(String roomType, Room... rooms) {
        for (Room room : rooms) {
            if (room.roomType.equals(roomType) && room.availability) {
                return room;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int txn;
        Scanner sc = new Scanner(System.in);

        HotelBooking bookingSystem = new HotelBooking();

        Room r1 = new Room("Deluxe", 101, 2500, true);
        Room r2 = new Room("Deluxe", 102, 2500, true);
        Room r3 = new Room("Deluxe", 103, 2500, true);
        Room r4 = new Room("SuperDeluxe", 104, 3500, true);
        Room r5 = new Room("SuperDeluxe", 105, 3500, true);
        Room r6 = new Room("SuperDeluxe", 106, 3500, true);

        do {
            System.out.println("Welcome to ABC hotel*");
            System.out.println("1. Book Room\n2. Print invoice\n3. Cancellation\n");
            System.out.println("Enter your choice:");
            int ch = sc.nextInt();
            Reservation x = null;

            switch (ch) {
                case 1:
                    System.out.println("Enter guest name:");
                    String a = sc.next();
                    System.out.println("Enter guest email:");
                    String b = sc.next();
                    System.out.println("Enter guest contact:");
                    int c = sc.nextInt();
                    System.out.println("Enter no of persons:");
                    int d = sc.nextInt();
                    System.out.println("Enter checkin date:");
                    String e = sc.next();
                    System.out.println("Enter checkout date:");
                    String f = sc.next();
                    System.out.println("Enter the type of room:");
                    String type = sc.next();
                    Guest g1 = new Guest(a, b, c,d,e,f);

                    // Find the first available room with the specified room type
                    Room selectedRoom = findAvailableRoom(type, r1, r2, r3, r4, r5, r6);

                    if (selectedRoom != null) {
                        x = new Reservation(selectedRoom, g1);
                        selectedRoom.availability = false; // Mark the room as booked
                        bookingSystem.addReservation(x); // Add reservation to the list
                        System.out.println("Reservation successful!");
                    } else {
                        System.out.println("No available rooms for the specified type.");
                    }
                    break;
                case 2:
                    System.out.println("Enter customer name to print invoice:");
                    String customerName = sc.next();
                    bookingSystem.printInvoice(customerName);
                    break;
                case 3:
                    System.out.println("Enter customer name to cancel reservation:");
                    String cancelName = sc.next();
                    bookingSystem.cancelReservation(cancelName);
                    break;
            }
            System.out.println("Want to do another process (1/0):");
            txn = sc.nextInt();
        } while (txn == 1);
    }
}