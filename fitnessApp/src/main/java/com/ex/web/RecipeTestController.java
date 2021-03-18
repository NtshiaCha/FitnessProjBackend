package com.ex.web;

import com.ex.models.entities.Recipes;
import com.ex.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/recipes")
public class RecipeTestController {
    RecipeService service;
    @Autowired
    public void setService(RecipeService service){
        this.service = service;
    }

    @CrossOrigin
    @PostMapping(path="getRecipe",
            produces={MediaType.APPLICATION_JSON_VALUE})
    public Recipes getRecipeByID( Integer recipid) {
        System.out.println("hit the mapping");
        System.out.println(recipid);
        return service.getRecipeById(recipid);

    }
    @CrossOrigin
    @PostMapping(path = "getAllRecipes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Recipes> getAll(){

        return service.getAll();
    }
    @CrossOrigin
    @PostMapping(path = "userCal",
            produces ={MediaType.APPLICATION_JSON_VALUE})
    public int calCalories(Integer userid) {
//        System.out.println("hit the mapping");
        System.out.println(userid);
        return service.calcCalories(userid);
    }
    @CrossOrigin
    @PostMapping(path = "mealPlan",
            produces ={MediaType.APPLICATION_JSON_VALUE})
    public List<Recipes> userMealPlan(@RequestBody int userId) {
        return service.getMealPlan(userId);
    }
}
