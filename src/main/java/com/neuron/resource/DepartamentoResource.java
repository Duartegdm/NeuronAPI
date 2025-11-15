package com.neuron.resource;

import com.neuron.dao.DepartamentoDao;
import com.neuron.dto.departamento.CreateDepartamentoDto;
import com.neuron.dto.departamento.DetailDepartamentoDto;
import com.neuron.model.Departamento;
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

@Path("/departamento")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DepartamentoResource {
    @Inject
    DepartamentoDao dao;

    @Inject
    private ModelMapper mapper;

    @POST
    public Response create(@Valid CreateDepartamentoDto createDepartamentoDto, @Context UriInfo uriInfo) throws SQLException {
        Departamento departamento = mapper.map(createDepartamentoDto, Departamento.class);

        dao.cadastrar(departamento);

        URI uri = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(departamento.getCodigo())).build();

        return Response.created(uri).entity(mapper.map(departamento, DetailDepartamentoDto.class)).build();//201
    }
}
