package org.lab.dao.responseModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.lab.dao.models.Owner;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@Getter
public class OwnerSaveModel {
    private final Date birthDate;
    private final String ownerName;
    private final List<CatSaveModel> cats;

    public Owner castOwnerSaveModelToOwner(){
        Owner owner = new Owner();
        owner.setOwnerName(this.ownerName);
        owner.setBirthDate(this.getBirthDate());
        owner.setCats(this.getCats().stream().map(CatSaveModel::castCatSaveModelToCat).toList());
        return owner;
    }
}
