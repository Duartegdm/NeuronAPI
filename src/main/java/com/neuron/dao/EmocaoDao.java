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
}
