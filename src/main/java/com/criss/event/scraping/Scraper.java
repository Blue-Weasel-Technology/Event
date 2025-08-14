package com.criss.event.scraping;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.criss.event.scraping.location.CoordinatesConverter;
import com.criss.event.utility.RomanianConverter;

public class Scraper {
    
    // Add CoordinatesConverter instance here
    private CoordinatesConverter coordinatesConverter = new CoordinatesConverter();

    public void gatherData() {
        //Connection to the database
        try (var connection =  DB.connect()){
            System.out.println("Connected to the PostgreSQL database.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        // Loads the events and displays logs
        WebDriver driver = new ChromeDriver();
        Bot scrapeBot = new Bot(driver);
        scrapeBot.search("https://www.iabilet.ro/bilete-concerte/");
        HashMap<String, String> styles = new HashMap<>();
        List<WebElement> eventAnchors = new ArrayList<>();
        List<String> eventLinks = new ArrayList<>();

        // Quit if needed
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nShutdown detected! Closing browser...");
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("Error while closing driver: " + e.getMessage());
            }
        }));

        /* fetches the links for the events */
        try{
            WebElement stylesMenu = driver.findElement(By.xpath("/html/body/header/div/div/div/ul[1]/li/div/ul"));
            eventAnchors = stylesMenu.findElements(By.tagName("a"));
        } catch(Exception e) {
            System.err.println("STYLE WEB ELEMENT ANCHOR NOT FOUND!");
        }
        
        for (WebElement eventAnchor : eventAnchors) {
            try {
                String href = eventAnchor.getDomProperty("href");
                if (href != null && !href.isBlank() && !href.isEmpty() && !href.equals("https://www.iabilet.ro/bilete-concerte/")) {
                    eventLinks.add(href);
                    String styleName = eventAnchor.getDomProperty("innerText").trim();
                    styles.put(href, styleName);
                }
            } catch (Exception e) {
                System.err.println("COULD NOT GATHER STYLE OR CORESPONDING LINK!");
            }
        }

        styles.forEach((k, v) -> {
            boolean continueGatherData=true;
            scrapeBot.search(k); // opens style-specific page
            List<WebElement> styleSortedAnchors = new ArrayList<>();
            Set<String> styleSortedLinks = new HashSet<>(); // Event links of a specific style
            try {
                WebElement eventListItems = driver.findElement(By.className("event-list-items"));
                List<WebElement> titleDivs = new ArrayList<>();
                titleDivs = eventListItems.findElements(By.className("title"));
                for(WebElement titleDiv : titleDivs){
                    styleSortedAnchors.add(titleDiv.findElement(By.tagName("a")));
                }
                for (WebElement styleSortedAnchor : styleSortedAnchors) {
                    styleSortedLinks.add(styleSortedAnchor.getDomProperty("href"));
                }
            } catch (Exception e) {
                System.err.println("EVENT LINKS NOT FOUND IN THEIR STYLE CATEGORY!");
            }
            Set<String> visitedNames = new HashSet<>();
            System.out.println(styleSortedLinks.toString());
            for (String styleSortedLink : styleSortedLinks) {
                
                try {
                    scrapeBot.search(styleSortedLink);
                } catch (Exception e) { System.err.println("Error: Unable to find styleSortedLink! \n" + e);}

                    String name=null;
                
                try {
                    name = driver.findElement(By.className("header-text")).getDomProperty("innerText").trim();
                }  catch(Exception e) {
                    System.err.println("Error: Unable to find event name! \n" + e);
                    continueGatherData=false;
                }

                    // Replace Romanian characters
                name = RomanianConverter.convertRomanianChars(name);

                if (!visitedNames.contains(name)) {
                        visitedNames.add(name);
                } else { continueGatherData=false;}
                
                    if(continueGatherData){
                        String style = v;
                        String image=null;
                        String locationDescription=null;
                        String localDateTime=null;
                        String description=null;
                        double[] coordinates = new double[]{0, 0};
                        
                        try{
                            WebElement poster = driver.findElement(By.className("poster-image"));
                            image = poster.findElement(By.tagName("img")).getDomProperty("src");
                        } catch(Exception e) {System.err.println("Error: Unable to find Image! \n"+ e);}
                        
                        try {
                            locationDescription = driver.findElement(By.className("location")).getText();
                        } catch(Exception e) {System.err.println("Error: Unable to find Location Text!\n" + e);}
                        
                        

                        try {
                            locationDescription = RomanianConverter.convertRomanianChars(locationDescription);
                        } catch(Exception e) {System.err.println("Error: Unable to convert Location Description! \n" + e);}
                        
                        try {
                            localDateTime = driver.findElement(By.className("date")).getText();
                        } catch(Exception e) {System.err.println("Error: Unable to find Date Text! \n" + e);}
                        
                        java.sql.Timestamp parsedDate=null;
                        
                        try{
                          parsedDate = DateParser.parse(localDateTime);
                        } catch(Exception e) {System.err.println("Error: Unable to parse date! \n" + e);}
                        

                        try{
                            driver.findElement(By.className("event-detail-toggle")).click();
                            scrapeBot.sleep(1);
                        } catch(Exception e) {System.err.println("Warning: Unable to click more details for the Description! \n" + e);}
                        
                        try {
                            description = driver.findElement(By.className("short-desc")).getText();
                        } catch(Exception e) {System.err.println("Error: Unable to find Short Description! \n" + e);}

                        try {
                            description = description + " " + driver.findElement(By.className("event-detail")).getText();
                            description = RomanianConverter.convertRomanianChars(description);
                        } catch(Exception e) {System.err.println("Error: Unable to create the complete Description! \n"+ e);}
                           
                        String shortDescription;
                        if(description.length()<=100) {
                            shortDescription=description;
                        } else {
                            shortDescription = description.substring(0, 100) + "...";
                        } 

                        // Call convertCoordinates using the CoordinatesConverter instance
                        try{
                            coordinates = coordinatesConverter.convertCoordinates(locationDescription);
                        } catch(Exception e) {System.err.println("Error: Unable to locate Event and could not get coordinates! \n" + e);}
                        
                        System.out.println(name + "\n" + style + "\n" + image + "\n" + locationDescription + "\n" + coordinates[0] + "\n" + coordinates[1] + "\n" + localDateTime + "\n" + description + "\n" + "------------------------------------------------");
                        if(shortDescription!=null && parsedDate!=null && image!=null &&  locationDescription!=null && name!= null)      //coordinates[0]!=0 && coordinates[1]!=0;
                            EventAdd.add(shortDescription, parsedDate, image, coordinates[0], locationDescription, coordinates[1], name, style);
                        else {
                            System.out.println("Error: Unable to add Event!");
                        }
                    }
                        
                
            }
        });

        driver.quit();
    }
}

