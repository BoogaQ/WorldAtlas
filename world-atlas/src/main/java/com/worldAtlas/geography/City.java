package com.worldAtlas.geography;

public class City extends GeographicalLocation implements Comparable<City> {
	
	private Country country;	

	public City(int id, String name, int population, Country country) {
		super(id, name, population);
		this.country = country;
	}
	public Country getCountry() {
		return country;
	}
	@Override
	public String toString() {
		return "\n" + this.getId() + " : " + this.getName() + "\n\tPopulation: " + this.getPopulation() + "\n";
	}
	
	@Override
	public int compareTo(City c) {
		if (c.getPopulation() - this.getPopulation()!= 0) {
			return c.getPopulation() - this.getPopulation();
		} else {
			return this.getId() - c.getId();
		}
	}

}
