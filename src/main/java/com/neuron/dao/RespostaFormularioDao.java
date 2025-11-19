package com.neuron.dao;

import com.neuron.model.RespostaFormulario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class RespostaFormularioDao {

    @Inject
    DataSource dataSource;

    public void cadastrar(RespostaFormulario resposta) throws SQLException {
        String sql = "INSERT INTO T_NRON_RESP_FORMULARIO " +
                "(ID_RESPOSTA, DT_RESPOSTA, MOT_RESPOSTA, FEL_RESPOSTA, EST_RESPOSTA, OBS_RESPOSTA, " +
                "SAU_MEN_RESPOSTA, PROB_RESPOSTA, MOD_VER_RESPOSTA, DT_ANL_RESPOSTA, ID_USUARIO, ID_REGIST_EMOCAO) " +
                "VALUES (SEQ_RESP_FORMULARIO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, new String[]{"ID_RESPOSTA"})) {

            stmt.setTimestamp(1, Timestamp.valueOf(resposta.getDtResposta()));
            stmt.setInt(2, resposta.getMotivacao());
            stmt.setInt(3, resposta.getFelicidade());
            stmt.setInt(4, resposta.getEstresse());
            stmt.setString(5, resposta.getObservacao());
            stmt.setInt(6, resposta.getSaudeMental());
            stmt.setInt(7, resposta.getProblemas());
            stmt.setInt(8, resposta.getModoVer());
            stmt.setTimestamp(9, Timestamp.valueOf(resposta.getDtAnalise()));
            stmt.setInt(10, resposta.getIdUsuario());
            stmt.setInt(11, resposta.getIdRegistEmocao());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    resposta.setIdResposta(rs.getInt(1));
                }
            }
        }
    }


    public List<RespostaFormulario> listarPorUsuario(int idUsuario) throws SQLException {
        String sql = "SELECT ID_RESPOSTA, DT_RESPOSTA, MOT_RESPOSTA, FEL_RESPOSTA, EST_RESPOSTA, OBS_RESPOSTA, " +
                "SAU_MEN_RESPOSTA, PROB_RESPOSTA, MOD_VER_RESPOSTA, DT_ANL_RESPOSTA, ID_USUARIO, ID_REGIST_EMOCAO " +
                "FROM T_NRON_RESP_FORMULARIO " +
                "WHERE ID_USUARIO = ? " +
                "ORDER BY DT_RESPOSTA DESC";

        List<RespostaFormulario> respostas = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    RespostaFormulario r = new RespostaFormulario();
                    r.setIdResposta(rs.getInt("ID_RESPOSTA"));
                    r.setDtResposta(rs.getTimestamp("DT_RESPOSTA").toLocalDateTime());
                    r.setMotivacao(rs.getInt("MOT_RESPOSTA"));
                    r.setFelicidade(rs.getInt("FEL_RESPOSTA"));
                    r.setEstresse(rs.getInt("EST_RESPOSTA"));
                    r.setObservacao(rs.getString("OBS_RESPOSTA"));
                    r.setSaudeMental(rs.getInt("SAU_MEN_RESPOSTA"));
                    r.setProblemas(rs.getInt("PROB_RESPOSTA"));
                    r.setModoVer(rs.getInt("MOD_VER_RESPOSTA"));
                    r.setDtAnalise(rs.getTimestamp("DT_ANL_RESPOSTA").toLocalDateTime());
                    r.setIdUsuario(rs.getInt("ID_USUARIO"));
                    r.setIdRegistEmocao(rs.getInt("ID_REGIST_EMOCAO"));
                    respostas.add(r);
                }
            }
        }

        return respostas;
    }
}
