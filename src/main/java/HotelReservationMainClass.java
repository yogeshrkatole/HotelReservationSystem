package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HotelReservationMainClass {
public static void main(String[] args) {
	System.out.println("Welcome to Hotel Reservation Program");
	
	HotelReservationSystem system = new HotelReservationSystem();
	
	system.addHotel("Lakewood", 110, 90, 80, 80, 3);
    system.addHotel("Bridgewood", 150, 50, 110, 50, 4);
    system.addHotel("Ridgewood", 220, 150, 100, 40, 5);
    HotelReservationMainClass.showHotels(system);
	Scanner scanner = new Scanner(System.in);

	System.out.print("Enter any one customer type (Regular/Rewards): ");
    String customerType = scanner.nextLine();

    System.out.print("Enter dates like (26Mar2009,27Mar2009,28Mar2009): ");
    String dateInput = scanner.nextLine();

    List<LocalDate> dates = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy");
    for (String date : dateInput.split(",")) {
        dates.add(LocalDate.parse(date.trim(), formatter));
    }
    
    System.out.println("Cheapest Hotels By Total Rates Of Weekday and Weekend");
    List<String> cheapestHotelsByRates=new ArrayList<String>();
    cheapestHotelsByRates = system.findCheapestHotelsByWeekdayAndWeekendRates(customerType, dates);
    for(int i=0;i<cheapestHotelsByRates.size();i++) {
    	System.out.println(cheapestHotelsByRates.get(i));
    }

    
    String cheapestHotel = system.findCheapestHotel(customerType, dates);
    Double totalRates=0.0;
    int rating=0;
    for(int i=0;i<system.getHotels().size();i++) {
    	if(cheapestHotel.equalsIgnoreCase(system.getHotels().get(i).getName())) {
    		totalRates = system.calculateTotalCost(system.getHotels().get(i), customerType, dates);
    		rating=system.getHotels().get(i).getRating();
    	}
    }
    System.out.println("Cheapest Hotels By Rates And Rating");
    System.out.println("Cheapest Hotel: " + cheapestHotel+", Rating:"+rating+", Total Rates:$"+totalRates);
    
    scanner.close();
}
private static void showHotels(HotelReservationSystem system) {
for(int i=0;i<system.getHotels().size();i++) {
	System.out.println("Hotel Name:"+system.getHotels().get(i).getName()+", Regular Weekday Rates:"+system.getHotels().get(i).getRegularWeekdayRate()+", Regular Weekend Rates:"+system.getHotels().get(i).getRegularWeekendRate()+",Rating:"+system.getHotels().get(i).getRating());
}
}
}
