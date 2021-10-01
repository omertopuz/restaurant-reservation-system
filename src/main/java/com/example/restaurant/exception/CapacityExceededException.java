package com.example.restaurant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Selected option has capacity smaller than your request.")
public class CapacityExceededException extends RuntimeException{

    public CapacityExceededException(String message)
    {
        super(message);
    }

}
