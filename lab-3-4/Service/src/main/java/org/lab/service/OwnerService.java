package org.lab.service;

import lombok.Getter;
import lombok.Setter;
import org.lab.dao.models.Owner;
import org.lab.dao.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Service
@ComponentScan({"org.lab.dao.repositories"})
public class OwnerService {
    private OwnerRepository ownerRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository){
        this.ownerRepository = ownerRepository;
    }
    public Optional<Owner> findOwnerById(Long id){
        return ownerRepository.findById(id);
    }

    public Owner saveOwner(Owner owner){
        return ownerRepository.save(owner);
    }

    public void updateOwner(){
        ownerRepository.flush();
    }

    public List<Owner> findAll(){
        return ownerRepository.findAll();
    }

    public void deleteOwnerById(Long id){
        ownerRepository.deleteById(id);
    }

}
