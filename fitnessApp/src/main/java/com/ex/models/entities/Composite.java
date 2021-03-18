package com.ex.models.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "COMPOSITE")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@CompositeId")
@NoArgsConstructor
@ToString
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.recipes",
            joinColumns = @JoinColumn(name = "RecipID")),
        @AssociationOverride(name = "primaryKey.ingredients",
            joinColumns = @JoinColumn(name = "IngID"))
})
public class Composite implements Serializable {

    private CompositeId primaryKey = new CompositeId();

    @Column(name = "AMOUNTFLOAT")
    private float amountFloat;

    @Column(name = "AMOUNT")
    private String amountString;

    @EmbeddedId
    public CompositeId getPrimaryKey(){
        return primaryKey;
    }

    public void setPrimaryKey(CompositeId primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Transient
    public Recipes getRecipes(){
        return getPrimaryKey().getRecipes();
    }

    public void setRecipes(Recipes recipes)
    {
        getPrimaryKey().setRecipes(recipes);
    }

    @Transient
    public Ingredients getIngredients(){
        return getPrimaryKey().getIngredients();
    }

    public void setIngredients(Ingredients ingredients){
        getPrimaryKey().setIngredients(ingredients);
    }



}
