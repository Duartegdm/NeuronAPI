package com.neuron.dao;

import com.neuron.model.Acesso;
import com.neuron.model.Emocao;
import com.neuron.model.TipoAcesso;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class EmocaoDao {

    @Inject
    DataSource dataSource;

    public void cadastrar(Emocao emocao) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("insert into t_nron_emocao (id_emocao, nm_emocao, cor_emocao, id_catg_emocao) " +
                    "values (seq_nron_emocao.nextval, ?, ?, ?)", new String[]{"id_emocao"});

            stmt.setString(1, emocao.getNome());
            stmt.setString(2, emocao.getCor());
            stmt.setInt(3, emocao.getCodigoCatgEmocao());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            while (rs.next()){
                emocao.setId(rs.getInt(1));
            }
        }

    }

    public List<Emocao> listar() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from t_nron_emocao");

            List<Emocao> emocoes = new ArrayList<>();

            while(rs.next()) {
                Emocao emocao = new Emocao();

                emocao.setId(rs.getInt(1));
                emocao.setNome(rs.getString(2));
                emocao.setCor(rs.getString(3));
                emocao.setCodigoCategEmocao(rs.getInt(4));

                emocoes.add(emocao);
            }

            return emocoes;
        }
    }

    public List<Emocao> listarPorCategoria(int idCategoria) throws SQLException {
        String sql = """
        SELECT 
            ID_EMOCAO,
            NM_EMOCAO,
            COR_EMOCAO,
            ID_CATG_EMOCAO
        FROM T_NRON_EMOCAO
        WHERE ID_CATG_EMOCAO = ?
        ORDER BY NM_EMOCAO
    """;

        List<Emocao> lista = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idCategoria);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Emocao e = new Emocao();
                e.setId(rs.getInt("ID_EMOCAO"));
                e.setNome(rs.getString("NM_EMOCAO"));
                e.setCor(rs.getString("COR_EMOCAO"));
                e.setCodigoCategEmocao(rs.getInt("ID_CATG_EMOCAO"));
                lista.add(e);
            }
        }

        return lista;
    }


    public String buscarNomeEmocao(int id) throws SQLException {
        String sql = "SELECT NM_EMOCAO FROM T_NRON_EMOCAO WHERE ID_EMOCAO = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) return rs.getString("NM_EMOCAO");
            return null;
        }
    }

}
