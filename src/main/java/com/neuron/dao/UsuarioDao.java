package com.neuron.dao;

import com.neuron.model.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.*;

@ApplicationScoped
public class UsuarioDao {

    @Inject
    private DataSource dataSource;

    public void cadastrar(Usuario usuario) throws SQLException {

        try (Connection conexao = dataSource.getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("insert into t_nron_usuario (id_usuario, nm_usuario, em_usuario, " +
                    "sen_hash_usuario, stt_usuario, dt_cad_usuario, id_acesso, id_departamento) values (seq_nron_usuario.NEXTVAL, " +
                    "?, ?, ?, ?, ?, ?, ?)", new String[]{"id_usuario"});

            System.out.println("Usuario no DAO: "+usuario);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenhaHash());
            stmt.setBoolean(4, usuario.isStatusAtivo());
            stmt.setDate(5, java.sql.Date.valueOf(usuario.getDataCadastro()));
            stmt.setInt(6, usuario.getCodigoAcesso());
            stmt.setInt(7, usuario.getCodigoDepartamento());

            stmt.executeUpdate();

            //Recuperar o valor da chave gerada para o doce
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                usuario.setId(rs.getInt(1));
            }
        }
    }

    public Usuario buscarPorEmail(String email) throws SQLException {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("select * from t_nron_usuario where " +
                    "em_usuario = ?");
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("result set > 0");
                return mapearUsuario(rs);
            }
            System.out.println("passou o retorno");
            throw new RuntimeException("E-mail não encontrado");
        }
    }

    private Usuario mapearUsuario(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setId(rs.getInt("id_usuario"));
        u.setNome(rs.getString("nm_usuario"));
        u.setEmail(rs.getString("em_usuario"));
        u.setSenhaHash(rs.getString("sen_hash_usuario"));
        u.setStatusAtivo(rs.getBoolean("stt_usuario"));
        u.setCodigoAcesso(rs.getInt("id_acesso"));
        u.setCodigoDepartamento(rs.getInt("id_departamento"));
        System.out.println(u);
        return u;
    }
}
