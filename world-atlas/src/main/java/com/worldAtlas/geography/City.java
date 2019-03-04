package com.worldAtlas.geography;

import com.worldAtlas.transport.TravelTo;

public class City extends GeographicalLocation implements Comparable<City> {
	
	private int country;	

	public City(int id, String name, int population, int country) {
		super(id, name, population);
		this.country = country;
	}
	public int getCountry() {
		return country;
	}
	@Override
	public String toString() {
		return this.getName() + ", a city in " + country + "\n\tPopulation: " + this.getPopulation() + "\n";
	}
	
	@Override
	public int compareTo(City c) {
		if (c.getPopulation() - this.getPopulation()!= 0) {
			return c.getPopulation() - this.getPopulation();
		} else {
			return this.getId() - c.getId();
		}
		
	}
	
	public String travelTo(City city, TravelTo<City> t) {
		return t.printJourney(city);
	}
	
	
}
