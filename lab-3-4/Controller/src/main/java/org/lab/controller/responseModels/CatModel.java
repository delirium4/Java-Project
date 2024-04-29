package org.lab.controller.responseModels;

import lombok.Getter;
import org.lab.dao.models.Cat;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CatModel {
    private final String name;
    private final Date birthDate;
    private final String breed;
    private final String color;
    private final String ownerName;
    private final List<CatModel> friends;
    public CatModel(Cat cat){
        name = cat.getName();
        birthDate = cat.getBirthDate();
        breed = cat.getBreed();
        color = cat.getColorId().toString();
        ownerName = cat.getOwner().getOwnerName();
        friends = cat.getFriends().stream().map(CatModel::new).collect(Collectors.toList());
    }

}
