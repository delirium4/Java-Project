package org.lab.controller.Exceptions;

public class CatNotFoundException extends RuntimeException{
    public CatNotFoundException(Long id){
        super("Could not find cat " + id);
    }
}
