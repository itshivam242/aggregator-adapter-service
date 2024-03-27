package com.nagarro.eventhandler;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public interface EventHandler {
    void handleEvent(JsonNode event) throws IOException;
}
