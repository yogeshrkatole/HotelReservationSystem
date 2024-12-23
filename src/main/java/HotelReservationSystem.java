package main.java;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
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

	public String findCheapestHotelByRatesAndRating(String customerType, String dateInput) throws IllegalArgumentException {
		validateInputs(customerType, dateInput);
		List<LocalDate> dates = parseDates(dateInput);
		double minCost = hotels.stream()
                .mapToDouble(hotel -> calculateTotalCost(hotel, customerType, dates))
                .min()
                .orElse(Double.MAX_VALUE);

        Optional<Hotel> cheapestBestRatedHotel = hotels.stream()
                .filter(hotel -> calculateTotalCost(hotel, customerType, dates) == minCost)
                .max((hotel1, hotel2) -> Integer.compare(hotel1.getRating(), hotel2.getRating()));

        if (cheapestBestRatedHotel.isPresent()) {
            Hotel hotel = cheapestBestRatedHotel.get();
            double totalCost = calculateTotalCost(hotel, customerType, dates);
            return hotel.getName() + " (Rating: " + hotel.getRating() + ", Total Rates: $" + totalCost + ")";
        } else {
            return "No Hotels Available";
        }
	}

	 private void validateInputs(String customerType, String dateInput) {
	        if (!customerType.equalsIgnoreCase("Regular") && !customerType.equalsIgnoreCase("Rewards")) {
	            throw new IllegalArgumentException("Invalid customer type. Valid options are 'Regular' or 'Rewards'.");
	        }

	        if (dateInput == null || dateInput.isEmpty()) {
	            throw new IllegalArgumentException("Date range cannot be null or empty.");
	        }

	        Pattern datePattern = Pattern.compile("\\d{2}[A-Za-z]{3}\\d{4}");
	        for (String date : dateInput.split(",")) {
	            if (!datePattern.matcher(date.trim()).matches()) {
	                throw new IllegalArgumentException("Invalid Date Format. Please Use The Format 'ddMMMyyyy' (E.g., 11Sep2020). ");
	            }
	        }
	    }
	 
	public List<String> findCheapestHotelsByRates(String customerType, String dateInput) {
		validateInputs(customerType, dateInput);
		List<LocalDate> dates = parseDates(dateInput);
        double minCost = hotels.stream()
                .mapToDouble(hotel -> calculateTotalCost(hotel, customerType, dates))
                .min()
                .orElse(Double.MAX_VALUE);

        return hotels.stream()
                .filter(hotel -> calculateTotalCost(hotel, customerType, dates) == minCost)
                .map(hotel -> hotel.getName() + " (Total Cost: $" + calculateTotalCost(hotel, customerType, dates) + ")")
                .collect(Collectors.toList());
    }

	public String findBestRatedHotel(String customerType, String dateInput) {
		validateInputs(customerType, dateInput);
		List<LocalDate> dates = parseDates(dateInput);
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
	
	public static List<LocalDate> parseDates(String dateInput) {
        List<LocalDate> dates = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy");

        try {
            for (String date : dateInput.split(",")) {
                dates.add(LocalDate.parse(date.trim(), formatter));
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid Date Format. Please Use The Format 'ddMMMyyyy' (E.g., 11Sep2020). ");
        }

        return dates;
    }
	
	
}
