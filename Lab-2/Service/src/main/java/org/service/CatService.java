package org.service;

import org.dao.models.Cat;
import org.dao.CatDao;
import org.dao.models.CatRelations;
import org.dao.CatRelationsDao;


public class CatService {
    private CatDao catDao;
    private CatRelationsDao catRelationsDao;
    public CatService(CatDao catDao, CatRelationsDao catRelationsDao){
        this.catDao = catDao;
        this.catRelationsDao = catRelationsDao;
    }
    public Cat findCatById(Integer id){
        return catDao.findCatById(id);
    }

    public void saveCat(Cat cat){
        catDao.save(cat);
    }

    public void updateCat(){
        catDao.update();
    }

    public void createRelations(Cat firstCat, Cat secondCat){
        firstCat.addFriend(secondCat);
        catRelationsDao.save(new CatRelations(firstCat.getId(), secondCat.getId()));
    }
}
