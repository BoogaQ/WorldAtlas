package com.worldAtlas.app;

import com.worldAtlas.geography.City;
import com.worldAtlas.geography.Country;

public class App 
{
    public static void main( String[] args )
    {
 	
    	CountryDirectory dc = CountryDirectory.initializeDirectory("asd");
    	
    	for (Country c : dc.countries()) {
    		System.out.print(c);
    	}
    	
    	System.out.print(dc.getByName("Ireland").isBordering(dc.getById(2)));
    }
}
