package com.neuron.dao;

import com.neuron.model.RegistroEmocao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class RegistroEmocaoDao {

    @Inject
    DataSource dataSource;

    public void cadastrar(RegistroEmocao registro) throws SQLException {
        String sql = "INSERT INTO T_NRON_REGIST_EMOCAO " +
                "(ID_REGIST_EMOCAO, INT_REGIST_EMOCAO, DS_REGIST_EMOCAO, DT_REGIST_EMOCAO, ID_EMOCAO, ID_USUARIO) " +
                "VALUES (SEQ_REGIST_EMOCAO.NEXTVAL, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, new String[]{"ID_REGIST_EMOCAO"})) {

            stmt.setInt(1, registro.getIntRegistEmocao());
            stmt.setString(2, registro.getDsRegistEmocao());
            stmt.setTimestamp(3, Timestamp.valueOf(registro.getDtRegistEmocao()));
            stmt.setInt(4, registro.getIdEmocao());
            stmt.setInt(5, registro.getIdUsuario());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    registro.setIdRegistEmocao(rs.getInt(1));
                }
            }
        }
    }


    public List<RegistroEmocao> listarPorUsuario(int idUsuario) throws SQLException {
        String sql = "SELECT ID_REGIST_EMOCAO, INT_REGIST_EMOCAO, DS_REGIST_EMOCAO, DT_REGIST_EMOCAO, ID_EMOCAO, ID_USUARIO " +
                "FROM T_NRON_REGIST_EMOCAO WHERE ID_USUARIO = ? ORDER BY DT_REGIST_EMOCAO DESC";

        List<RegistroEmocao> registros = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    RegistroEmocao r = new RegistroEmocao();
                    r.setIdRegistEmocao(rs.getInt("ID_REGIST_EMOCAO"));
                    r.setIntRegistEmocao(rs.getInt("INT_REGIST_EMOCAO"));
                    r.setDsRegistEmocao(rs.getString("DS_REGIST_EMOCAO"));
                    r.setDtRegistEmocao(rs.getTimestamp("DT_REGIST_EMOCAO").toLocalDateTime());
                    r.setIdEmocao(rs.getInt("ID_EMOCAO"));
                    r.setIdUsuario(rs.getInt("ID_USUARIO"));
                    registros.add(r);
                }
            }
        }

        return registros;
    }

}
