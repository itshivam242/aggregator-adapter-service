package com.nagarro.eventhandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.converter.SpiMenuToUberEatsMenu;
import com.nagarro.service.UpsertMenuService;
import com.nagarro.spi.models.MenuConfiguration;
import com.nagarro.uber.eats.models.MenuData;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;

@Slf4j
@ApplicationScoped
public class UpsertMenuEventHandler implements EventHandler {
    private final UpsertMenuService upsertMenuService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final SpiMenuToUberEatsMenu spiMenuToUberEatsMenu= new SpiMenuToUberEatsMenu();

    @Inject
    public UpsertMenuEventHandler(UpsertMenuService upsertMenuService) {
        this.upsertMenuService=upsertMenuService;
    }


    @Override
    public void handleEvent(JsonNode event) throws IOException {
        log.info("Handling Published Menu  Event: {}", event);
        String storeId = event.path("detail").path("context").path("aggregatorStoreId").asText();
        var payload = event.path("detail").path("payload");
        var menuUrl = objectMapper.convertValue(payload,URL.class);
        var spiMenu = objectMapper.readValue(menuUrl, MenuConfiguration.class);
        MenuData menuData= spiMenuToUberEatsMenu.spiMenuItemToUberEatsMenuItemConverter(spiMenu);
        String menuDataString=String.valueOf(objectMapper.convertValue(menuData, JsonNode.class));
        log.info("menuDataString: {}",menuDataString);
        upsertMenuService.upsertMenu(storeId, menuDataString);
    }
}