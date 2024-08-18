package org.lab.catMicroservice.Service;

import lombok.Getter;
import lombok.Setter;
import org.lab.dao.models.Cat;
import org.lab.dao.repositories.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Service
@ComponentScan({"org.lab.dao.repositories"})
public class CatService {
    private CatRepository catRepository;
    @Autowired
    public CatService(CatRepository catRepository){
        this.catRepository = catRepository;
    }
    public Optional<Cat> findCatById(Long id){
        return catRepository.findById(id);
    }

    public void save(Cat cat){
        catRepository.save(cat);
    }

    public void updateCat(){
        catRepository.flush();
    }

    public List<Cat> findAll(){
        return catRepository.findAll();
    }

    public void deleteCatById(Long id){
        catRepository.deleteById(id);
    }

    public void createRelations(Cat firstCat, Cat secondCat){
        firstCat.addFriend(secondCat);
        catRepository.saveAndFlush(firstCat);
        catRepository.saveAndFlush(secondCat);
    }

    public List<Cat> findCatsByBreed(String breed){
        return catRepository.findCatsByBreed(breed);
    }

    public List<Cat> findCatsByColor(Long colorId){
        return catRepository.findCatsByColorId(colorId);
    }

    public List<Cat> findCatsByOwnerId(Long id){
        return catRepository.findCatsByOwner_Id(id);
    }
}
