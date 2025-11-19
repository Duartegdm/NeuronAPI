package com.neuron.dao;

import com.neuron.dto.dashboardRh.EmocaoMaisComumDto;
import com.neuron.dto.dashboardRh.EvolucaoEmocionalDto;
import com.neuron.dto.dashboardRh.IndicadoresDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class DashboarRhdDao {

    @Inject
    DataSource dataSource;

    public IndicadoresDto buscarIndicadores(Long idUsuario) {
        String sql = """
            SELECT 
                COUNT(*) AS total_registros,
                NVL(AVG(INT_REGIST_EMOCAO), 0) AS media_intensidade
            FROM T_NRON_REGIST_EMOCAO
            WHERE ID_USUARIO = ?
        """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, idUsuario);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new IndicadoresDto(
                            rs.getInt("total_registros"),
                            rs.getDouble("media_intensidade")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new IndicadoresDto(0, 0.0);
    }


    public EmocaoMaisComumDto buscarEmocaoMaisComum(Long idUsuario) {
        String sql = """
                SELECT emocao, COUNT(*) AS qtd
                FROM registro_emocao
                WHERE id_usuario = ?
                GROUP BY emocao
                ORDER BY qtd DESC
                FETCH FIRST 1 ROWS ONLY
            """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, idUsuario);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new EmocaoMaisComumDto(
                            rs.getString("emocao"),
                            rs.getInt("qtd")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new EmocaoMaisComumDto("Desconhecida", 0);
    }

    public List<EvolucaoEmocionalDto> buscarEvolucaoEmocional(Long idUsuario) {
        String sql = """
                SELECT 
                    TRUNC(data_registro) AS dia,
                    AVG(intensidade) AS media_dia
                FROM registro_emocao
                WHERE id_usuario = ?
                GROUP BY TRUNC(data_registro)
                ORDER BY dia ASC
            """;

        List<EvolucaoEmocionalDto> lista = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, idUsuario);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new EvolucaoEmocionalDto(
                            rs.getDate("dia").toLocalDate(),
                            rs.getDouble("media_dia")
                    ));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
