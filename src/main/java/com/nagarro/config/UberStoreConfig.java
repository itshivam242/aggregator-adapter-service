package com.nagarro.config;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/v1/delivery")
@RegisterRestClient(configKey="uber-store")
public interface UberStoreConfig {
    @GET
    @Path("/stores")
    Response getStores(@HeaderParam("Authorization") String authorizationHeader);
}