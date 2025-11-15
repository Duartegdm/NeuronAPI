package com.neuron.resource;

import com.neuron.dao.AcessoDao;
import com.neuron.dto.acesso.CreateAcessoDto;
import com.neuron.dto.acesso.DetailAcessoDto;
import com.neuron.model.Acesso;
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
@Path("/acessos")
public class AcessoResource {
    @Inject
    private AcessoDao acessoDao;

    @Inject
    private ModelMapper mapper;

    @POST
    public Response create(@Valid CreateAcessoDto dto, @Context UriInfo uriInfo) throws SQLException {
        Acesso acesso = mapper.map(dto, Acesso.class);

        acessoDao.cadastrar(acesso);

        URI uri = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(acesso.getCodigo())).build();

        return Response.created(uri).entity(mapper.map(acesso, DetailAcessoDto.class)).build();
    }

    @GET
    public List<DetailAcessoDto> listar() throws SQLException {
        return acessoDao.listar().stream()
                .map(a -> mapper.map(a, DetailAcessoDto.class)).toList();
    }
}
