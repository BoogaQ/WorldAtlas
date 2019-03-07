package com.worldAtlas.transport;

import java.util.ArrayList;
import java.util.List;

import com.worldAtlas.geography.GeographicalLocation;

public class Journey implements Comparable<Journey> {
	private List<String> legs = new ArrayList<String>();
	private int airplaneHops = 0;
	public Journey() {		
		
	}	
	
	public List<String> getJourney() {
		return legs;
	}
	
	public <T extends GeographicalLocation> void addLeg(TravelTo<T> strategy, T from, T to) {
		if (strategy instanceof CityPlaneTravel) {
			airplaneHops += 1;
		}
		legs.add(strategy.printJourney(from, to));
	}
	public List<String> getLegs() {
		return legs;
	}
	public int getAirplaneHops() {
		return airplaneHops;
	}
	
	@Override
	public int compareTo(Journey j) {
		if (getLegs().size() == j.getLegs().size()) {
			return j.getAirplaneHops() - this.airplaneHops;
		} else {
			return getLegs().size() - j.getLegs().size();
		}
	}
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("--------------------");
		for (String leg : legs) {
			s.append("\n" + leg);
		}	
		s.append("--------------------");
		return s.toString();
	}
}
