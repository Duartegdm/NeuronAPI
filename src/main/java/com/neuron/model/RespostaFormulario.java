package com.neuron.model;

import java.time.LocalDateTime;

public class RespostaFormulario {

    private int idResposta;
    private LocalDateTime dtResposta;
    private int motivacao;
    private int felicidade;
    private int estresse;
    private String observacao;
    private int saudeMental;
    private int problemas;
    private int modoVer;
    private LocalDateTime dtAnalise;
    private int idUsuario;
    private int idRegistEmocao;

    public int getIdResposta() {
        return idResposta;
    }

    public void setIdResposta(int idResposta) {
        this.idResposta = idResposta;
    }

    public LocalDateTime getDtResposta() {
        return dtResposta;
    }

    public void setDtResposta(LocalDateTime dtResposta) {
        this.dtResposta = dtResposta;
    }

    public int getMotivacao() {
        return motivacao;
    }

    public void setMotivacao(int motivacao) {
        this.motivacao = motivacao;
    }

    public int getFelicidade() {
        return felicidade;
    }

    public void setFelicidade(int felicidade) {
        this.felicidade = felicidade;
    }

    public int getEstresse() {
        return estresse;
    }

    public void setEstresse(int estresse) {
        this.estresse = estresse;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getSaudeMental() {
        return saudeMental;
    }

    public void setSaudeMental(int saudeMental) {
        this.saudeMental = saudeMental;
    }

    public int getProblemas() {
        return problemas;
    }

    public void setProblemas(int problemas) {
        this.problemas = problemas;
    }

    public int getModoVer() {
        return modoVer;
    }

    public void setModoVer(int modoVer) {
        this.modoVer = modoVer;
    }

    public LocalDateTime getDtAnalise() {
        return dtAnalise;
    }

    public void setDtAnalise(LocalDateTime dtAnalise) {
        this.dtAnalise = dtAnalise;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdRegistEmocao() {
        return idRegistEmocao;
    }

    public void setIdRegistEmocao(int idRegistEmocao) {
        this.idRegistEmocao = idRegistEmocao;
    }
}
