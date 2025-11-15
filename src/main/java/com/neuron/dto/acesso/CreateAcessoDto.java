package com.neuron.dto.acesso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateAcessoDto {

    @NotBlank(message = "O tipo de acesso é obrigatório")
    @Size(max = 50, message = "Limite de caracteres excedido")
    private String tipoAcesso;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(max = 255, message = "Limite de caracteres excedido")
    private String descricao;

    public @NotBlank(message = "O tipo de acessi é obrigatório") @Size(max = 50, message = "Limite de caracteres excedido") String getTipoAcesso() {
        return tipoAcesso;
    }

    public void setTipoAcesso(@NotBlank(message = "O tipo de acessi é obrigatório") @Size(max = 50, message = "Limite de caracteres excedido") String tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }

    public @NotBlank(message = "A descrição é obrigatória") @Size(max = 255, message = "Limite de caracteres excedido") String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank(message = "A descrição é obrigatória") @Size(max = 255, message = "Limite de caracteres excedido") String descricao) {
        this.descricao = descricao;
    }
}
