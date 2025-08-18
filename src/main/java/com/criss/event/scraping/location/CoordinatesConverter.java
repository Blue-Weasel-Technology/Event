package com.criss.event.scraping.location;

import java.util.regex.*;
import java.util.List;

public class CoordinatesConverter {
    public double[] convertCoordinates(String locationDescription) {

        // List of cities in Romania
        List<String> cities = List.of(
            "Bucuresti", "Adjud", "Agigea", "Agnita", "Alba Iulia", "Arad", "Bacau", "Baia Mare", "Baicoi", "Barlad",
            "Barnova", "Beius", "Belis", "Bistrita", "Blaj", "Bobalna", "Botosani", "Braila", "Brasov", "Breb", "Brezoi",
            "Buftea", "Buzau", "Calarasi", "Campina", "Campulung", "Campulung Moldovenesc", "Caransebes", "Cernavoda",
            "Cernica", "Chisoda", "Chitila", "Cislau", "Cisnadie", "Clondiru de Sus", "Cluj-Napoca", "Codlea", "Constanta",
            "Corbu", "Costinesti", "Craiova", "Cristian", "Darabani", "Darmănești", "Dej", "Deva", "Dorohoi",
            "Drobeta-Turnu Severin", "Fagaras", "Falticeni", "Feleacu", "Floresti", "Focsani", "Galati", 
            "Garana", "Gheorghe Doja", "Giurgiu", "Govora", "Gura Humorului", "Gura Vadului", "Horezu", "Hunedoara", 
            "Iasi", "Ilia", "Insurăței", "Izvoarele", "Jupiter", "Ludus", "Lugoj", "Mamaia", "Mamaia-Sat", "Mangalia", 
            "Margau", "Medias", "Merisani", "Miercurea Ciuc", "Mioveni", "Mocesti", "Mogosoaia", "Nehoiu", "Onesti", 
            "Oradea", "Petrosani", "Piatra-Neamt", "Pitesti", "Ploiesti", "Plopeni", "Poiana lui Stanga", "Popesti Leordeni", 
            "Radauti", "Ramnicu Valcea", "Rasnov", "Reghin", "Resita", "Roman", "Sabaoani", "Satu Mare", "Sebes", "Sibiu", 
            "Sighisoara", "Slatina", "Slobozia", "Suceava", "Targoviste", "Targu Frumos", "Targu Lapus", "Targu Mures", 
            "Targu Ocna", "Tarnaveni", "Timisoara", "Tulcea", "Turceni", "Turda", "Vadu Oii", "Valea Danului", "Vama Veche", 
            "Vaslui", "Venus", "Viseu de Sus", "Zalau"
        );

        // Variables
        String postalCode = "";
        String city = "";
        String locationSearch1 = "";
        String locationSearch2 = "";
        double[] coordinates = new double[]{0, 0};

        // 1. Extract postal code
        String postalCodeRegex = "\\d{6}";
        Pattern postalCodePattern = Pattern.compile(postalCodeRegex);
        Matcher postalCodeMatcher = postalCodePattern.matcher(locationDescription);

        if (postalCodeMatcher.find()) {
            postalCode = postalCodeMatcher.group();
            locationDescription = locationDescription.replace(postalCode, "").trim();
        }

        // 2. Split by newline if exists
        if (locationDescription.contains("\n")) {
            String[] splitLocation = locationDescription.split("\n", 2);

            if (splitLocation.length > 0) {
                locationSearch1 = splitLocation[0].trim();
            }

            if (splitLocation.length > 1) {
                locationSearch2 = splitLocation[1].trim();
            }

            // 3. Extract city name
            for (String c : cities) {
                String cityRegex = "\\b" + Pattern.quote(c) + "\\b";
                Pattern cityPattern = Pattern.compile(cityRegex, Pattern.CASE_INSENSITIVE);
                Matcher cityMatcher = cityPattern.matcher(locationSearch1);

                if (cityMatcher.find()) {
                    city = cityMatcher.group();
                    break;
                }
            }

            // 4. Add city to locationSearch2 if missing
            if (!city.isEmpty() && !locationSearch2.contains(city)) {
                locationSearch2 = locationSearch2 + " " + city;
            }
        } else {
            locationSearch1 = locationDescription;
        }

        System.out.println("Location Search 1: " + locationSearch1);
        System.out.println("Location Search 2: " + locationSearch2);
        System.out.println("Postal Code: " + postalCode);

        coordinates = LocationService.getCoordinates(locationSearch1, locationSearch2, postalCode);
        return coordinates;
    }
}

    
