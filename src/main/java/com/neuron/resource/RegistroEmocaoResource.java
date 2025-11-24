package com.neuron.resource;

import com.neuron.dto.registroEmocao.CreateRegistroEmocaoDto;
import com.neuron.dto.registroEmocao.DetailRegistroEmocaoDto;
import com.neuron.dto.registroEmocao.ListRegistroEmocaoDto;
import com.neuron.model.RegistroEmocao;
import com.neuron.service.RegistroEmocaoService;
import jakarta.annotation.security.PermitAll;
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
@Path("/registro-emocao")
//@PermitAll
public class RegistroEmocaoResource {

    @Inject
    RegistroEmocaoService service;

    @Inject
    ModelMapper mapper;

    @POST
    public Response create(CreateRegistroEmocaoDto dto, @Context UriInfo uriInfo) throws SQLException {
        RegistroEmocao registro = service.create(dto);

        URI uri = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(registro.getIdRegistEmocao()))
                .build();

        return Response.created(uri).entity(mapper.map(registro, DetailRegistroEmocaoDto.class)).build();
    }

    @GET
    @Path("/usuario/{idUsuario}")
    public List<ListRegistroEmocaoDto> listarPorUsuario(@PathParam("idUsuario") int idUsuario) throws SQLException {
        return service.listarPorUsuario(idUsuario);
    }
}
