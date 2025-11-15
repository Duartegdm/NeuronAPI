package com.neuron.dao;

import com.neuron.model.Acesso;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@ApplicationScoped
public class AcessoDao {

    @Inject
    DataSource dataSource;

    public void cadastrar(Acesso acesso) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("insert into t_nron_acesso (id_acesso, tp_acesso, ds_acesso) values " +
                    "(seq_nron_id_acesso.NEXTVAL, ?, ?");
            stmt.setObject(1, acesso.getTipoAcesso());
            stmt.setString(2, acesso.getDescricao());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()) {
                acesso.setCodigo(rs.getInt(1));
            }
        }
    }
}
