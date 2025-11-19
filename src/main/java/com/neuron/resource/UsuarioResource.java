package com.neuron.resource;

import com.neuron.dao.UsuarioDao;
import com.neuron.dto.usuario.DetailUsuarioDto;
import com.neuron.dto.usuario.UpdateUsuarioDto;
import com.neuron.model.Usuario;
import com.neuron.service.UsuarioService;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.List;


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
    @GET
    public List<DetailUsuarioDto> listar() throws SQLException {
        return usuarioDao.listar().stream()
                .map(u -> mapper.map(u, DetailUsuarioDto.class)).toList();
    }

    @PUT
    @Path("/{id}")
    @PermitAll
    public Response atualizar(@PathParam("id")int id, @Valid UpdateUsuarioDto dto) throws SQLException {
        System.out.println("Usuario AtualizarDao: "+usuarioDao);
        Usuario usuario = service.atualizar(dto, id);
        System.out.println("Usuario mapeado AtualizarDao: "+usuario);
        usuarioDao.atualizar(usuario);

        return Response.ok().build();
    }

}
