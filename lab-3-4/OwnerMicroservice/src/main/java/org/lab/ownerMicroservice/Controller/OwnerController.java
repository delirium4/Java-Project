package org.lab.ownerMicroservice.Controller;

import org.lab.dao.ListWrappers.OwnerListWrapper;
import org.lab.dao.models.Owner;
import org.lab.dao.responseModels.OwnerModel;
import org.lab.ownerMicroservice.Exceptions.OwnerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.lab.ownerMicroservice.Service.OwnerService;

import java.util.List;

@RestController
public class OwnerController {
    private final OwnerService ownerService;

    @Autowired
    OwnerController(OwnerService ownerService){
        this.ownerService = ownerService;
    }

    @GetMapping("/owners")
    OwnerListWrapper findAll(){
        return new OwnerListWrapper(ownerService.findAll().stream().map(OwnerModel::new).toList());
    }

    @GetMapping("/owners/{id}")
    OwnerModel findOwner(@PathVariable Long id){
        return new OwnerModel(ownerService.findOwnerById(id).orElseThrow(() -> new OwnerNotFoundException(id)));
    }

    @DeleteMapping("/deleteOwner/{id}")
    void deleteOwner(@PathVariable Long id){
        ownerService.deleteOwnerById(id);
    }

}
