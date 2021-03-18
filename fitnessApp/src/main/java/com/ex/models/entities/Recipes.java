package com.ex.models.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="RECIPES")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@recipID")
//@Getter
//@Setter
@NoArgsConstructor
@ToString
public class Recipes {

    private String name;
    private int calorie;
    private boolean isbfast;
    private boolean islunch;
    private boolean isdinner;
    private int recipID;
    private String directions;

    private Set<Composite> composites = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RECIPID")
    public int getRecipID() {
        return recipID;
    }

    public void setRecipID(int recipID) {
        this.recipID = recipID;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public boolean isIsbfast() {
        return isbfast;
    }

    public void setIsbfast(boolean isbfast) {
        this.isbfast = isbfast;
    }

    public boolean isIslunch() {
        return islunch;
    }

    public void setIslunch(boolean islunch) {
        this.islunch = islunch;
    }

    public boolean isIsdinner() {
        return isdinner;
    }

    public void setIsdinner(boolean isdinner) {
        this.isdinner = isdinner;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    @OneToMany(mappedBy = "primaryKey.recipes",
            cascade = {CascadeType.PERSIST,CascadeType.MERGE},
            fetch = FetchType.EAGER)
    public Set<Composite> getComposites() {
        return composites;
    }

    public void setComposites(Set<Composite> composites) {
        this.composites = composites;
    }

    //    @Column(name="NAME")
//    private String name;
//
//    @Column(name="CALORIE")
//    private int calorie;
//
//    @Column(name="ISBFAST")
//    private boolean isBfast;
//
//
//    @Column(name="ISLUNCH")
//    private boolean isLunch;
//
//    @Column(name="ISDINNER")
//    private boolean isDinner;
//
//    Set<Composite> composites;
}
