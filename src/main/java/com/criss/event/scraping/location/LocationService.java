package com.criss.event.scraping.location;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LocationService {

    public static double[] getCoordinates(String locationSearch1, String locationSearch2, String postalCode) throws InterruptedException {

        RestTemplate restTemplate = new RestTemplate();
        //1
        String url = "https://nominatim.openstreetmap.org/search?format=json&q="+locationSearch1+"&email=you@example.com";
        LocationDTO[] locations = restTemplate.getForObject(url, LocationDTO[].class);
        if (locations != null && locations.length > 0) 
            return new double[]{Double.parseDouble(locations[0].getLat()), Double.parseDouble(locations[0].getLon())};

        Thread.sleep(1000);

        //2
        url = "https://nominatim.openstreetmap.org/search?format=json&country=Romania&postalcode="+postalCode+"&email=you@example.com";
        locations = restTemplate.getForObject(url, LocationDTO[].class);
        if (locations != null && locations.length > 0) 
            return new double[]{Double.parseDouble(locations[0].getLat()), Double.parseDouble(locations[0].getLon())};

        Thread.sleep(1000);

        //3
        url = "https://nominatim.openstreetmap.org/search?format=json&q="+locationSearch2+"&email=you@example.com";
        locations = restTemplate.getForObject(url, LocationDTO[].class);
        if (locations != null && locations.length > 0) 
            return new double[]{Double.parseDouble(locations[0].getLat()), Double.parseDouble(locations[0].getLon())};

        return new double[0];
    }
}