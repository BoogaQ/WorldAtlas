package com.worldAtlas.transport;

import com.worldAtlas.geography.GeographicalLocation;

public class CityTrainTravel<T extends GeographicalLocation> implements TravelTo<T> {
	public String printJourney(T source, T destination) {
		return "Travel from " + source.getName() + " to " + destination.getName() + " by Train.";
	}
}
