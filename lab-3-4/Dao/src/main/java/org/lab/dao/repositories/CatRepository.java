package org.lab.dao.repositories;

import org.lab.dao.models.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long>{
    List<Cat> findCatsByBreed(String breed);
    List<Cat> findCatsByColorId(Long colorId);
    List<Cat> findCatsByOwner_Id(Long ownerId);

}
