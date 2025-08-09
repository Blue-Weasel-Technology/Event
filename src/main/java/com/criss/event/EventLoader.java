package com.criss.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.InputStream;
import java.util.List;

public class EventLoader {

    public static List<Event> loadEventsFromStream(InputStream inputStream) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            CollectionType listType = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, Event.class);
            return objectMapper.readValue(inputStream, listType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
