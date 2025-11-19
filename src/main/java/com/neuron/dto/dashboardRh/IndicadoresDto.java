package com.neuron.dto.dashboardRh;

public class IndicadoresDto {

    private double mediaBemEstar;
    private double porcentagemBurnout;
    private double indiceGeral;

    public IndicadoresDto(double mediaBemEstar, double porcentagemBurnout, double indiceGeral) {
        this.mediaBemEstar = mediaBemEstar;
        this.porcentagemBurnout = porcentagemBurnout;
        this.indiceGeral = indiceGeral;
    }

    public IndicadoresDto(int totalRegistros, double mediaIntensidade) {
    }

    public double getMediaBemEstar() {
        return mediaBemEstar;
    }

    public void setMediaBemEstar(double mediaBemEstar) {
        this.mediaBemEstar = mediaBemEstar;
    }

    public double getPorcentagemBurnout() {
        return porcentagemBurnout;
    }

    public void setPorcentagemBurnout(double porcentagemBurnout) {
        this.porcentagemBurnout = porcentagemBurnout;
    }

    public double getIndiceGeral() {
        return indiceGeral;
    }

    public void setIndiceGeral(double indiceGeral) {
        this.indiceGeral = indiceGeral;
    }
}
