package com.worldAtlas.transport;

import com.worldAtlas.geography.Capital;

public class CityPlaneTravel<T extends Capital> implements TravelTo<T> {
	public String printJourney(T source, T destination) {
		return "Travel from " + source.getName() + " to " + destination.getName() + " by Plane.";
	}
}
