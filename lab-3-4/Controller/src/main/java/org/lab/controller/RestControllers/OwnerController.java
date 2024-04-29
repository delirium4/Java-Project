package org.lab.controller.RestControllers;

import org.lab.controller.Exceptions.OwnerNotFoundException;
import org.lab.dao.models.Owner;
import org.lab.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ComponentScan(basePackages = "org.lab.service")
public class OwnerController {
    private final OwnerService ownerService;

    @Autowired
    OwnerController(OwnerService ownerService){
        this.ownerService = ownerService;
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/owners")
    ResponseEntity<List<Owner>> findAll(){
        return new ResponseEntity<>(ownerService.findAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/owners")
    ResponseEntity<Owner> saveOwner(@RequestBody Owner newOwner){
        return new ResponseEntity<>(ownerService.saveOwner(newOwner), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/owners/{id}")
    ResponseEntity<Owner> findOwner(@PathVariable Long id){
        return new ResponseEntity<>(ownerService.findOwnerById(id).orElseThrow(() -> new OwnerNotFoundException(id)), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/owners/{id}")
    ResponseEntity<Owner> replaceOwner(@RequestBody Owner newOwner, @PathVariable Long id){
        return new ResponseEntity<>(ownerService.findOwnerById(id).map(owner -> {
            owner.setOwnerName(newOwner.getOwnerName());
            owner.setBirthDate(newOwner.getBirthDate());
            return ownerService.saveOwner(owner);
        }).orElseGet(() -> {
            newOwner.setId(id);
            return ownerService.saveOwner(newOwner);
        }), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/owners/{id}")
    void deleteOwner(@PathVariable Long id){
        ownerService.deleteOwnerById(id);
    }

}
