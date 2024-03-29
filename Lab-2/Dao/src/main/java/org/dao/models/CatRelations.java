package org.dao.models;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Table(name = "cats_friendship")
public class CatRelations {
    @Column(name = "first_cat_id")
    @ManyToMany(mappedBy = "friends")
    private Integer firstCatId;

    @Column(name = "second_cat_id")
    @ManyToMany(mappedBy = "friendsOf")
    private Integer secondCatId;
}
