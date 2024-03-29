package org.service;

import lombok.Getter;
import lombok.Setter;
import org.dao.models.Owner;
import org.dao.OwnerDao;

@Getter
@Setter
public class OwnerService {
    private OwnerDao ownerDao;
    public OwnerService(OwnerDao ownerdao){
        this.ownerDao = ownerdao;
    }
    public Owner findOwner(Integer id){
        return ownerDao.findOwnerById(id);
    }

    public void saveOwner(Owner owner){
        ownerDao.save(owner);
    }

    public void updateOwner(){
        ownerDao.update();
    }

}
