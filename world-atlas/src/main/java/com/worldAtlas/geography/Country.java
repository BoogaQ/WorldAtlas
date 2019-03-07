package com.worldAtlas.geography;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class Country extends GeographicalLocation implements Comparable<Country> {
	private Capital capital;
	private Set<City> cities;
	private Set<Country> bordering;
	
	public Country(int id, String name) {
		super(id, name);
		cities = new TreeSet<City>();
		this.bordering = new TreeSet<Country>();
	}
	
	public Country(int id, String name, Capital capital) {
		super(id, name);
		cities = new TreeSet<City>();
		this.capital = capital;
		this.bordering = new TreeSet<Country>();
	}
	
	public void setCapital(Capital c) {
		capital = c;
	}
	public Capital getCapital() {
		return capital;
	}
	public Set<City> getCities() {
		return cities;
	}
	public void addCity(City c) {
		cities.add(c);
	}
	public Set<Country> getBordering() {
		return bordering;
	}
	public void addBordering(Country n) {
		bordering.add(n);
	}
	public void addBorderingMany(Collection<Country> n) {
		bordering.addAll(n);
	}
	public boolean isBordering(Country c) {
		if (bordering.contains(c)) {
			return true;
		}
		return false;
	}
	@Override
	public int compareTo(Country g) {
		return this.getId() - g.getId();
	}
	@Override
	public int getPopulation() {
		int population = 0;
		for (City c : cities) {
			population += c.getPopulation();
		}
		return population;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("\n" + this.getId() + " : " + this.getName());
		s.append("\nPopulation: " + this.getPopulation());
		s.append("\nCities: ");
		for (City c : cities) {
			s.append("\n\t" + c.toString() + "\n");
		}
		s.append("Bordering countries: ");
		for (Country b : bordering) {
			s.append("|" + b.getName());
		}
		s.append("\n\nCapital City: " + getCapital().getName() + "\n-------------------------");
		return s.toString();
	}
}
