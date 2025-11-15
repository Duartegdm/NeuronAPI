package com.neuron.beans;

import com.neuron.dto.usuario.CreateUsuarioDto;
import com.neuron.model.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.modelmapper.ModelMapper;

public class ModelMapperProducer {

    @ApplicationScoped
    @Produces
    public ModelMapper modelMapper(){
        ModelMapper mapper = new ModelMapper();
        mapper.typeMap(CreateUsuarioDto.class, Usuario.class).addMappings(m -> {
            m.map(CreateUsuarioDto::getSenha, Usuario::setSenhaHash);
        });
        return mapper;
    }
}