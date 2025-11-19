package com.neuron.service;

import com.neuron.model.Usuario;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@ApplicationScoped
public class JwtService {

    public String gerarToken(Usuario usuario) {
        try {
            System.out.println("Gerando token JWT para: " + usuario.getEmail());

            String token = Jwt.issuer("neuron-api")
                    .subject(usuario.getEmail())
                    .claim("id", usuario.getId())
                    .claim("role", usuario.getCodigoAcesso())
                    .expiresAt(Instant.now().plus(1, ChronoUnit.HOURS))
                    .sign();

            System.out.println("Token gerado com sucesso!");
            return token;

        } catch (Exception e) {
            System.err.println("Erro ao gerar token: " + e.getMessage());
            throw new RuntimeException("Falha ao gerar token JWT: " + e.getMessage());
        }
    }
}