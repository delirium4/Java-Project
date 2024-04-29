package org.lab.controller.NotFoundAdvices;

import org.lab.controller.Exceptions.OwnerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class OwnerNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(OwnerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String OwnerNotFoundHandler(OwnerNotFoundException exception){
        return exception.getMessage();
    }
}
