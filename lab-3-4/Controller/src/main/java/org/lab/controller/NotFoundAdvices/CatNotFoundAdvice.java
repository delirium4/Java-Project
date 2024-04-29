package org.lab.controller.NotFoundAdvices;

import org.lab.controller.Exceptions.CatNotFoundException;
import org.lab.controller.Exceptions.OwnerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CatNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(CatNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String CatNotFoundHandler(CatNotFoundException exception){
        return exception.getMessage();
    }
}
