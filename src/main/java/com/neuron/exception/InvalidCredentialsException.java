package com.neuron.exception;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class InvalidCredentialsException extends WebApplicationException {
    public InvalidCredentialsException() {
        super("Credenciais inválidas", Response.Status.UNAUTHORIZED);
    }
}