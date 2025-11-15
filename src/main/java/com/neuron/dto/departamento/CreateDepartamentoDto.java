package com.neuron.dto.departamento;

import jakarta.validation.constraints.*;

public class CreateDepartamentoDto {

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "Limite de caracteres excedido")
    private String nome;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 150, message = "Limite de caracteres excedido")
    private String descricao;


    public @NotBlank(message = "Nome é obrigatório") @Size(max = 100, message = "Limite de caracteres excedido") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "Nome é obrigatório") @Size(max = 100, message = "Limite de caracteres excedido") String nome) {
        this.nome = nome;
    }

    public @NotBlank(message = "Descrição é obrigatória") @Size(max = 150, message = "Limite de caracteres excedido") String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank(message = "Descrição é obrigatória") @Size(max = 150) String descricao) {
        this.descricao = descricao;
    }
}
