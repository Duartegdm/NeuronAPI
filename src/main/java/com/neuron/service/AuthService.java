package com.neuron.service;
import io.quarkus.elytron.security.common.BcryptUtil;

public class AuthService {
    public String gerarHashSenha(String senha) {
        return BcryptUtil.bcryptHash(senha);
    }
}
