package com.criss.event.scraping.location;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LocationService {

    public static double[] getCoordinates(String locationSearch1, String locationSearch2, String postalCode) {

        RestTemplate restTemplate = new RestTemplate();

        // 1
        String url = "https://nominatim.openstreetmap.org/search?format=json&q=" 
                + locationSearch1 + "&email=you@example.com";
        System.out.println("Try #1: " + url);
        LocationDTO[] locations = restTemplate.getForObject(url, LocationDTO[].class);

        if (locations != null && locations.length > 0) {
            System.out.println("Coordinates found at Try #1!");
            return new double[]{
                    Double.parseDouble(locations[0].getLat()), 
                    Double.parseDouble(locations[0].getLon())
            };
        } else {
            System.out.println("No coordinates found at Try #1.");
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 2
        url = "https://nominatim.openstreetmap.org/search?format=json&country=Romania&postalcode=" 
                + postalCode + "&email=you@example.com";
        System.out.println("Try #2: " + url);
        locations = restTemplate.getForObject(url, LocationDTO[].class);

        if (locations != null && locations.length > 0) {
            System.out.println("Coordinates found at Try #2!");
            return new double[]{
                    Double.parseDouble(locations[0].getLat()), 
                    Double.parseDouble(locations[0].getLon())
            };
        } else {
            System.out.println("No coordinates found at Try #2.");
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 3
        url = "https://nominatim.openstreetmap.org/search?format=json&q=" 
                + locationSearch2 + "&email=you@example.com";
        System.out.println("Try #3: " + url);
        locations = restTemplate.getForObject(url, LocationDTO[].class);

        if (locations != null && locations.length > 0) {
            System.out.println("Coordinates found at Try #3!");
            return new double[]{
                    Double.parseDouble(locations[0].getLat()), 
                    Double.parseDouble(locations[0].getLon())
            };
        } else {
            System.out.println("No coordinates found at Try #3.");
        }

        System.out.println("All tries failed. Returning empty coordinates.");
        return new double[0];
    }
}
