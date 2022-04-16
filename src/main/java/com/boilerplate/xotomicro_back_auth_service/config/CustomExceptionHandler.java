package com.boilerplate.xotomicro_back_auth_service.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.boilerplate.xotomicro_back_auth_service.dto.ErrorDto;
import com.boilerplate.xotomicro_back_auth_service.exception.ServiceException;

@ControllerAdvice
public class CustomExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleServiceException(ServiceException ex) {
        logger.warn("Error: {}", ex.getMessage());
        return new ErrorDto(ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleException(Exception ex) {
        logger.warn("Exception: ", ex);
        return new ErrorDto("Internal server error");
    }
}
