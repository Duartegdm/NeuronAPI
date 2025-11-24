package com.neuron.resource;

import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.time.Instant;

@Path("/health")
//@PermitAll
public class HealthResource {

    @GET
    public Response health() {
        return Response.ok().entity("{\"status\": \"OK\", \"timestamp\": \"" +
                Instant.now().toString() + "\"}").build();
    }
}
