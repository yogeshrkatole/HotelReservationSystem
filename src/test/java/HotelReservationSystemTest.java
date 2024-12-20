package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import main.java.Hotel;
import main.java.HotelReservationSystem;

public class HotelReservationSystemTest {
	@Test
	public void testAddHotel() {
		HotelReservationSystem system = new HotelReservationSystem();
 
		system.addHotel("Lakewood", 100);

		assertEquals("Lakewood", system.getHotels().get(0).getName());
		assertEquals(100, system.getHotels().get(0).getRegularCustomerRate());
	}
}
 