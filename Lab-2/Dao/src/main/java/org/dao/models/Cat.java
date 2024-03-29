package org.dao.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table (name = "cats")
public class Cat {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name = "birth_date")
    private Date birthDate;
    @Column(name = "color_id")
    private Integer colorId;
    private String breed;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private Owner owner;
    @ManyToMany(fetch = FetchType.EAGER)

    @JoinTable(
            name = "cats_friendship",
            joinColumns = @JoinColumn(name = "first_cat_id"),
            inverseJoinColumns = @JoinColumn(name = "second_cat_id")
    )
    private List<Cat> friends;
    @ManyToMany(mappedBy = "friends")
    private List<Cat> friendsOf;
    public Cat(String name, Date birth_date, Colors color, String breed){
        this.name = name;
        this.birthDate = birth_date;
        colorId = color.ordinal();
        this.breed = breed;
        friends = new ArrayList<Cat>();
        friendsOf = new ArrayList<Cat>();
    }

    public void addFriend(Cat cat){
        friends.add(cat);
        cat.friendsOf.add(this);
    }
}
