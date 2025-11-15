package com.neuron.resource;

import com.neuron.dao.CatgEmocaoDao;
import com.neuron.dao.EmocaoDao;
import com.neuron.dto.acesso.DetailAcessoDto;
import com.neuron.dto.emocao.CreateEmocaoDto;
import com.neuron.dto.emocao.DetailEmocaoDto;
import com.neuron.model.Emocao;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.modelmapper.ModelMapper;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/emocoes")
public class EmocaoResource {

    @Inject
    EmocaoDao emocaoDao;

    @Inject
    ModelMapper mapper;

    @POST
    public Response create(CreateEmocaoDto dto, @Context UriInfo uriInfo) throws SQLException {
        Emocao emocao = mapper.map(dto, Emocao.class);

        emocaoDao.cadastrar(emocao);

        URI uri = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(emocao.getId())).build();

        return Response.created(uri).entity(mapper.map(emocao, DetailEmocaoDto.class)).build();
    }


    @GET
    public List<DetailEmocaoDto> listar() throws SQLException {
        return emocaoDao.listar().stream()
                .map(e -> mapper.map(e, DetailEmocaoDto.class)).toList();
    }
}
