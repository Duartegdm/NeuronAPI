package com.neuron.dto.registroEmocao;

import java.time.LocalDateTime;

public class DetailRegistroEmocaoDto {

    private Integer idRegistEmocao;
    private Integer intRegistEmocao;
    private String dsRegistEmocao;
    private LocalDateTime dtRegistEmocao;
    private Integer idEmocao;
    private Integer idUsuario;

    public Integer getIdRegistEmocao() {
        return idRegistEmocao;
    }

    public void setIdRegistEmocao(Integer idRegistEmocao) {
        this.idRegistEmocao = idRegistEmocao;
    }

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

    public LocalDateTime getDtRegistEmocao() {
        return dtRegistEmocao;
    }

    public void setDtRegistEmocao(LocalDateTime dtRegistEmocao) {
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
