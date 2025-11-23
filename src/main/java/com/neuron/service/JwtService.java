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
            System.out.println("=== DEBUG JWT ===");
            System.out.println("Email: " + usuario.getEmail());
            System.out.println("JWT_SECRET_KEY exists: " + (System.getenv("JWT_SECRET_KEY") != null));
            System.out.println("Java version: " + System.getProperty("java.version"));

            String token = Jwt.issuer("neuron-api")
                    .subject(usuario.getEmail())
                    .claim("id", String.valueOf(usuario.getId()))
                    .groups(String.valueOf(usuario.getCodigoAcesso()))
                    .expiresAt(Instant.now().plus(1, ChronoUnit.HOURS))
                    .sign();

            System.out.println("Token gerado com sucesso!");
            return token;

        } catch (Exception e) {
            System.err.println("=== ERRO DETALHADO JWT ===");
            System.err.println("Tipo do erro: " + e.getClass().getName());
            System.err.println("Mensagem: " + e.getMessage());

            // Log da stack trace completa
            e.printStackTrace();

            // Log da causa raiz se existir
            if (e.getCause() != null) {
                System.err.println("Causa raiz: " + e.getCause().getClass().getName());
                System.err.println("Mensagem da causa: " + e.getCause().getMessage());
                e.getCause().printStackTrace();
            }

            throw new RuntimeException("Falha ao gerar token JWT: " + e.getMessage(), e);
        }
    }
}