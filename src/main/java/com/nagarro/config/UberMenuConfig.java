package com.nagarro.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.nagarro.uber.eats.models.MenuData;
import jakarta.json.Json;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/v2/eats")
@RegisterRestClient(configKey="uber-menu")
@Consumes(MediaType.APPLICATION_JSON)
public interface UberMenuConfig {
    @PUT
    @Path("/stores/{store_id}/menus")
    Response upsertMenu(@HeaderParam("Authorization") String authorizationHeader, @PathParam("store_id") String storeId, String menu);

}
