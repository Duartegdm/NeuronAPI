package com.neuron.dto.respostaFormulario;

public class CreateRespostaFormularioDto {
    private String dtResposta;
    private int motivacao;
    private int felicidade;
    private int estresse;
    private String observacao;
    private int saudeMental;
    private int problemas;
    private int modoVer;
    private String dtAnalise;
    private int idUsuario;
    private int idRegistEmocao;

    public String getDtResposta() {
        return dtResposta;
    }

    public void setDtResposta(String dtResposta) {
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

    public String getDtAnalise() {
        return dtAnalise;
    }

    public void setDtAnalise(String dtAnalise) {
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
