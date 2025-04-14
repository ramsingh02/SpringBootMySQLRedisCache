package com.mysql.redis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourcrNotFoundException extends RuntimeException{

    public ResourcrNotFoundException(String message){
        super(message);
    }
}
