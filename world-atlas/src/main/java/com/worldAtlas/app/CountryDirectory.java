package com.worldAtlas.app;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.worldAtlas.geography.City;
import com.worldAtlas.geography.Country;
import com.worldAtlas.transport.TakeBusTo;


public class CountryDirectory {
	
	private static CountryDirectory me;
	
	private HashMap<String, Country> byName;
	private HashMap<Integer, Country> byId;
	private HashMap<String, City> cities;
	
	private CountryDirectory() {
		byName = new HashMap<String, Country>();
		byId = new HashMap<Integer, Country>();
		cities = new HashMap<String, City>();
	}	
	
	public static CountryDirectory initializeDirectory(String filepath) {
		if (me == null) {
			me = new CountryDirectory();
		}
		try {
			InputStream json = new FileInputStream("F:\\OOPAssignment\\world-atlas\\countries.json");
			JSONTokener parser = new JSONTokener(json);
			JSONObject data = new JSONObject(parser);
			
			JSONArray countryArray = data.getJSONArray("countries");
			for (int i = 0; i < countryArray.length(); i++) {
				int id = countryArray.getJSONObject(i).getInt("id");
				String countryName = countryArray.getJSONObject(i).getString("name");
				int capital = countryArray.getJSONObject(i).getInt("capital");
				Country country = new Country(id, countryName, capital);
				
				JSONArray cityArray = countryArray.getJSONObject(i).getJSONArray("cities");
				
				for (int j = 0; j < cityArray.length(); j++) {
					int cityId = cityArray.getJSONObject(j).getInt("id");
					String cityName = cityArray.getJSONObject(j).getString("name");
					int population = cityArray.getJSONObject(j).getInt("population");
					City city = new City(cityId, cityName, population, id);
					country.addCity(city);   	
				}
				me.byId.put(id, country);
				me.byName.put(countryName, country);
			}
			JSONObject bordering = data.getJSONObject("bordering");
			for (String s : bordering.keySet()) {
				for (int k = 0; k < bordering.getJSONArray(s).length(); k++) {
					me.byName.get(s).addBordering((bordering.getJSONArray(s).getInt(k)));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return me;
	}
	
	public Country getById(int id) {
		return byId.get(id);
	}
	public Country getByName(String name) {
		return byName.get(name);
	}
	public City getCity(String name) {
		return cities.get(name);
	}
	public SortedSet<Country> countries() {
		SortedSet<Country> countries = new TreeSet<Country>();
		for (String name : byName.keySet()) {
			countries.add(byName.get(name));
		}
		return countries;
	}
	
	public SortedSet<City> cities() {
		SortedSet<City> cities = new TreeSet<City>();
		for (String name : byName.keySet()) {
			cities.addAll(byName.get(name).getCities());
		}
		return cities;
	}
	public String travel(City a, City b) {
		StringBuilder s = new StringBuilder();
		if (a.getCountry() == b.getCountry()) {
			s.append(a.travelTo(b, new TakeBusTo()));
		}
	}
}
