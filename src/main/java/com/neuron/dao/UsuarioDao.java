package com.neuron.dao;

import com.neuron.model.CatgEmocao;
import com.neuron.model.Emocao;
import com.neuron.model.NomeCatgEmocao;
import com.neuron.model.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

            setInsertColumns(usuario, stmt);

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
                return mapearUsuario(rs);
            }
            return null;
        }
    }

    public List<Usuario> listar() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from t_nron_usuario");

            List<Usuario> usuarios = new ArrayList<>();

            while(rs.next()) {
                Usuario usuario = new Usuario();

                usuario = mapearUsuario(rs);

                usuarios.add(usuario);
            }

            return usuarios;
        }
    }

    public void atualizar(Usuario usuario) throws SQLException {
        try (Connection conexao = dataSource.getConnection()) {
            String sql = "UPDATE t_nron_usuario SET "
                    + "nm_usuario = ?, "
                    + "em_usuario = ?, "
                    + "stt_usuario = ?, "
                    + "sen_hash_usuario = ?, "
                    + "dt_cad_usuario = ?, "
                    + "id_acesso = ?, "
                    + "id_departamento = ? "
                    + "WHERE id_usuario = ?";

            System.out.println("Tentando atualizar usuário ID: " + usuario.getId() +
                    " com email: " + usuario.getEmail());

            PreparedStatement stmt = conexao.prepareStatement(sql);

            setUpdateColumns(usuario, stmt);

            int rows = stmt.executeUpdate();

            if (rows == 0) {
                throw new RuntimeException("Usuário não encontrado para atualização. ID: " + usuario.getId());
            }
        }
    }

    public void deletar(int idUsuario) throws SQLException {
        try (Connection conexao = dataSource.getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement(
                    "DELETE FROM t_nron_usuario WHERE id_usuario = ?"
            );

            stmt.setInt(1, idUsuario);

            int rows = stmt.executeUpdate();

            if (rows == 0) {
                throw new RuntimeException("Usuário não encontrado para exclusão. ID: " + idUsuario);
            }
        }
    }

    public void deletar(String email) throws SQLException {
        try (Connection conexao = dataSource.getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement(
                    "DELETE FROM t_nron_usuario WHERE em_usuario = ?"
            );
            stmt.setString(1, email);

            int rows = stmt.executeUpdate();

            if (rows == 0) {
                throw new RuntimeException("Usuário não encontrado para exclusão: " + email);
            }
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

    private static void setUpdateColumns(Usuario usuario, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getEmail());
        stmt.setBoolean(3, usuario.isStatusAtivo());
        stmt.setString(4, usuario.getSenhaHash());
        stmt.setDate(5, Date.valueOf(usuario.getDataCadastro()));
        stmt.setInt(6, usuario.getCodigoAcesso());
        stmt.setInt(7, usuario.getCodigoDepartamento());
        stmt.setInt(8, usuario.getId());
    }

    private void setInsertColumns(Usuario usuario, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getEmail());
        stmt.setString(3, usuario.getSenhaHash());
        stmt.setBoolean(4, usuario.isStatusAtivo());
        stmt.setDate(5, Date.valueOf(usuario.getDataCadastro()));
        stmt.setInt(6, usuario.getCodigoAcesso());
        stmt.setInt(7, usuario.getCodigoDepartamento());
    }
}
