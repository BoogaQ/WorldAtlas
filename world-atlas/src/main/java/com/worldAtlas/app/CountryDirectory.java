package com.worldAtlas.app;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.worldAtlas.geography.Capital;
import com.worldAtlas.geography.City;
import com.worldAtlas.geography.Country;


public class CountryDirectory implements Directory {
	
	private static CountryDirectory me;
	
	private HashMap<Integer, Country> byId;
	private HashMap<Integer, City> cities;
	
	private CountryDirectory() {
		byId = new HashMap<Integer, Country>();
		cities = new HashMap<Integer, City>();
	}	
	
	public static CountryDirectory initializeDirectory(String filepath) {
		if (me == null) {
			me = new CountryDirectory();
		}
		try {
			InputStream json = new FileInputStream(filepath);
			JSONTokener parser = new JSONTokener(json);
			JSONObject data = new JSONObject(parser);
			
			JSONObject bordering = data.getJSONObject("bordering");
			JSONArray countryArray = data.getJSONArray("countries");
			
			// Loop through array of country objects
			for (int i = 0; i < countryArray.length(); i++) {
				int id = countryArray.getJSONObject(i).getInt("id");
				String countryName = countryArray.getJSONObject(i).getString("name");
				int capital = countryArray.getJSONObject(i).getInt("capital");
				Country country = new Country(id, countryName);
				
				// Loop through cities in each country
				JSONArray cityArray = countryArray.getJSONObject(i).getJSONArray("cities");				
				for (int j = 0; j < cityArray.length(); j++) {
					int cityId = cityArray.getJSONObject(j).getInt("id");
					String cityName = cityArray.getJSONObject(j).getString("name");
					int population = cityArray.getJSONObject(j).getInt("population");
					City city = new City(cityId, cityName, population, country);
					if (cityId == capital) {
						city = new Capital(cityId, cityName, population, country);
						country.setCapital((Capital) city); 
					}				
					country.addCity(city);   	
					me.cities.put(cityId, city);
				}
				me.byId.put(id, country);		
			}		
			for (String s : bordering.keySet()) {
				JSONArray borderingArray = bordering.getJSONArray(s);
				for (int k = 0; k < borderingArray.length(); k++) {
					me.getById(Integer.parseInt(s)).addBordering(me.getById(borderingArray.getInt(k)));
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return me;
	}
	
	public Country getById(Integer id) {
		return byId.get(id);
	}
	
	public City getCity(Integer id) {
		return cities.get(id);
	}
	public SortedSet<Country> getCountries() {
		SortedSet<Country> countries = new TreeSet<Country>();
		for (int id : byId.keySet()) {
			countries.add(byId.get(id));
		}
		return countries;
	}
	
	public SortedSet<City> getCities() {
		SortedSet<City> output = new TreeSet<City>();
		for (int id : cities.keySet()) {
			output.add(cities.get(id));
		}
		return output;                                                                                                                                                                                                                                                                                                                                                      
	} 
}
