package com.neuron.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UniqueElements;


public class CreateUsuarioDto {
    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 150, message = "Limite de caracteres excedido")
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "O e-mail deve ser válido")
    @Size(max = 150, message = "Limite de caracteres excedido")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @Size(max = 28, message = "Limite de caracteres excedido")
    private String senha;

    @NotNull(message = "O tipo de acesso é obrigatório")
    private int codigoAcesso;

    @NotNull(message = "O departamento é obrigatório")
    private int codigoDepartamento;

    public @NotBlank(message = "O nome é obrigatório") @Size(max = 150, message = "Limite de caracteres excedido") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "O nome é obrigatório") @Size(max = 150, message = "Limite de caracteres excedido") String nome) {
        this.nome = nome;
    }

    public @NotBlank(message = "O e-mail é obrigatório") @Email(message = "O e-mail deve ser válido") @Size(max = 150, message = "Limite de caracteres excedido") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "O e-mail é obrigatório") @UniqueElements(message = "Esse e-mail já está em uso") @Email(message = "O e-mail deve ser válido") @Size(max = 150, message = "Limite de caracteres excedido") String email) {
        this.email = email;
    }

    public @NotBlank(message = "A senha é obrigatória") @Size(max = 28, message = "Limite de caracteres excedido") String getSenha() {
        return senha;
    }

    public void setSenha(@NotBlank(message = "A senha é obrigatória") @Size(max = 28, message = "Limite de caracteres excedido") String senha) {
        this.senha = senha;
    }

    @NotNull(message = "O tipo de acesso é obrigatório")
    public int getCodigoAcesso() {
        return codigoAcesso;
    }

    public void setCodigoAcesso(@NotNull(message = "O tipo de acesso é obrigatório") int codigoAcesso) {
        this.codigoAcesso = codigoAcesso;
    }

    @NotNull(message = "O departamento é obrigatório")
    public int getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(@NotBlank(message = "O departamento é obrigatório") int codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }
}
