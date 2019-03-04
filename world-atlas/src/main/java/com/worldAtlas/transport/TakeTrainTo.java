package com.worldAtlas.transport;

import com.worldAtlas.geography.City;

public class TakeTrainTo implements TravelTo<City> {
	public String printJourney(City destination) {
		return " traveling to " + destination.getName();
	}
}
