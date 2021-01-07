package com.example.numbergenerator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Invalid task input")
public class InvalidTaskInputException extends RuntimeException {

}
