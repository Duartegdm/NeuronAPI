package com.neuron.service;

import com.neuron.dao.UsuarioDao;
import com.neuron.dto.auth.RegisterDto;
/*import com.neuron.exception.InvalidCredentialsException;*/
import com.neuron.exception.InvalidCredentialsException;
import com.neuron.model.Usuario;
import com.neuron.utils.PasswordUtils;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.time.LocalDate;

@ApplicationScoped
public class UsuarioService {

    @Inject
    UsuarioDao usuarioDao;

    @Inject
    ModelMapper mapper;

    public Usuario registrar(RegisterDto dto) throws SQLException {
        Usuario usuario = mapper.map(dto, Usuario.class);

        usuario.setStatusAtivo(true);
        usuario.setDataCadastro(LocalDate.now());
        usuario.setSenhaHash(PasswordUtils.hash(dto.getSenha()));

        usuarioDao.cadastrar(usuario);
        return usuario;
    }

    public Usuario autenticar(String email, String senha) throws SQLException {
        Usuario usuario = usuarioDao.buscarPorEmail(email);
        if (!PasswordUtils.verify(senha, usuario.getSenhaHash())) {
            throw new InvalidCredentialsException();
        }
        return usuario;
    }

    public Usuario buscarPorEmail(String email) throws SQLException {
        return usuarioDao.buscarPorEmail(email);
    }
}
