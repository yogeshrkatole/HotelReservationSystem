package main.java;

import java.util.Scanner;

public class HotelReservationMainClass {
public static void main(String[] args) {
	System.out.println("Welcome to Hotel Reservation Program");
	
	Scanner scanner = new Scanner(System.in);
    HotelReservationSystem system = new HotelReservationSystem();

    System.out.print("Enter hotel name to add: ");
    String name = scanner.nextLine();
    System.out.print("Enter regular customer rate for " + name + ": ");
    double rate = scanner.nextDouble();

    system.addHotel(name, rate);
    system.displayHotels();

    scanner.close();
}
}
