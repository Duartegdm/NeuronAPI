package com.neuron.resource;

import com.neuron.dao.UsuarioDao;
import com.neuron.service.UsuarioService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.modelmapper.ModelMapper;


@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/usuarios")
public class UsuarioResource {

    @Inject
    private UsuarioDao usuarioDao;

    @Inject
    private ModelMapper mapper;
    @Inject
    UsuarioService service;

    // Fazer PUT, DELETE E GET

}
