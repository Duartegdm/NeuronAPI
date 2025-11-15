package com.neuron.dto.emocao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DetailEmocaoDto {
    private int id;
    private String nome;
    private String cor;
    private int codigoCatgEmocao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getCodigoCatgEmocao() {
        return codigoCatgEmocao;
    }

    public void setCodigoCatgEmocao(int codigoCatgEmocao) {
        this.codigoCatgEmocao = codigoCatgEmocao;
    }
}
