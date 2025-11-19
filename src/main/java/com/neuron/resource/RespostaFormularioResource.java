package com.neuron.resource;

import com.neuron.dto.respostaFormulario.CreateRespostaFormularioDto;
import com.neuron.model.RespostaFormulario;
import com.neuron.service.RespostaFormularioService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/resposta-formulario")
public class RespostaFormularioResource {

    @Inject
    RespostaFormularioService service;

    @POST
    public Response create(CreateRespostaFormularioDto dto, @Context UriInfo uriInfo) {
        RespostaFormulario resposta = service.create(dto);
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(resposta.getIdResposta())).build();
        return Response.created(uri).entity(resposta).build();
    }

    @GET
    @Path("/usuario/{idUsuario}")
    public List<RespostaFormulario> listarPorUsuario(@PathParam("idUsuario") int idUsuario) throws Exception {
        return service.listarPorUsuario(idUsuario);
    }
}
