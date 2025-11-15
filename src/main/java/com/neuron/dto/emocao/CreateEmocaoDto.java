package com.neuron.dto.emocao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateEmocaoDto {
    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 30, message = "Limite de caracteres excedido")
    private String nome;

    @NotBlank(message = "A cor é obrigatória")
    @Size(max = 10, message = "Cor inválida")
    private String cor;

    @NotNull(message = "A categoria da emoção é obrigatória")
    private int codigoCatgEmocao;

    public @NotBlank(message = "O nome é obrigatório") @Size(max = 30, message = "Limite de caracteres excedido") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "O nome é obrigatório") @Size(max = 30, message = "Limite de caracteres excedido") String nome) {
        this.nome = nome;
    }

    public @NotBlank(message = "A cor é obrigatória") @Size(max = 10, message = "Cor inválida") String getCor() {
        return cor;
    }

    public void setCor(@NotBlank(message = "A cor é obrigatória") @Size(max = 10, message = "Cor inválida") String cor) {
        this.cor = cor;
    }

    @NotNull(message = "A categoria da emoção é obrigatória")
    public int getCodigoCatgEmocao() {
        return codigoCatgEmocao;
    }

    public void setCodigoCatgEmocao(@NotNull(message = "A categoria da emoção é obrigatória") int codigoCatgEmocao) {
        this.codigoCatgEmocao = codigoCatgEmocao;
    }
}
