package com.criss.event;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Value;

import java.io.InputStream;
import java.util.List;


@Controller
public class HomeController {
    @Value("${spring.application.name}")
    private String appName;

    @RequestMapping("/")
    public String index() {
        System.out.println("Welcome to " + appName);
        return "map.html";
    }

    @GetMapping("/events")
    @ResponseBody
    public List<Event> getEvents() {
        InputStream jsonStream = EventApplication.class.getClassLoader().getResourceAsStream("static/events.json");
        return EventLoader.loadEventsFromStream(jsonStream);
    }
}
