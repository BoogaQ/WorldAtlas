package com.worldAtlas.transport;

import com.worldAtlas.geography.City;
import com.worldAtlas.geography.GeographicalLocation;

public class CityBusTravel<T extends GeographicalLocation> implements TravelTo<City> {
	public String printJourney(City source, City destination) {
		return "Travel from " + source.getName() + " to " + destination.getName() + " by Bus.";
	}
}
