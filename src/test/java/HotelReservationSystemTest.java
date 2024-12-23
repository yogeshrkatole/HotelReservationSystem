package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.Hotel;
import main.java.HotelReservationSystem;

public class HotelReservationSystemTest {
	
	private HotelReservationSystem system;

    @BeforeEach
    public void addedHotels() {
        system = new HotelReservationSystem();
        system.addHotel("Lakewood", 110, 90, 80, 80, 3);
        system.addHotel("Bridgewood", 160, 60, 110, 50, 4);
        system.addHotel("Ridgewood", 220, 150, 100, 40, 5);
    }
	@Test
	public void testAddHotel() {
		assertEquals("Lakewood", system.getHotels().get(0).getName());
		assertEquals(110, system.getHotels().get(0).getRegularWeekdayRate());
		assertEquals(90, system.getHotels().get(0).getRegularWeekendRate());
		assertEquals(80, system.getHotels().get(0).getRewardsWeekdayRate());
		assertEquals(80, system.getHotels().get(0).getRewardsWeekendRate());
		assertEquals(3, system.getHotels().get(0).getRating());
	}
	

    @Test
    public void testFindCheapestHotelForRegularCustomer() {
        String dateInput="16Mar2020,17Mar2020,18Mar2020";
        String result = system.findCheapestHotelByRatesAndRating("Regular", dateInput);
        assertEquals("Lakewood (Rating: 3, Total Rates: $330.0)", result);
    }

	@Test
    public void testFindCheapestHotelForRewardsCustomer() {
		String dateInput= "26Mar2009,27Mar2009,28Mar2009";
        String result = system.findCheapestHotelByRatesAndRating("Rewards", dateInput);
        assertEquals("Ridgewood (Rating: 5, Total Rates: $240.0)", result);
    }
	
	@Test
	public void testFindBestRatedHotelForRegularCustomer() {
		String dateInput="11Sep2020,12Sep2020";
		String result=system.findBestRatedHotel("Regular", dateInput);
		assertEquals("Ridgewood (Total Cost: $370.0, Rating: 5)", result);
	}
	
	@Test
	public void testFindBestRatedHotelForRewardCustomer() {
		String dateInput="11Sep2020,12Sep2020";
		String result=system.findBestRatedHotel("Rewards", dateInput);
		assertEquals("Ridgewood (Total Cost: $140.0, Rating: 5)", result);
	}
	
}
 