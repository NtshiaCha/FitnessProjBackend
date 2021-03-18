package com.ex.services;

import com.ex.dao.RecipeDAO;
import com.ex.models.entities.Recipes;
import com.ex.models.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service("recipeService")
@Scope("prototype")
@Transactional(
        isolation = Isolation.REPEATABLE_READ,
        propagation = Propagation.REQUIRED
)
public class RecipeService {
    RecipeDAO dao;
    WorkoutService workoutService;
    UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @Autowired
    public void setDao(RecipeDAO dao){
        this.dao=dao;
    }

    @Autowired
    public void setWorkoutService(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    /*
REMEMBER TO WRITE A METHOD HERE FOR EACH METHOD IN THE ExerciseDAO THAT CALLS THE CORRESPONDING METHOD IN THE ExerciseDAO. SEE UserService FOR EXAMPLE
 */

    public List<Recipes> getMealPlan (int userID) {
        double totatlCal = calcCalories(userID);
        ArrayList<Recipes> recipe = new ArrayList<>();

        while (totatlCal > 0)
        {
            Recipes recipes = (Recipes) dao.getById((int)(Math.random() * 45));
            
            recipe.add(recipes);
            
            totatlCal = totatlCal - recipes.getCalorie();
        }
        return recipe;
    }

    public int calcCalories (int userId){

//        BMR = 655 + (4.35 x WEIGHT_IN_POUNDS) + (4.7 x HEIGHT_IN_INCH) - (4.7 x AGE_IN_YEAR) - female
//        BMR = 66 + (6.23 x WEIGHT_IN_POUNDS) + (12.7 x HEIGHT_IN_INCH) - (6.8 x AGE_IN_YEAR) - male.

        double workOutCal = workoutService.getCalCount(userId);

        User user = userService.getById(userId);
        double BMR;
        double weight = user.getWeight();
        double height = user.getHeight();
        int age = user.getAge();
        String gender = user.getGender();

        if (gender.equals("Male")){
            BMR = 7 * (66 + (6.23 * weight) + (12.7 * height) - (6.8 * age));
        }
        else {
            BMR = 7 * (655 + (4.35 * weight) + (4.7 * height) - (4.7 * age));
        }

        if(user.isStrength())
        {
            BMR = BMR + workOutCal + (200 * 7);
        }

        if(user.isWeightLoss())
        {
            BMR = BMR + workOutCal - (500 * 7);
        }

        return (int) BMR;
    }

    public Recipes getRecipeById(int recipeid){
        return (Recipes)dao.getById(recipeid);
    }

    public List<Recipes> getAll(){ return dao.getAll();}

}
