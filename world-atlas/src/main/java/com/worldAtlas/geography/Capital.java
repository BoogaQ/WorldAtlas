package com.worldAtlas.geography;

public class Capital extends City {
	public Capital(int id, String name, int population, Country country) {
		super(id, name, population, country);
	}
	
	@Override
	public String toString() {
		return this.getName() + ", a city in " + this.getCountry().getName() + "\nPopulation: " + this.getPopulation() + "\n";
	}
}
