package main.java;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Comparator;
import java.util.stream.*;

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

	public String findCheapestHotel(String customerType, List<LocalDate> dates) throws IllegalArgumentException {
		validateInputs(customerType, dates);
	    return hotels.stream()
	            .min(Comparator.comparingDouble((Hotel hotel) -> calculateTotalCost(hotel, customerType, dates))
	                    .thenComparing(Hotel::getRating, Comparator.reverseOrder())) 
	            .map(Hotel::getName)
	            .orElse("No hotels available");
	}

	 private void validateInputs(String customerType, List<LocalDate> dates) {
	        if (!customerType.equalsIgnoreCase("Regular") && !customerType.equalsIgnoreCase("Rewards")) {
	            throw new IllegalArgumentException("Invalid customer type. Valid options are 'Regular' or 'Rewards'.");
	        }

	        if (dates == null || dates.isEmpty()) {
	            throw new IllegalArgumentException("Date range cannot be null or empty.");
	        }

	        for (LocalDate date : dates) {
	            if (date == null) {
	                throw new IllegalArgumentException("Date range contains invalid dates.");
	            }
	        }
	    }
	 
	public List<String> findCheapestHotelsByWeekdayAndWeekendRates(String customerType, List<LocalDate> dates) {
        double minCost = hotels.stream()
                .mapToDouble(hotel -> calculateTotalCost(hotel, customerType, dates))
                .min()
                .orElse(Double.MAX_VALUE);

        return hotels.stream()
                .filter(hotel -> calculateTotalCost(hotel, customerType, dates) == minCost)
                .map(hotel -> hotel.getName() + " (Total Cost: $" + calculateTotalCost(hotel, customerType, dates) + ")")
                .collect(Collectors.toList());
    }

	public String findBestRatedHotel(String customerType, List<LocalDate> dates) {
        Optional<Hotel> bestRatedHotel = hotels.stream()
                .max((hotel1, hotel2) -> Integer.compare(hotel1.getRating(), hotel2.getRating()));

        if (bestRatedHotel.isPresent()) {
            Hotel hotel = bestRatedHotel.get();
            double totalCost = calculateTotalCost(hotel, customerType, dates);
            return hotel.getName() + " (Total Cost: $" + totalCost + ", Rating: " + hotel.getRating() + ")";
        } else {
            return "No hotels available";
        }
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
