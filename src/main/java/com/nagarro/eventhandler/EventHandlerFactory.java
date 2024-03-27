package com.nagarro.eventhandler;

import com.nagarro.service.UpsertMenuService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class EventHandlerFactory {
    @Inject
    UpsertMenuService upsertMenuService;

    public EventHandler createEventHandler(String eventType) {
        if (eventType.equals("AGGR_UBER_EATS_MENU_PUBLISHED")) {
            return new UpsertMenuEventHandler(upsertMenuService);
        }
        return null;
    }
}