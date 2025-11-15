package com.neuron.service;

import com.neuron.dao.UsuarioDao;
import com.neuron.dto.usuario.CreateUsuarioDto;
import com.neuron.model.Usuario;
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

    public Usuario criarUsuario(CreateUsuarioDto dto) throws SQLException {

        Usuario usuario = mapper.map(dto, Usuario.class);
        usuario.setStatusAtivo(true);
        usuario.setDataCadastro(LocalDate.now());
        usuario.setSenhaHash(BcryptUtil.bcryptHash(usuario.getSenhaHash()));

        usuarioDao.cadastrar(usuario);
        return usuario;
    }
}
