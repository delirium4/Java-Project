package org.lab.controller.Exceptions;

public class OwnerNotFoundException extends RuntimeException{
    public OwnerNotFoundException(Long id){
        super("Could not find owner " + id);
    }
}
