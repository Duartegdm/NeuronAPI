package com.neuron.dto.registroEmocao;

public class CreateRegistroEmocaoDto {

    private Integer intRegistEmocao;
    private String dsRegistEmocao;
    private String dtRegistEmocao;
    private Integer idEmocao;
    private Integer idUsuario;


    public Integer getIntRegistEmocao() {
        return intRegistEmocao;
    }

    public void setIntRegistEmocao(Integer intRegistEmocao) {
        this.intRegistEmocao = intRegistEmocao;
    }

    public String getDsRegistEmocao() {
        return dsRegistEmocao;
    }

    public void setDsRegistEmocao(String dsRegistEmocao) {
        this.dsRegistEmocao = dsRegistEmocao;
    }

    public String getDtRegistEmocao() {
        return dtRegistEmocao;
    }

    public void setDtRegistEmocao(String dtRegistEmocao) {
        this.dtRegistEmocao = dtRegistEmocao;
    }

    public Integer getIdEmocao() {
        return idEmocao;
    }

    public void setIdEmocao(Integer idEmocao) {
        this.idEmocao = idEmocao;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
