package com.worldAtlas.geography;

import java.util.Objects;

public abstract class GeographicalLocation {
	
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
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}	
		if (o == null) {
			return false;
		}
		if (getClass() != o.getClass())
	        return false;
		GeographicalLocation gl = (GeographicalLocation) o;
		return Objects.equals(this.getId(), gl.getId());
	}
}
