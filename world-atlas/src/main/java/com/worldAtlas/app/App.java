package com.worldAtlas.app;

import java.util.Scanner;
import java.util.Set;

import com.worldAtlas.geography.Country;
import com.worldAtlas.transport.Journey;
import com.worldAtlas.transport.TravelStrategy;

public class App 
{
    public static void main( String[] args )
    {
    	Scanner input = new Scanner(System.in);
    	CountryDirectory dc = CountryDirectory.initializeDirectory("F:\\OOPAssignment\\world-atlas\\countries.json");
    	TravelStrategy route = new TravelStrategy(dc);
    	
    	int choice = 100;
    	
    	while (choice != 0) {
    		System.out.println("\nWelcome to Karolis' world atlas. Please choose an operatin: ");
    		System.out.println("1: List all Countries\n2: List all cities\n3: Get City by ID\n4: Get Country by ID "
    				+ "\n5: Check if two countries are bordering\n6: List all cities in a country\n7: Get Country capital\n8: Travel between cities"
    				+ "\n0: Exit program");
    		
    		choice = input.nextInt();
    		
    		if (choice == 1) {
    			System.out.print(dc.getCountries());
    		} else if (choice == 2) {
    			System.out.print(dc.getCities());
    		} else if (choice == 3) {
    			System.out.print("Enter ID of City: ");
    			int id = input.nextInt();
    			if (dc.getCity(id) != null) {
    				System.out.print(dc.getCity(id));
    			} else {
    				System.out.print("No City by this ID: " + choice);
    			}
    		} else if (choice == 4) {
    			System.out.print("Enter ID of Country: ");
    			int id = input.nextInt();
    			if (dc.getCity(id) != null) {
    				System.out.print(dc.getCity(id));
    			} else {
    				System.out.print("No Country by this ID: " + choice);
    			}
    		} else if (choice == 5) {
    			System.out.print("Enter ID of Country 1: ");
    			int country1 = input.nextInt();
    			System.out.print("Enter ID of Country 2: ");
    			int country2 = input.nextInt();
    			if (dc.getById(country1).isBordering(dc.getById(country2))) {
    				System.out.print("The two countries are bordering. ");		
    			} else {
    				System.out.print("The two countries are not bordering. ");
    			}
    		} else if (choice == 6) {
    			System.out.print("Enter ID of Country: ");
    			int country = input.nextInt();
    			System.out.print(dc.getById(country).getCities());
    		} else if (choice == 7) {
    			System.out.print("Enter ID of Country: ");
    			int country = input.nextInt();
    			System.out.print(dc.getById(country).getCapital());
    		} else if (choice == 8) {
    			System.out.print("Enter ID of City you're travelling from: ");
    			int city1 = input.nextInt();
    			System.out.print("Enter ID of City you're travelling to: ");
    			int city2 = input.nextInt();
    			Set<Journey> journeys = route.travelTo(city1, city2);
    			if (journeys != null) {
    				System.out.print("****Journeys****");
        			for (Journey j : journeys) {
        				System.out.print(j);
        			}
        			System.out.print("****Journeys****");
    			}
    			
    		} else {
    			System.out.print("Unrecognized command. ");
    		}
    	}
    	
    }
}
