package com.worldAtlas.transport;

import com.worldAtlas.geography.City;

public interface TravelTo<T extends City> {
	public String printJourney(T destination);
}
