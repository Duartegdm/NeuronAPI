package com.neuron.resource;

import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;

@Path("/debug")
@PermitAll
public class DebugResource {

    @GET
    @Path("/env")
    public Response checkEnv() {
        Map<String, String> env = new HashMap<>();
        env.put("JWT_SECRET_KEY", System.getenv("JWT_SECRET_KEY") != null ? "PRESENT" : "MISSING");
        env.put("DATABASE_URL", System.getenv("DATABASE_URL"));
        env.put("java.version", System.getProperty("java.version"));

        return Response.ok(env).build();
    }
}
