package com.worldAtlas.transport;

import java.util.ArrayList;

import com.worldAtlas.app.CountryDirectory;
import com.worldAtlas.geography.City;

public class JourneyPlanner {
	private TravelStrategy travelStrategy;
	private CountryDirectory countryDirectory;
	
	public JourneyPlanner(TravelStrategy ts, CountryDirectory cd) {
		travelStrategy = ts;
		countryDirectory = cd;
	}
	
	public Journey travelTo(String from, String to) {
		City source = countryDirectory.getCity(from);
		City destination = countryDirectory.getCity(to);
		
		if (source == destination) {
			
		}
	}

}
