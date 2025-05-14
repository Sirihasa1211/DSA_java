/*
Problem Statement:
Create a console-based Metro Management System that allows users to:
1. View available metro routes.
2. Calculate fare based on source and destination.
3. Book a ticket and generate a receipt.
4. Exit the system.

This program should use object-oriented principles such as classes, objects, inheritance (if needed), and encapsulation.
*/

import java.util.*;

class MetroRoute {
    String source, destination;
    int fare;

    MetroRoute(String source, String destination, int fare) {
        this.source = source;
        this.destination = destination;
        this.fare = fare;
    }

    void displayRoute() {
        System.out.println(source + " -> " + destination + " : Rs." + fare);
    }
}

public class MetroManagementSystem {
    static List<MetroRoute> routes = new ArrayList<>();

    public static void main(String[] args) {
        routes.add(new MetroRoute("A", "B", 20));
        routes.add(new MetroRoute("B", "C", 15));
        routes.add(new MetroRoute("C", "D", 25));

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Metro Management System ---");
            System.out.println("1. View Routes\n2. Book Ticket\n3. Exit");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    for (MetroRoute route : routes) route.displayRoute();
                    break;
                case 2:
                    sc.nextLine();
                    System.out.print("Enter source: ");
                    String src = sc.nextLine();
                    System.out.print("Enter destination: ");
                    String dest = sc.nextLine();
                    boolean found = false;
                    for (MetroRoute route : routes) {
                        if (route.source.equalsIgnoreCase(src) && route.destination.equalsIgnoreCase(dest)) {
                            System.out.println("Fare: Rs." + route.fare);
                            System.out.println("Ticket Booked: " + src + " -> " + dest);
                            found = true;
                        }
                    }
                    if (!found) System.out.println("Route not found.");
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 3);
    }
}
