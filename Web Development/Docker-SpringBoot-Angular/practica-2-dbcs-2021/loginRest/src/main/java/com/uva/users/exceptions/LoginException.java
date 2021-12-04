package com.uva.users.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class LoginException extends RuntimeException {
    public LoginException(String mensaje) {
        super(mensaje);
    }
}
