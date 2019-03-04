package com.worldAtlas.geography;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class Country extends GeographicalLocation implements Comparable<Country> {
	private int capital;
	private Set<City> cities;
	private Set<Integer> bordering;
	
	public Country(int id, String name, int capital) {
		super(id, name);
		cities = new TreeSet<City>();
		this.capital = capital;
		this.bordering = new TreeSet<Integer>();
	}
	
	public int getCapital() {
		return capital;
	}
	
	public Set<City> getCities() {
		return cities;
	}
	public void addCity(City c) {
		cities.add(c);
	}
	public Set<Integer> getBordering() {
		return bordering;
	}
	public void addBordering(Country n) {
		bordering.add(n.getId());
	}
	public void addBordering(Integer n) {
		bordering.add(n);
	}
	public void addBorderingMany(Collection<Integer> n) {
		bordering.addAll(n);
	}
	public boolean isBordering(Country c) {
		if (bordering.contains(c.getId())) {
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
		s.append(this.getName());
		s.append("\nPopulation: " + this.getPopulation());
		s.append("\nCities: ");
		for (City c : cities) {
			s.append("\n\t" + c.toString());
		}
		s.append(bordering);
		return s.toString();
	}
}
