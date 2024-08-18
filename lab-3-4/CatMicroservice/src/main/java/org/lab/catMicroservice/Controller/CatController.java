package org.lab.catMicroservice.Controller;

import lombok.AllArgsConstructor;
import org.lab.catMicroservice.Exceptions.CatNotFoundException;
import org.lab.dao.ListWrappers.CatListWrapper;
import org.lab.catMicroservice.Service.CatService;
import org.lab.dao.responseModels.CatModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class CatController {
    private final CatService catService;


    @GetMapping("/cats")
    CatListWrapper findAll(){
        return new CatListWrapper(catService.findAll().stream().map(CatModel::new).toList());
    }

    @GetMapping("/cat/{id}")
    ResponseEntity<CatModel> findCat(@PathVariable Long id){
        return new ResponseEntity<>(new CatModel(catService.findCatById(id).orElseThrow(() -> new CatNotFoundException(id))), HttpStatus.OK);
    }

    @DeleteMapping("/deleteCat/{id}")
    void deleteCat(@PathVariable Long id){
        catService.deleteCatById(id);
    }

    @GetMapping("/catsByBreed/{breed}")
    CatListWrapper findCatsByBreed(@PathVariable String breed){
        return new CatListWrapper(catService.findCatsByBreed(breed).stream().map(CatModel::new).toList());
    }


    @GetMapping("/catsByColorId/{colorId}")
    CatListWrapper findCatsByColorId(@PathVariable Long colorId){
        return new CatListWrapper(catService.findCatsByColor(colorId).stream().map(CatModel::new).toList());
    }

    @PostMapping("/relations/{firstCatId}/{secondCatId}")
    void createRelations(@PathVariable Long firstCatId, @PathVariable Long secondCatId){
        catService.createRelations(catService.findCatById(firstCatId).orElseThrow(() -> new CatNotFoundException(firstCatId)), catService.findCatById(secondCatId).orElseThrow(() -> new CatNotFoundException(secondCatId)));
    }
}
