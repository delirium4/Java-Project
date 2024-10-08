package org.lab.dao.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table (name = "owners")
public class Owner {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "birth_date")
    private Date birthDate;
    @Column(name = "owner_name")
    private String ownerName;
    @OneToMany(mappedBy = "owner",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Cat> cats;
    public Owner(Date birthDate, String ownerName){
        this.birthDate = birthDate;
        cats = new ArrayList<Cat>();
        this.ownerName = ownerName;
    }

    public void addCat(Cat cat){
        cats.add(cat);
        cat.setOwner(this);
    }
}
