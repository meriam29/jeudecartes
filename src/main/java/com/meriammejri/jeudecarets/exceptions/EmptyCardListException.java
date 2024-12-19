package com.meriammejri.jeudecarets.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmptyCardListException extends RuntimeException {
    public EmptyCardListException(String message) {
        super(message);
    }
}