package com.neuron.dao;

import com.neuron.dto.categoriaEmocao.CreateCatgEmocaoDto;
import com.neuron.model.CatgEmocao;
import com.neuron.model.Emocao;
import com.neuron.model.NomeCatgEmocao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CatgEmocaoDao {

    @Inject
    DataSource dataSource;

    public void cadastrar(CatgEmocao catgEmocao) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("insert into t_nron_catg_emocao (id_catg_emocao, nome_catg_emocao)" +
                    "values (SEQ_NRON_CATG_EMOCAO.NEXTVAL, ?)", new String[]{"id_catg_emocao"});
            stmt.setString(1, catgEmocao.getNome().name());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                catgEmocao.setId(rs.getInt(1));
            }
        }
    }

    public List<CatgEmocao> listar() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from t_nron_catg_emocao");

            List<CatgEmocao> catgEmocoes = new ArrayList<>();

            while(rs.next()) {
                CatgEmocao catgEmocao = new CatgEmocao();

                catgEmocao.setId(rs.getInt(1));
                catgEmocao.setNome(NomeCatgEmocao.valueOf(rs.getString(2)));

                catgEmocoes.add(catgEmocao);
            }

            return catgEmocoes;
        }
    }
}
