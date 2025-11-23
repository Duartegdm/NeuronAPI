package com.neuron.resource;

import com.neuron.dto.auth.LoginDto;
import com.neuron.dto.auth.RegisterDto;
import com.neuron.dto.auth.TokenResponseDto;
import com.neuron.dto.usuario.DetailUsuarioDto;
import com.neuron.model.Usuario;
import com.neuron.service.JwtService;
import com.neuron.service.UsuarioService;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.modelmapper.ModelMapper;

import java.net.URI;
import java.sql.SQLException;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({"1"})
public class AuthResource {

    @Inject
    UsuarioService usuarioService;

    @Inject
    ModelMapper mapper;

    @Inject
    JwtService jwtService;

    @POST
    @Path("/register")
    @PermitAll
    public Response create(@Valid RegisterDto dto, @Context UriInfo uriInfo) throws SQLException {
        Usuario usuario = usuarioService.registrar(dto);

        URI uri = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(usuario.getId())).build();

        return Response.created(uri).entity(mapper.map(usuario, DetailUsuarioDto.class)).build();
    }

    @POST
    @Path("/login")
    @PermitAll
    public Response login(LoginDto dto) throws SQLException {
        try {
            System.out.println("Tentando login com: " + dto.getEmail());

            Usuario usuario = usuarioService.autenticar(dto.getEmail(), dto.getSenha());
            System.out.println("Usuário autenticado: " + usuario.getEmail());

            String token = jwtService.gerarToken(usuario);
            System.out.println("Token gerado: " + token);

            return Response.ok(new TokenResponseDto(token)).build();

        } catch (Exception e) {
            System.out.println("Erro no login: " + e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Credenciais inválidas: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/me")
    @Authenticated
    public Response me(@Context SecurityContext ctx) throws SQLException {
        String email = ctx.getUserPrincipal().getName();
        Usuario usuario = usuarioService.buscarPorEmail(email);
        return Response.ok(usuario).build();
    }
}
