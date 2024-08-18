package org.lab.externalInterfacesMicroservice.RestControllers;

import org.lab.dao.ListWrappers.OwnerListWrapper;
import org.lab.dao.models.Owner;
import org.lab.dao.responseModels.CatModel;
import org.lab.dao.responseModels.OwnerModel;
import org.lab.dao.responseModels.OwnerSaveModel;
import org.lab.externalInterfacesMicroservice.Publishers.OwnerProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@ComponentScan(basePackages = "org.lab.ownerMicroservice")
public class OwnerController {
    private final OwnerProducer ownerProducer;

    @Autowired
    OwnerController(OwnerProducer ownerProducer){
        this.ownerProducer = ownerProducer;
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/owners")
    ResponseEntity<List<OwnerModel>> findAll(){
        OwnerListWrapper responseEntity =
                new RestTemplate().getForObject(
                        "http://localhost:8082/owners", OwnerListWrapper.class);
        return new ResponseEntity<>(responseEntity.getOwners(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/owners")
    void saveOwner(@RequestBody OwnerSaveModel newOwner){
        ownerProducer.sendOwner(newOwner);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/owners/{id}")
    ResponseEntity<OwnerModel> findOwner(@PathVariable Long id){
        OwnerModel responseEntity =
                new RestTemplate().getForObject(
                        "http://localhost:8082/owners/{id}", OwnerModel.class, id);
        return new ResponseEntity<>(responseEntity, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/owners/{id}")
    void replaceOwner(@RequestBody OwnerSaveModel newOwner){
        ownerProducer.sendOwner(newOwner);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/deleteOwner/{id}")
    void deleteOwner(@PathVariable Long id){
        new RestTemplate().delete("http://localhost:8082/deleteOwner/{id}", id);
    }

}
