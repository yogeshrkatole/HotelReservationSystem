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
        List<LocalDate> dates = parseDates("16Mar2020", "17Mar2020", "18Mar2020");
        String result = system.findCheapestHotel("Regular", dates);
        assertEquals("Lakewood", result);
    }

	@Test
    public void testFindCheapestHotelForRewardsCustomer() {
        List<LocalDate> dates = parseDates("26Mar2009", "27Mar2009", "28Mar2009");
        String result = system.findCheapestHotel("Rewards", dates);
        assertEquals("Ridgewood", result);
    }

    
    public static List<LocalDate> parseDates(String... dateStrings) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy");
        List<LocalDate> dates = new ArrayList<>();

        for (String dateString : dateStrings) {
            LocalDate date = LocalDate.parse(dateString.trim(), formatter);
            dates.add(date);
        }

        return dates;
    }
}
 