package com.neuron.service;

import com.neuron.dao.RespostaFormularioDao;
import com.neuron.dto.respostaFormulario.CreateRespostaFormularioDto;
import com.neuron.model.RespostaFormulario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class RespostaFormularioService {

    @Inject
    RespostaFormularioDao dao;

    public RespostaFormulario create(CreateRespostaFormularioDto dto) {
        try {
            RespostaFormulario resposta = new RespostaFormulario();
            resposta.setDtResposta(dto.getDtResposta() != null ? LocalDateTime.parse(dto.getDtResposta()) : LocalDateTime.now());
            resposta.setMotivacao(dto.getMotivacao());
            resposta.setFelicidade(dto.getFelicidade());
            resposta.setEstresse(dto.getEstresse());
            resposta.setObservacao(dto.getObservacao());
            resposta.setSaudeMental(dto.getSaudeMental());
            resposta.setProblemas(dto.getProblemas());
            resposta.setModoVer(dto.getModoVer());
            resposta.setDtAnalise(dto.getDtAnalise() != null ? LocalDateTime.parse(dto.getDtAnalise()) : LocalDateTime.now());
            resposta.setIdUsuario(dto.getIdUsuario());
            resposta.setIdRegistEmocao(dto.getIdRegistEmocao());

            dao.cadastrar(resposta);
            return resposta;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar resposta do formulário", e);
        }
    }

    public List<RespostaFormulario> listarPorUsuario(int idUsuario) throws Exception {
        return dao.listarPorUsuario(idUsuario);
    }
}
