package com.worldAtlas.transport;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.worldAtlas.app.CountryDirectory;
import com.worldAtlas.geography.Capital;
import com.worldAtlas.geography.City;
import com.worldAtlas.geography.Country;

public class TravelStrategy {
	
	private CountryDirectory countryDirectory;
	
	public TravelStrategy(CountryDirectory cd) {
		countryDirectory = cd;
	}
	
	public SortedSet<Journey> travelTo(int from, int to) {
		City a = countryDirectory.getCity(from);
		City b = countryDirectory.getCity(to);
		if (a == null) {
			System.out.print("No cities by id " + from);
			return null;
		} else if (b == null) {
			System.out.print("No cities by id " + to);
			return null;
		}
		SortedSet<Journey> journeys = new TreeSet<Journey>();
		// Only go into conditionals if destination differs from the source.
		if (!a.equals(b)) {
			// If cities in the same country, take a bus.
			if (a.getCountry() == b.getCountry()) {
				Journey j = new Journey();
				j.addLeg(new CityBusTravel<City>(), a, b);
				journeys.add(j);
			// All other possibilities where countries are different
			} else {
				// If the cities are in two bordering countries
				if (a.getCountry().isBordering(b.getCountry()) && b.getCountry().isBordering(a.getCountry())) {
					Journey j = new Journey();
					j.addLeg(new CityTrainTravel<City>(), a, b);
					journeys.add(j);
				if (a instanceof Capital && b instanceof Capital) {
					Journey i = new Journey();
					i.addLeg(new CityPlaneTravel<Capital>(), (Capital) a, (Capital) b); 
					journeys.add(i);
				} // Where cities are not bordering
				} else {				
					Set<Country> sharedBorder = a.getCountry().getBordering().stream()
							.filter(b.getCountry().getBordering()::contains)
							.collect(Collectors.toSet());
					if (sharedBorder.size() > 0) {
						for (Country c : sharedBorder) {
							for (City d : c.getCities()) {
								Journey j = new Journey();
								j.addLeg(new CityTrainTravel<City>(), a, d);
								j.addLeg(new CityTrainTravel<City>(), d, b);
								journeys.add(j);
							}
						}
					}
					Journey k = new Journey();
					// If both cities are capitals
					if ((a instanceof Capital) && (b instanceof Capital)) {
						k.addLeg(new CityPlaneTravel<Capital>(),(Capital) a, (Capital) b);
						journeys.add(k);	
						
					// If destination city is not a capital.
					} else if ((a instanceof Capital) && !(b instanceof Capital)) {
						k.addLeg(new CityPlaneTravel<Capital>(), (Capital) a, b.getCountry().getCapital());
						k.addLeg(new CityBusTravel<City>(), b.getCountry().getCapital(), b);
						journeys.add(k);
						
					} else if (!(a instanceof Capital) && b instanceof Capital) {
						k.addLeg(new CityBusTravel<City>(), a, a.getCountry().getCapital());
						k.addLeg(new CityPlaneTravel<Capital>(), a.getCountry().getCapital(), (Capital) b);
						journeys.add(k);
					} else if (!(a instanceof Capital) && !(b instanceof Capital)) {
						k.addLeg(new CityBusTravel<City>(), a, a.getCountry().getCapital());
						k.addLeg(new CityPlaneTravel<Capital>(), a.getCountry().getCapital(), b.getCountry().getCapital());
						k.addLeg(new CityBusTravel<City>(), b.getCountry().getCapital(), b);
						journeys.add(k);
					}			
				}
			}		
		} else {
			Journey noRoute = new Journey();
			noRoute.addLeg(new NoRouteFound<City>(), a, b);
			journeys.add(noRoute);
		}	
		return journeys;
	}
}
