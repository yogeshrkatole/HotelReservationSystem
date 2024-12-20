package main.java;

public class Hotel {
	private String name;
    private double regularCustomerRate;
    
	@Override
	public String toString() {
		return "Hotel [name=" + name + ", regularCustomerRate=" + regularCustomerRate + "]";
	}
	public Hotel(String name, double regularCustomerRate) {
		super();
		this.name = name;
		this.regularCustomerRate = regularCustomerRate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getRegularCustomerRate() {
		return regularCustomerRate;
	}
	public void setRegularCustomerRate(double regularCustomerRate) {
		this.regularCustomerRate = regularCustomerRate;
	}
    
}
