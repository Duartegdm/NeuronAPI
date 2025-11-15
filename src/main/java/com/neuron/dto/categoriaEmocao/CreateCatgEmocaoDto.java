package com.neuron.dto.categoriaEmocao;

import com.neuron.model.NomeCatgEmocao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateCatgEmocaoDto {
    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 30, message = "Limite de caracteres excedido")
    private NomeCatgEmocao nome;

    public @NotBlank(message = "O nome é obrigatório") @Size(max = 30, message = "Limite de caracteres excedido") NomeCatgEmocao getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "O nome é obrigatório") @Size(max = 30, message = "Limite de caracteres excedido") NomeCatgEmocao nome) {
        this.nome = nome;
    }
}
