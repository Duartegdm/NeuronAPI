package com.neuron.dto.dashboardRh;

public class EmocaoMaisComumDto {

    private String emocao;
    private int quantidade;

    public EmocaoMaisComumDto(String emocao, int quantidade) {
        this.emocao = emocao;
        this.quantidade = quantidade;
    }

    public String getEmocao() {
        return emocao;
    }

    public void setEmocao(String emocao) {
        this.emocao = emocao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
