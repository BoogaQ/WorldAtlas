package com.worldAtlas.transport;

import com.worldAtlas.geography.GeographicalLocation;

public class NoRouteFound<T extends GeographicalLocation> implements TravelTo<T> {
	public String printJourney(T source, T destination) {
		return "Couldn't find route between " + source + " and " + destination + ".";
	}
}
