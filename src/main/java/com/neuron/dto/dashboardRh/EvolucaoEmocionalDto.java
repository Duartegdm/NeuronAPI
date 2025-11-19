package com.neuron.dto.dashboardRh;

import java.time.LocalDate;

public class EvolucaoEmocionalDto {

    private String data;
    private double mediaIntensidade;

    public EvolucaoEmocionalDto(String data, double mediaIntensidade) {
        this.data = data;
        this.mediaIntensidade = mediaIntensidade;
    }

    public EvolucaoEmocionalDto(LocalDate dia, double mediaDia) {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getMediaIntensidade() {
        return mediaIntensidade;
    }

    public void setMediaIntensidade(double mediaIntensidade) {
        this.mediaIntensidade = mediaIntensidade;
    }
}
