package com.neuron.service;

import com.neuron.dao.EmocaoDao;
import com.neuron.dao.RegistroEmocaoDao;
import com.neuron.dto.registroEmocao.CreateRegistroEmocaoDto;
import com.neuron.dto.registroEmocao.ListRegistroEmocaoDto;
import com.neuron.model.RegistroEmocao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class RegistroEmocaoService {

    @Inject
    RegistroEmocaoDao registroEmocaoDao;

    @Inject
    EmocaoDao emocaoDao;


    public RegistroEmocao create(CreateRegistroEmocaoDto dto) {
        if (dto.getDsRegistEmocao() == null || dto.getDsRegistEmocao().isBlank()) {
            throw new IllegalArgumentException("Descrição obrigatória");
        }

        try {
            RegistroEmocao registro = new RegistroEmocao();
            registro.setIntRegistEmocao(dto.getIntRegistEmocao());
            registro.setDsRegistEmocao(dto.getDsRegistEmocao());
            registro.setDtRegistEmocao(dto.getDtRegistEmocao() != null ?
                    LocalDateTime.parse(dto.getDtRegistEmocao()) : LocalDateTime.now());
            registro.setIdEmocao(dto.getIdEmocao());
            registro.setIdUsuario(dto.getIdUsuario());

            registroEmocaoDao.cadastrar(registro);
            return registro;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar registro de emoção", e);
        }

    }

    public List<ListRegistroEmocaoDto> listarPorUsuario(int idUsuario) throws SQLException {
        List<RegistroEmocao> registros = registroEmocaoDao.listarPorUsuario(idUsuario);

        return registros.stream().map(registro -> {
            ListRegistroEmocaoDto dto = new ListRegistroEmocaoDto();

            dto.setData(registro.getDtRegistEmocao());
            dto.setIntensidade(registro.getIntRegistEmocao() / 10.0);

            try {
                dto.setEmocao(emocaoDao.buscarNomeEmocao(registro.getIdEmocao()));
            } catch (SQLException e) {
                e.printStackTrace();
                dto.setEmocao(null);
            }

            return dto;
        }).toList();
    }

}
