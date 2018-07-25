package com.hibejix.cursomc.security.exceptions;

import org.springframework.security.core.AuthenticationException;


public class JwtExpiredTokenException extends AuthenticationException {

    private String token;

    public JwtExpiredTokenException(String msg) {
        super(msg);
        System.out.println(msg);
    }

    public JwtExpiredTokenException(String token, String msg, Throwable t) {
        super(msg, t);
        this.token = token;
        System.out.println(msg);
    }

    public String token() {
        return this.token;
    }
}
