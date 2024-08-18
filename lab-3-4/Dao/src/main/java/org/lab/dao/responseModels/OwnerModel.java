package org.lab.dao.responseModels;

import lombok.Getter;
import org.lab.dao.models.Owner;

import java.sql.Date;
import java.util.List;

@Getter
public class OwnerModel {
    private final Date birthDate;
    private final String ownerName;
    private final List<CatModel> cats;

    public OwnerModel(Owner owner){
        birthDate = owner.getBirthDate();
        ownerName = owner.getOwnerName();
        cats = owner.getCats().stream().map(CatModel::new).toList();
    }

    public OwnerModel(){
        birthDate = null;
        ownerName = null;
        cats = null;
    }
}
