package main.java;

import java.util.ArrayList;
import java.util.List;

public class HotelReservationSystem {
	private List<Hotel> hotels;

    public List<Hotel> getHotels() {
		return hotels;
	}

	public HotelReservationSystem() {
        this.hotels = new ArrayList<>();
    }

    public void addHotel(String name, double regularCustomerRate) {
        Hotel hotel = new Hotel(name, regularCustomerRate);
        hotels.add(hotel);
        System.out.println("Hotel added: " + name);
    }

    public void displayHotels() {
        for (Hotel hotel : hotels) {
            System.out.println("Hotel Name: " + hotel.getName() + ", Regular Customer Rate: $" + hotel.getRegularCustomerRate());
        }
    }
}
