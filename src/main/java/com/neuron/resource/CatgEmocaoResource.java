package com.neuron.resource;

import com.neuron.dao.CatgEmocaoDao;
import com.neuron.dao.EmocaoDao;
import com.neuron.dto.categoriaEmocao.CreateCatgEmocaoDto;
import com.neuron.dto.categoriaEmocao.DetailCategEmocao;
import com.neuron.dto.emocao.DetailEmocaoDto;
import com.neuron.model.CatgEmocao;
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
@Path("/catg-emocoes")
public class CatgEmocaoResource {

    @Inject
    CatgEmocaoDao catgEmocaoDao;

    @Inject
    ModelMapper mapper;

    @POST
    public Response create(CreateCatgEmocaoDto dto, @Context UriInfo uriInfo) throws SQLException {
        CatgEmocao catgEmocao = mapper.map(dto, CatgEmocao.class);

        catgEmocaoDao.cadastrar(catgEmocao);

        URI uri = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(catgEmocao.getId())).build();

        return Response.created(uri).entity(mapper.map(catgEmocao, DetailCategEmocao.class)).build();
    }

    @GET
    public List<DetailCategEmocao> listar() throws SQLException {
        return catgEmocaoDao.listar().stream()
                .map(e -> mapper.map(e, DetailCategEmocao.class)).toList();
    }


}
