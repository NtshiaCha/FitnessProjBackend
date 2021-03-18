package com.ex.models.entities;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class CompositeId implements Serializable {
    private Recipes recipes;
    private Ingredients ingredients;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Recipes getRecipes() {
        return recipes;
    }

    public void setRecipes(Recipes recipes) {
        this.recipes = recipes;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Ingredients getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
    }
}
