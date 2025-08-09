package com.criss.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Scraper {
    
    public void gatherData() {
        //Loads the events and displays logs

        WebDriver driver = new ChromeDriver();
        Bot scrapeBot = new Bot(driver);
        scrapeBot.search("https://www.iabilet.ro/bilete-concerte/");
        HashMap<String, String> styles = new HashMap<>();
        List<WebElement> eventAnchors = new ArrayList<>();
        List<String> eventLinks = new ArrayList<>();

        //Quit if needed

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nShutdown detected! Closing browser...");
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("Error while closing driver: " + e.getMessage());
            }
        }));

        /* fetches the links for the events */
        WebElement stylesMenu= driver.findElement(By.xpath("/html/body/header/div/div/div/ul[1]/li/div/ul"));
        eventAnchors=stylesMenu.findElements(By.tagName("a"));
        for(WebElement eventAnchor : eventAnchors) {
            try {
                
                String href = eventAnchor.getDomProperty("href"); 
                if (href != null && !href.isBlank() && !href.isEmpty() && !href.equals("https://www.iabilet.ro/bilete-concerte/")) {
                    eventLinks.add(href);
                    String styleName = eventAnchor.getDomProperty("innerText").trim();
                    styles.put(href, styleName);
                }
            } catch (Exception e) {
            }
        }

        styles.forEach((k, v) -> {
            scrapeBot.search(k);    //opens style-specific page
            WebElement eventListItems = driver.findElement(By.className("event-list-items"));
            List<WebElement> styleSortedAnchors = new ArrayList<>();
            Set<String> styleSortedLinks = new HashSet<>();      //Event links of a specific style
            try {
                styleSortedAnchors = eventListItems.findElements(By.tagName("a"));
            
                for(WebElement styleSortedAnchor : styleSortedAnchors) {
                    styleSortedLinks.add(styleSortedAnchor.getDomProperty("href"));
            }
            } catch (Exception e) {
            }
            Set<String> visitedNames = new HashSet<>();

            for(String styleSortedLink : styleSortedLinks) {
                scrapeBot.search(styleSortedLink);

                try {
                    String name;
                    WebElement titleElement = driver.findElement(By.className("header-text"));
                    name = titleElement.findElement(By.tagName("h1")).getText();
                    if(!visitedNames.contains(name)) {
                        visitedNames.add(name);
                        String style = v;
                        String image;
                        String locationDescription;
                        String localDateTime;
                        String description;
                        WebElement poster = driver.findElement(By.className("poster-image"));
                        image = poster.findElement(By.tagName("img")).getDomProperty("src");
                        locationDescription = driver.findElement(By.className("location")).getText();
                        localDateTime = driver.findElement(By.className("date")).getText();
                        description = driver.findElement(By.className("short-desc")).getText();
                        System.out.println(name + "\n" + style + "\n" + image + "\n" + locationDescription + "\n" + localDateTime + "\n" + description + "\n" + "------------------------------------------------");
                    }
                    else {
                        continue;
                    }
                } catch(Exception e) {
                }
            }
        });
    
        driver.quit();
    }
}