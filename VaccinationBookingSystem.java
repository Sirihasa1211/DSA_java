/*
Problem Statement:
Build a simulation of a vaccination slot booking system.
Users should be able to:
1. View available centers and time slots.
2. Book a slot if available.
3. Prevent double bookings and update slot availability.

This reflects real-world applications like CoWIN portal in India.
*/

import java.util.*;

class Slot {
    String center;
    String time;
    boolean isBooked;

    Slot(String center, String time) {
        this.center = center;
        this.time = time;
        this.isBooked = false;
    }

    void display() {
        System.out.println(center + " - " + time + (isBooked ? " [Booked]" : " [Available]"));
    }
}

public class VaccinationBookingSystem {
    static List<Slot> slots = new ArrayList<>();

    public static void main(String[] args) {
        slots.add(new Slot("City Hospital", "10:00 AM"));
        slots.add(new Slot("Green Clinic", "11:00 AM"));
        slots.add(new Slot("Health Point", "01:00 PM"));

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Vaccination Slot Booking ---");
            System.out.println("1. View Slots\n2. Book Slot\n3. Exit");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    for (int i = 0; i < slots.size(); i++) {
                        System.out.print((i + 1) + ". ");
                        slots.get(i).display();
                    }
                    break;
                case 2:
                    System.out.print("Enter slot number to book: ");
                    int slotNum = sc.nextInt() - 1;
                    if (slotNum >= 0 && slotNum < slots.size()) {
                        if (!slots.get(slotNum).isBooked) {
                            slots.get(slotNum).isBooked = true;
                            System.out.println("Slot booked successfully!");
                        } else {
                            System.out.println("Slot already booked.");
                        }
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;
                case 3:
                    System.out.println("Stay safe!");
                    break;
                default:
                    System.out.println("Invalid input.");
            }
        } while (choice != 3);
    }
}
