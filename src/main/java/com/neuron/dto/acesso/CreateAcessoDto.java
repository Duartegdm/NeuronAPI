package com.neuron.dto.acesso;

import com.neuron.model.TipoAcesso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateAcessoDto {

    private TipoAcesso tipoAcesso;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(max = 255, message = "Limite de caracteres excedido")
    private String descricao;

    public TipoAcesso getTipoAcesso() {
        return tipoAcesso;
    }

    public void setTipoAcesso(TipoAcesso tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }

    public @NotBlank(message = "A descrição é obrigatória") @Size(max = 255, message = "Limite de caracteres excedido") String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank(message = "A descrição é obrigatória") @Size(max = 255, message = "Limite de caracteres excedido") String descricao) {
        this.descricao = descricao;
    }
}