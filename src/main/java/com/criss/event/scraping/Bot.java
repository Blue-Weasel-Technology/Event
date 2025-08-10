package com.criss.event.scraping;


import org.openqa.selenium.WebDriver;

public class Bot {
    public WebDriver driver;
    
    public Bot(WebDriver driver) {
        this.driver=driver;
    }

    public void search(String query) {
        driver.get(query);
        sleep(4);
    }
    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        }
        catch(InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    
}