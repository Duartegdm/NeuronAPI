package com.neuron.beans;

import com.neuron.dto.auth.RegisterDto;
import com.neuron.dto.categoriaEmocao.CreateCatgEmocaoDto;
import com.neuron.dto.departamento.DetailDepartamentoDto;
import com.neuron.dto.emocao.CreateEmocaoDto;
import com.neuron.dto.usuario.UpdateUsuarioDto;
import com.neuron.model.CatgEmocao;
import com.neuron.model.Departamento;
import com.neuron.model.Emocao;
import com.neuron.model.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.modelmapper.ModelMapper;

public class ModelMapperProducer {

    @ApplicationScoped
    @Produces
    public ModelMapper modelMapper(){
        ModelMapper mapper = new ModelMapper();
        /*
        mapper.typeMap(CreateUsuarioDto.class, Usuario.class).addMappings(m -> {
            m.map(CreateUsuarioDto::getSenha, Usuario::setSenhaHash);
            m.map(CreateUsuarioDto::getCodigoDepartamento, Usuario::setCodigoDepartamento);
            m.map(CreateUsuarioDto::getCodigoAcesso, Usuario::setCodigoAcesso);
        });
        */

        mapper.typeMap(RegisterDto.class, Usuario.class).addMappings(m -> {
            m.map(RegisterDto::getCodigoDepartamento, Usuario::setCodigoDepartamento);
            m.map(RegisterDto::getCodigoAcesso, Usuario::setCodigoAcesso);
        });

        mapper.typeMap(UpdateUsuarioDto.class, Usuario.class).addMappings(m -> {
            m.map(UpdateUsuarioDto::getCodigoDepartamento, Usuario::setCodigoDepartamento);
            m.map(UpdateUsuarioDto::getCodigoAcesso, Usuario::setCodigoAcesso);
        });

        mapper.typeMap(Departamento.class, DetailDepartamentoDto.class).addMappings(m -> {
            m.map(Departamento::getCodigo, DetailDepartamentoDto::setCodigo);
        });

        mapper.typeMap(CreateCatgEmocaoDto.class, CatgEmocao.class).addMappings(m -> {
            m.map(CreateCatgEmocaoDto::getNome, CatgEmocao::setNome);
        });

        mapper.typeMap(CreateEmocaoDto.class, Emocao.class).addMappings(m ->{
            m.map(CreateEmocaoDto::getCodigoCatgEmocao, Emocao::setCodigoCategEmocao);
        });

        return mapper;

    }
}