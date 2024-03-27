package com.nagarro.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.config.UberMenuConfig;
import io.quarkus.arc.Unremovable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
@Unremovable
@Slf4j
public class UpsertMenuService {
    @Inject
    UberAuthService uberAuthService;
    @Inject
    @RestClient
    UberMenuConfig uberMenuConfig;

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response upsertMenu(@PathParam("storeId") String storeId, String menuData) {
        try {
            log.info("Attempting to get token...");
            Response tokenResponse = uberAuthService.getToken();
            if (tokenResponse == null) {
                log.error("Token response is null");
                return Response.serverError().entity("Token response is null").build();
            }
            log.info("Token response status: {}", tokenResponse.getStatus());
            if (tokenResponse.getStatus() != Response.Status.OK.getStatusCode()) {
                log.error("Failed to get access token. Status code: {}", tokenResponse.getStatus());
                return tokenResponse;
            }

            String jsonResponse = tokenResponse.readEntity(String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);
            String accessToken = jsonNode.get("access_token").asText();
            String authorizationHeader = "Bearer " + accessToken;
            Response updateResponse = uberMenuConfig.upsertMenu(authorizationHeader, storeId,menuData);
            if (updateResponse.getStatus() == Response.Status.NO_CONTENT.getStatusCode()) {
                log.info("Menu Published Successfully");
                return Response.ok().build();
            } else {
                log.error("Failed to update store status for ID {}. Response code: {}", storeId, updateResponse.getStatus());
                return Response.status(updateResponse.getStatus()).build();
            }
        } catch (JsonProcessingException e) {
            log.error("Error processing JSON", e);
            return Response.serverError().build();
        } catch (Exception e) {
            log.error("Error processing API request", e);
            return Response.serverError().build();
        }
    }

}
