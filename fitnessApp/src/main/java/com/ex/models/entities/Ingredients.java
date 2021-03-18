package com.ex.models.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "INGREDIENTS")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "INGID")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Ingredients {


    private int ingId;
    private String name;

    private Set<Composite> composites = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "INGID")
    public int getIngId() {
        return ingId;
    }

    public void setIngId(int ingId) {
        this.ingId = ingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "primaryKey.ingredients", cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.EAGER)
    public Set<Composite> getComposites() {
        return composites;
    }

    public void setComposites(Set<Composite> composites) {
        this.composites = composites;
    }

    //    @Id
//    @Column(name="ingID")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int ingredientID;
//
//    @Column(name="NAME")
//    private String name;
//
//    @ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    Set<Composite> composites;
}
