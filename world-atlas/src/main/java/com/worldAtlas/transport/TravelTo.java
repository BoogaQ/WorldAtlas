package com.worldAtlas.transport;

import com.worldAtlas.geography.GeographicalLocation;

public interface TravelTo<T extends GeographicalLocation> {
	public String printJourney(T source, T destination);
}
