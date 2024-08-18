package org.lab.externalInterfacesMicroservice.RestControllers;

import lombok.AllArgsConstructor;
import org.lab.dao.ListWrappers.CatListWrapper;
import org.lab.dao.responseModels.CatModel;
import org.lab.dao.responseModels.CatSaveModel;
import org.lab.dao.models.UserRole;
import org.lab.externalInterfacesMicroservice.Publishers.CatProducer;
import org.lab.externalInterfacesMicroservice.Security.UserDetailsConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.lab.externalInterfacesMicroservice.authentication.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@AllArgsConstructor
public class CatController {
    private final IAuthenticationFacade authenticationFacade;
    private final CatProducer catProducer;

    public String getUserRole() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getAuthorities().stream().toList().get(0).getAuthority();
    }

    Long getOwnerId() {
        Authentication authentication = authenticationFacade.getAuthentication();
        UserDetailsConfig user = (UserDetailsConfig) authentication.getPrincipal();
        return user.getOwnerId();
    }

    @GetMapping("/cats")
    ResponseEntity<List<CatModel>> findAll() {
        CatListWrapper responseEntity =
                new RestTemplate().getForObject(
                        "http://localhost:8081/cats", CatListWrapper.class);
        if (getUserRole().equals(UserRole.User.toString()))
            return new ResponseEntity<>(responseEntity.getCats().stream().filter(cat -> cat.getOwnerId().equals(getOwnerId())).toList(), HttpStatus.OK);
        return new ResponseEntity<>(responseEntity.getCats(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/postCat")
    void saveCat(@RequestBody CatSaveModel catToPost) {
        catProducer.sendCat(catToPost);
    }

    @GetMapping("/cat/{id}")
    ResponseEntity<CatModel> findCat(@PathVariable Long id) {
        CatModel responseEntity =
                new RestTemplate().getForObject(
                        "http://localhost:8081/cat/{id}", CatModel.class, id);
        return new ResponseEntity<>(responseEntity, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/putCats")
    void replaceCat(@RequestBody CatSaveModel catToPersist) {
        catProducer.sendCat(catToPersist);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/deleteCat/{id}")
    void deleteCat(@PathVariable Long id) {
        new RestTemplate().delete("http://localhost:8081/deleteCat/{id})", id);
    }

    @GetMapping("/catsByBreed/{breed}")
    ResponseEntity<List<CatModel>> findCatsByBreed(@PathVariable String breed) {
        CatListWrapper responseEntity =
                new RestTemplate().getForObject(
                        "http://localhost:8081/catsByBreed/{breed}", CatListWrapper.class, breed);
        if (getUserRole().equals(UserRole.Admin.toString()))
            return new ResponseEntity<>(responseEntity.getCats(), HttpStatus.OK);
        return new ResponseEntity<>(responseEntity.getCats().stream().filter(cat -> cat.getOwnerId().equals(getOwnerId())).toList(), HttpStatus.OK);
    }

    @GetMapping("/catsByColorId/{colorId}")
    ResponseEntity<List<CatModel>> findCatsByColorId(@PathVariable Long colorId) {
        CatListWrapper responseEntity =
                new RestTemplate().getForObject(
                        "http://localhost:8081/cats", CatListWrapper.class);
        if (getUserRole().equals(UserRole.Admin.toString()))
            return new ResponseEntity<>(responseEntity.getCats(), HttpStatus.OK);
        return new ResponseEntity<>(responseEntity.getCats().stream().filter(cat -> cat.getOwnerId().equals(getOwnerId())).toList(), HttpStatus.OK);
    }
}
