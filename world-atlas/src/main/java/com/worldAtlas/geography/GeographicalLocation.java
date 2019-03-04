package com.worldAtlas.geography;

public class GeographicalLocation{
	
	private int id;
	private String name;
	private int population;

	
	public GeographicalLocation(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public GeographicalLocation(int id, String name, int population) {
		this.id = id;
		this.name = name;
		this.population = population;
	}
	
	public int getId() {
		return id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public int getPopulation() {
		return population;
	}
}
