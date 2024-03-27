package com.nagarro.eventhandler;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import java.io.IOException;

@Slf4j
@RequestScoped
public class EventRouterResource {
    @Inject
    EventHandlerFactory eventHandlerFactory;

    @Incoming("event-channel")
    public void processEvent(String eventType, JsonNode eventJson) throws IOException {
        EventHandler eventHandler = eventHandlerFactory.createEventHandler(eventType);
        if (eventHandler != null) {
            eventHandler.handleEvent(eventJson);
        } else {
            log.info("Unknown event type: {}", eventType);
        }
    }
}
