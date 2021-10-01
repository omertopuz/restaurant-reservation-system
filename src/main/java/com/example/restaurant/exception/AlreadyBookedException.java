package com.example.restaurant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Selected option has already been booked by someone else, try another option.")
public class AlreadyBookedException extends RuntimeException{

    public AlreadyBookedException(String message)
    {
        super(message);
    }

}
