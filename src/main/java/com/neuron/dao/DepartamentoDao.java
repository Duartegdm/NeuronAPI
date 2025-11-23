package com.neuron.dao;

import com.neuron.model.Acesso;
import com.neuron.model.Departamento;
import com.neuron.model.TipoAcesso;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class DepartamentoDao {

    @Inject
    DataSource dataSource;

    public void cadastrar(Departamento departamento) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("insert into t_nron_departamento (id_departamento, nm_departamento, ds_departamento) " +
                    "values (seq_nron_departamento.NEXTVAL, ?, ?)", new String[]{"id_departamento"});
            stmt.setString(1, departamento.getNome());
            stmt.setString(2, departamento.getDescricao());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                departamento.setCodigo(rs.getInt(1));
            }
        }
    }

    public List<Departamento> listar() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from t_nron_departamento");

            List<Departamento> departamentos = new ArrayList<>();

            while(rs.next()) {
                Departamento departamento = new Departamento();

                departamento.setCodigo(rs.getInt(1));
                departamento.setNome(rs.getString(2));
                departamento.setDescricao(rs.getString(3));

                departamentos.add(departamento);
            }

            return departamentos;
        }
    }
}
