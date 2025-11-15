package com.neuron.dao;

import com.neuron.model.Departamento;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@ApplicationScoped
public class DepartamentoDao {

    @Inject
    DataSource dataSource;

    public void cadastrar(Departamento departamento) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("insert into t_nron_departamento (id_departamento, nome_departamento, ds_departamento) " +
                    "values (seq_nron_id_departamento, ?, ?)");
            stmt.setString(1, departamento.getNome());
            stmt.setString(2, departamento.getDescricao());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                departamento.setCodigo(rs.getInt(1));
            }
        }
    }
}
