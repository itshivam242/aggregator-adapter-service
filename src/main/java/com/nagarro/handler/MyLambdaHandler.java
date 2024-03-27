package com.nagarro.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.databind.JsonNode;
import com.nagarro.converter.SpiMenuToUberEatsMenu;
import com.nagarro.eventhandler.EventHandler;
import com.nagarro.eventhandler.EventHandlerFactory;
import com.nagarro.spi.models.MenuConfiguration;
import com.nagarro.uber.eats.models.MenuData;
import io.smallrye.mutiny.operators.uni.builders.UniCreateFromEmitterWithState;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

@Slf4j
@Named("myLambdaHandler")
@ApplicationScoped
public class MyLambdaHandler implements RequestStreamHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Inject
    EventHandlerFactory eventHandlerFactory;
    private final SpiMenuToUberEatsMenu spiMenuToUberEatsMenu= new SpiMenuToUberEatsMenu();

    public void handleRequest(InputStream inputStream,
                              OutputStream outputStream,
                              Context context) throws IOException {
        log.info("Event Received");

        try {
            JsonNode inputEvent = objectMapper.readTree(inputStream);
            String eventType = inputEvent.path("detail-type").asText();
            String storeId = inputEvent.path("detail").path("context").path("aggregatorStoreId").asText();
            EventHandler eventHandler = eventHandlerFactory.createEventHandler(eventType);

            if (eventHandler != null) {
                log.info("Event Type: {}", eventType);
                eventHandler.handleEvent(inputEvent);
            } else {
                log.warn("Unknown event type: {}", eventType);
            }
        } catch (Exception e) {
            log.error("Error", e);
        }

        log.info("Lambda Executed");
    }
}
