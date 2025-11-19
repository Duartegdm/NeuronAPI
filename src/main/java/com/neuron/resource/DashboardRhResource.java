package com.neuron.resource;

import com.neuron.dto.dashboardRh.EmocaoMaisComumDto;
import com.neuron.dto.dashboardRh.EvolucaoEmocionalDto;
import com.neuron.dto.dashboardRh.IndicadoresDto;
import com.neuron.service.DashboardRhService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/dashboard-rh")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed("RH_CLEVEL")
public class DashboardRhResource {

    @Inject
    DashboardRhService dashboardService;

    @GET
    @Path("/{idUsuario}/indicadores")
    @PermitAll
    public Response indicadores(@PathParam("idUsuario") Long idUsuario) {
        IndicadoresDto dto = dashboardService.indicadoresGerais(idUsuario);
        return Response.ok(dto).build();

    }

    @GET
    @Path("/{idUsuario}/emocao-mais-comum")
    public Response emocaoMaisComum(@PathParam("idUsuario") Long idUsuario) {
        EmocaoMaisComumDto dto = dashboardService.emocaoMaisComum(idUsuario);
        return Response.ok(dto).build();

    }

    @GET
    @Path("/{idUsuario}/evolucao-emocional")
    public Response evolucaoEmocional(@PathParam("idUsuario") Long idUsuario) {
        List<EvolucaoEmocionalDto> lista = dashboardService.evolucaoEmocional(idUsuario);
        return Response.ok(lista).build();

    }
}
