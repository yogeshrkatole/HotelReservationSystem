package main.java;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class HotelReservationSystem {
	private List<Hotel> hotels;

	public List<Hotel> getHotels() {
		return hotels;
	}

	public HotelReservationSystem() {
		this.hotels = new ArrayList<>();
	}

	public void addHotel(String name, double regularWeekdayRate, double regularWeekendRate, double rewardsWeekdayRate,
			double rewardsWeekendRate, int rating) {
		Hotel hotel = new Hotel(name, regularWeekdayRate, regularWeekendRate, rewardsWeekdayRate, rewardsWeekendRate,
				rating);
		hotels.add(hotel);
		System.out.println("Hotel added: " + name);
	}

	public void displayHotels() {
		for (Hotel hotel : hotels) {
			System.out.println(hotel);
		}
	}

	public String findCheapestHotel(String customerType, List<LocalDate> dates) {
	    return hotels.stream()
	            .min(Comparator.comparingDouble((Hotel hotel) -> calculateTotalCost(hotel, customerType, dates))
	                    .thenComparing(Hotel::getRating, Comparator.reverseOrder())) 
	            .map(Hotel::getName)
	            .orElse("No hotels available");
	}


	public double calculateTotalCost(Hotel hotel, String customerType, List<LocalDate> dates) {
		double totalCost = 0;
		boolean isRegular = customerType.equalsIgnoreCase("Regular");

		for (LocalDate date : dates) {
			DayOfWeek day = date.getDayOfWeek();
			boolean isWeekend = (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY);

			if (isRegular) {
				totalCost += isWeekend ? hotel.getRegularWeekendRate() : hotel.getRegularWeekdayRate();
			} else {
				totalCost += isWeekend ? hotel.getRewardsWeekendRate() : hotel.getRewardsWeekdayRate();
			}
		}
		return totalCost;
	}
	
	
	
}
