package com.neuron.model;

public class Emocao {
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

    public void setCodigoCategEmocao(int codigoCatgEmocao) {
        this.codigoCatgEmocao = codigoCatgEmocao;
    }
}
