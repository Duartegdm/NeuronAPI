package com.neuron.dto.registroEmocao;

import java.time.LocalDateTime;

public class ListRegistroEmocaoDto {

    private LocalDateTime data;
    private String emocao;
    private Double intensidade;

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getEmocao() {
        return emocao;
    }

    public void setEmocao(String emocao) {
        this.emocao = emocao;
    }

    public Double getIntensidade() {
        return intensidade;
    }

    public void setIntensidade(Double intensidade) {
        this.intensidade = intensidade;
    }

}
