package com.neuron.exception;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class UserNotFoundException extends WebApplicationException {
    public UserNotFoundException() {
        super("Usuário não encontrado", Response.Status.NOT_FOUND);
    }
}
