package com.neuron.dao;

import com.neuron.model.Acesso;
import com.neuron.model.TipoAcesso;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AcessoDao {

    @Inject
    DataSource dataSource;

    public void cadastrar(Acesso acesso) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("insert into t_nron_acesso (id_acesso, tp_acesso, ds_acesso) values " +
                    "(seq_nron_acesso.NEXTVAL, ?, ?)", new String[]{"id_acesso"});
            stmt.setString(1, acesso.getTipoAcesso().name());
            stmt.setString(2, acesso.getDescricao());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()) {
                acesso.setCodigo(rs.getInt(1));
            }
        }
    }

    public List<Acesso> listar() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from t_nron_acesso");

            List<Acesso> acessos = new ArrayList<>();

            while(rs.next()) {
                Acesso acesso = new Acesso();

                acesso.setCodigo(rs.getInt(1));
                acesso.setTipoAcesso(TipoAcesso.valueOf(rs.getString(2)));
                acesso.setDescricao(rs.getString(3));

                acessos.add(acesso);
            }

            return acessos;
        }
    }
}
