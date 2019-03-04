package com.worldAtlas.transport;

import com.worldAtlas.geography.Capital;

public class TakePlaneTo implements TravelTo<Capital> {
	public String printJourney(Capital destination) {
		return " traveling to " + destination.getName();
	}
}
