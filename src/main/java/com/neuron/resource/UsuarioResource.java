package com.neuron.resource;

import com.neuron.dao.UsuarioDao;
import com.neuron.dto.auth.RegisterDto;
import com.neuron.dto.usuario.DetailUsuarioDto;
import com.neuron.dto.usuario.UpdateUsuarioDto;
import com.neuron.model.Usuario;
import com.neuron.service.UsuarioService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.modelmapper.ModelMapper;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;


@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/usuarios")
//@PermitAll
public class UsuarioResource {

    @Inject
    private UsuarioDao usuarioDao;

    @Inject
    private ModelMapper mapper;
    @Inject
    UsuarioService service;

    // Fazer PUT, DELETE E GET (CADASTRO FEITO PELO "/auth/register")
    @GET
    public List<DetailUsuarioDto> listar() throws SQLException {
        return usuarioDao.listar().stream()
                .map(u -> mapper.map(u, DetailUsuarioDto.class)).toList();
    }


    @GET
    @Path("/{id}")
    public Usuario listarPorId(@PathParam("id") int id) throws SQLException {
        return usuarioDao.buscarPorId(id);
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, @Valid UpdateUsuarioDto dto) throws SQLException {
        System.out.println("Usuario AtualizarDao: " + usuarioDao);
        Usuario usuario = service.atualizar(dto, id);
        System.out.println("Usuario mapeado AtualizarDao: " + usuario);
        usuarioDao.atualizar(usuario);

        return Response.ok().build();
    }

    @DELETE
    @Path("/id/{id}")
    public Response remover(@PathParam("id") int id) throws SQLException {
        service.remover(id);

        return Response.noContent().build();
    }

    @DELETE
    @Path("/email/{email}")
    public Response remover(@PathParam("email") String email) throws SQLException {
        service.remover(email);

        return Response.noContent().build();
    }

    @POST
    @PermitAll
    public Response create(@Valid RegisterDto dto, @Context UriInfo uriInfo) throws SQLException {
        Usuario usuario = service.registrar(dto);

        URI uri = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(usuario.getId())).build();

        return Response.created(uri).entity(mapper.map(usuario, DetailUsuarioDto.class)).build();
    }
}
