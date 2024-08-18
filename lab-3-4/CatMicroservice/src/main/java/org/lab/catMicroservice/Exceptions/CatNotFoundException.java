package org.lab.catMicroservice.Exceptions;

public class CatNotFoundException extends RuntimeException{
    public CatNotFoundException(Long id){
        super("Could not find cat " + id);
    }
}
