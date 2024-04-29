package org.lab.controller.RestControllers;

import lombok.AllArgsConstructor;
import org.lab.controller.Exceptions.CatNotFoundException;
import org.lab.controller.responseModels.CatModel;
import org.lab.dao.models.Cat;
import org.lab.dao.models.UserRole;
import org.lab.service.CatService;
import org.lab.service.configs.UserDetailsConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.lab.controller.authentication.*;

import java.util.List;

@RestController
@AllArgsConstructor
@ComponentScan(basePackages = "org.lab.service")
public class CatController {
    private final CatService catService;

    private final IAuthenticationFacade authenticationFacade;

    public String getUserRole(){
        Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getAuthorities().stream().toList().get(0).getAuthority();
    }

    Long getOwnerId(){
        Authentication authentication = authenticationFacade.getAuthentication();
        UserDetailsConfig user = (UserDetailsConfig) authentication.getPrincipal();
        return user.getOwnerId();
    }

    @GetMapping("/cats")
    ResponseEntity<List<CatModel>> findAll(){
        if(getUserRole().equals(UserRole.User.toString()))
            return new ResponseEntity<>(catService.findCatByOwnerId(getOwnerId()).stream().map(CatModel::new).toList(), HttpStatus.OK);
        return new ResponseEntity<>(catService.findAll().stream().map(CatModel::new).toList(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/cats")
    ResponseEntity<CatModel> saveCat(@RequestBody Cat newCat){
        return new ResponseEntity<>(new CatModel(catService.saveCat(newCat)), HttpStatus.OK);
    }

    @GetMapping("/cats/{id}")
    ResponseEntity<CatModel> findCat(@PathVariable Long id){
        if(getUserRole().equals(UserRole.Admin.toString()))
            return new ResponseEntity<>(new CatModel(catService.findCatById(id).orElseThrow(() -> new CatNotFoundException(id))), HttpStatus.OK);
        return new ResponseEntity<>(new CatModel(catService.findCatById(id).filter(cat -> cat.getOwner().getId().equals(getOwnerId())).orElseThrow(() -> new CatNotFoundException(id))), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/cats/{id}")
    ResponseEntity<CatModel> replaceCat(@RequestBody Cat newCat, @PathVariable Long id){
        return new ResponseEntity<>(new CatModel(catService.findCatById(id).map(cat -> {
            cat.setName(newCat.getName());
            cat.setBirthDate(newCat.getBirthDate());
            cat.setBreed(newCat.getBreed());
            cat.setColorId(newCat.getColorId());
            cat.setOwner(newCat.getOwner());
            return catService.saveCat(cat);
        }).orElseGet(() -> {
            newCat.setId(id);
            return catService.saveCat(newCat);
        })), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/cats/{id}")
    void deleteCat(@PathVariable Long id){
        catService.deleteCatById(id);
    }

    @GetMapping("/catsByBreed/{breed}")
    ResponseEntity<List<CatModel>> findCatsByBreed(@PathVariable String breed){
        if(getUserRole().equals(UserRole.Admin.toString()))
            return new ResponseEntity<>(catService.findCatsByBreed(breed).stream().map(CatModel::new).toList(), HttpStatus.OK);
        return new ResponseEntity<>(catService.findCatsByBreed(breed).stream().filter(cat -> cat.getOwner().getId().equals(getOwnerId())).map(CatModel::new).toList(), HttpStatus.OK);
    }

    @GetMapping("/catsByColorId/{colorId}")
    ResponseEntity<List<CatModel>> findCatsByColorId(@PathVariable Long colorId){
        if(getUserRole().equals(UserRole.Admin.toString()))
            return new ResponseEntity<>(catService.findCatsByColor(colorId).stream().map(CatModel::new).toList(), HttpStatus.OK);
        return new ResponseEntity<>(catService.findCatsByColor(colorId).stream().filter(cat -> cat.getOwner().getId().equals(getOwnerId())).map(CatModel::new).toList(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/relations/{firstCatId}/{secondCatId}")
    void createRelations(@PathVariable Long firstCatId, @PathVariable Long secondCatId){
        catService.createRelations(catService.findCatById(firstCatId).orElseThrow(() -> new CatNotFoundException(firstCatId)), catService.findCatById(secondCatId).orElseThrow(() -> new CatNotFoundException(secondCatId)));
    }

}
