package main.java;

public class Hotel {
	private String name;
    private double regularWeekdayRate;
    private double regularWeekendRate;
    private double rewardsWeekdayRate;
    private double rewardsWeekendRate;
    private int rating;

    public Hotel(String name, double regularWeekdayRate, double regularWeekendRate,
                 double rewardsWeekdayRate, double rewardsWeekendRate, int rating) {
        this.name = name;
        this.regularWeekdayRate = regularWeekdayRate;
        this.regularWeekendRate = regularWeekendRate;
        this.rewardsWeekdayRate = rewardsWeekdayRate;
        this.rewardsWeekendRate = rewardsWeekendRate;
        this.rating = rating;
    }


    public String getName() {
        return name;
    }

    public double getRegularWeekdayRate() {
        return regularWeekdayRate;
    }

    public double getRegularWeekendRate() {
        return regularWeekendRate;
    }

    public double getRewardsWeekdayRate() {
        return rewardsWeekdayRate;
    }

    public double getRewardsWeekendRate() {
        return rewardsWeekendRate;
    }

    public int getRating() {
        return rating;
    }

    public String toString() {
        return "Hotel{name='" + name + "', rating=" + rating + "}";
    }
    
}
