package org.lab.catMicroservice.PersistRequestHandler;

import org.lab.catMicroservice.Service.CatService;
import org.lab.dao.models.Cat;
import org.lab.dao.responseModels.CatSaveModel;

public class PersistHandler{
    private final CatService catService;

    public PersistHandler(CatService catService) {
        this.catService = catService;
    }

    public void Execute(CatSaveModel catSaveModel) {
        catService.save(catSaveModel.castCatSaveModelToCat());
    }
}
