package com.neuron.utils;

import io.quarkus.elytron.security.common.BcryptUtil;

public class PasswordUtils {
    public static String hash(String senha) {
        return BcryptUtil.bcryptHash(senha);
    }

    public static Boolean verify(String senha, String senhaHash) {
        return BcryptUtil.matches(senha, senhaHash);
    }
}
