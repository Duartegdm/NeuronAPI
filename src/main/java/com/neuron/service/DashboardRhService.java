package com.neuron.service;


import com.neuron.dao.DashboarRhdDao;
import com.neuron.dto.dashboardRh.EmocaoMaisComumDto;
import com.neuron.dto.dashboardRh.EvolucaoEmocionalDto;
import com.neuron.dto.dashboardRh.IndicadoresDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class DashboardRhService {

    @Inject
    DashboarRhdDao dao;

    public IndicadoresDto indicadoresGerais(Long idUsuario) {
        return dao.buscarIndicadores(idUsuario);
    }

    public EmocaoMaisComumDto emocaoMaisComum(Long idUsuario) {
        return dao.buscarEmocaoMaisComum(idUsuario);
    }

    public List<EvolucaoEmocionalDto> evolucaoEmocional(Long idUsuario) {
        return dao.buscarEvolucaoEmocional(idUsuario);
    }
}
