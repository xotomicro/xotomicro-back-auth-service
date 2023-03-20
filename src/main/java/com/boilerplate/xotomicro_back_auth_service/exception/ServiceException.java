package com.boilerplate.xotomicro_back_auth_service.exception;

public class ServiceException extends RuntimeException {
    static final long serialVersionUID = 1L;

    public ServiceException(String message) {
        super(message);
    }
}
