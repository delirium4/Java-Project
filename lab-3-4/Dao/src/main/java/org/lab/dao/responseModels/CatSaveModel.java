package org.lab.dao.responseModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.lab.dao.models.Cat;
import org.lab.dao.models.Owner;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@Getter
public class CatSaveModel {
    private final String name;
    private final Date birthDate;
    private final String breed;
    private final Long colorId;
    private final Owner owner;
    private final List<Cat> friends;
    private final List<Cat> friendsOf;

    public Cat castCatSaveModelToCat(){
        Cat cat = new Cat();
        cat.setName(this.getName());
        cat.setBreed(this.getBreed());
        cat.setOwner(this.getOwner());
        cat.setFriends(this.getFriends());
        cat.setFriendsOf(this.getFriendsOf());
        cat.setColorId(this.getColorId());
        cat.setBirthDate(this.getBirthDate());
        return cat;
    }
}
