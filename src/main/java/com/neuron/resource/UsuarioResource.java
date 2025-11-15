package com.neuron.resource;

import com.neuron.dao.UsuarioDao;
import com.neuron.dto.usuario.CreateUsuarioDto;
import com.neuron.dto.usuario.DetailUsuarioDto;
import com.neuron.model.Usuario;
import com.neuron.service.UsuarioService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.modelmapper.ModelMapper;

import java.net.URI;
import java.sql.SQLException;

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

    @POST
    public Response create(@Valid CreateUsuarioDto dto, @Context UriInfo uriInfo) throws SQLException {
        Usuario usuario = service.criarUsuario(dto);

        URI uri = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(usuario.getId())).build();

        return Response.created(uri).entity(mapper.map(usuario, DetailUsuarioDto.class)).build();

    }
}
