package org.lab.ownerMicroservice.PersistRequest;

import org.lab.dao.models.Owner;
import org.lab.ownerMicroservice.Service.OwnerService;

import java.util.Optional;

public class PersistHandler {
    private final OwnerService ownerService;

    public PersistHandler(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    public void Execute(Owner owner) {
        ownerService.saveOwner(owner);
    }
}
