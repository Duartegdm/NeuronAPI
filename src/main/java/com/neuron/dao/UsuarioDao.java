package com.neuron.dao;

import com.neuron.model.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@ApplicationScoped
public class UsuarioDao {

    @Inject
    private DataSource dataSource;

    public void cadastrar(Usuario usuario) throws SQLException {

        try (Connection conexao = dataSource.getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("insert into t_nron_usuario (id_usuario, nome, em_usuario, " +
                    "sen_hash_usuario, stt_usuario, dt_cadastro, id_acesso, id_departamento) values (seq_nron_id_usuario.NEXTVAL, " +
                    "?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenhaHash());
            stmt.setBoolean(4, usuario.isStatusAtivo());
            stmt.setObject(5, usuario.getDataCadastro());
            stmt.setInt(6, usuario.getCodigoAcesso());
            stmt.setInt(7, usuario.getCodigoDepartamento());

            stmt.executeUpdate();

            //Recuperar o valor da chave gerada para o doce
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                usuario.setCodigo(rs.getInt(1));
            }
        }
    }
}
