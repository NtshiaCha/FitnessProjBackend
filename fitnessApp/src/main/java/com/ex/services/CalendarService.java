package com.ex.services;

import com.ex.models.calendar.Calendar;
import com.ex.models.calendar.CalendarDay;
import com.ex.models.entities.Exercise;
import com.ex.models.entities.Recipes;
import com.ex.models.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service("calendarService")
@Scope("prototype")
@Transactional(
        isolation = Isolation.REPEATABLE_READ,
        propagation = Propagation.REQUIRED
)

public class CalendarService {
    UserService userService;
    WorkoutService workoutService;
    RecipeService recipeService;
    Calendar calendar;

    @Autowired
    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setWorkoutService(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @Autowired
    public void setRecipeService(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    public ArrayList<CalendarDay> buildCalendar(String userName) {
        ArrayList<CalendarDay> dayArray = new ArrayList<>();
        calendar.setUser(userService.getByUsername(userName));
        User user = calendar.getUser();
        calendar.setExerciseArr(workoutService.getWorkout(user.getUserId()));
        calendar.setRecipesArr((ArrayList) recipeService.getMealPlan(user.getUserId()));


        if (calendar.getUser().isStrength()) {
            calendar.setBenchPyramid(workoutService.getBenchPyramid(user.getUserId()));
            calendar.setSquatPyramid(workoutService.getSquatPyramid(user.getUserId()));
        }
        CalendarDay sunday = new CalendarDay();
        ArrayList<Recipes> sundayRecipes = new ArrayList<>();
        sundayRecipes.add(calendar.getRecipesArr().get(0));
        sundayRecipes.add(calendar.getRecipesArr().get(1));
        sundayRecipes.add(calendar.getRecipesArr().get(2));
        sunday.setRecipes(sundayRecipes);
        sunday.setExercise(sortBy("Arms", calendar.getExerciseArr()));
        dayArray.add(sunday);

        CalendarDay monday = new CalendarDay();
        ArrayList<Recipes> mondayRecipes = new ArrayList<>();
        mondayRecipes.add(calendar.getRecipesArr().get(3));
        mondayRecipes.add(calendar.getRecipesArr().get(4));
        mondayRecipes.add(calendar.getRecipesArr().get(5));
        monday.setRecipes(mondayRecipes);
        monday.setExercise(sortBy("Legs", calendar.getExerciseArr()));
        dayArray.add(monday);

        CalendarDay tuesday = new CalendarDay();
        ArrayList<Recipes> tuesdayRecipes = new ArrayList<>();
        tuesdayRecipes.add(calendar.getRecipesArr().get(6));
        tuesdayRecipes.add(calendar.getRecipesArr().get(7));
        tuesdayRecipes.add(calendar.getRecipesArr().get(8));
        tuesday.setRecipes(tuesdayRecipes);
        tuesday.setExercise(sortBy("Chest", calendar.getExerciseArr()));
        dayArray.add(tuesday);

        CalendarDay wednesday = new CalendarDay();
        ArrayList<Recipes> wednesdayRecipes = new ArrayList<>();
        wednesdayRecipes.add(calendar.getRecipesArr().get(9));
        wednesdayRecipes.add(calendar.getRecipesArr().get(10));
        wednesdayRecipes.add(calendar.getRecipesArr().get(11));
        wednesday.setRecipes(wednesdayRecipes);
        wednesday.setExercise(sortBy("Chest", calendar.getExerciseArr()));
        dayArray.add(wednesday);

        CalendarDay thursday = new CalendarDay();
        ArrayList<Recipes> thursdayRecipes = new ArrayList<>();
        thursdayRecipes.add(calendar.getRecipesArr().get(12));
        thursdayRecipes.add(calendar.getRecipesArr().get(13));
        thursdayRecipes.add(calendar.getRecipesArr().get(14));
        thursday.setRecipes(thursdayRecipes);
        thursday.setExercise(sortBy("Chest", calendar.getExerciseArr()));
        dayArray.add(thursday);

        CalendarDay friday = new CalendarDay();
        ArrayList<Recipes> fridayRecipes = new ArrayList<>();
        fridayRecipes.add(calendar.getRecipesArr().get(15));
        fridayRecipes.add(calendar.getRecipesArr().get(16));
        fridayRecipes.add(calendar.getRecipesArr().get(17));
        friday.setRecipes(fridayRecipes);
        friday.setExercise(sortBy("Chest", calendar.getExerciseArr()));
        dayArray.add(friday);

        CalendarDay saturday = new CalendarDay();
        ArrayList<Recipes> saturdayRecipes = new ArrayList<>();
        saturdayRecipes.add(calendar.getRecipesArr().get(18));
        saturdayRecipes.add(calendar.getRecipesArr().get(19));
        saturdayRecipes.add(calendar.getRecipesArr().get(20));
        saturday.setRecipes(saturdayRecipes);
        saturday.setExercise(sortBy("Chest", calendar.getExerciseArr()));
        dayArray.add(saturday);

        return dayArray;
    }

    public ArrayList<Exercise> sortBy(String criteria, ArrayList<Exercise> exercises) {
        ArrayList<Exercise> result = new ArrayList<>();
        for (Exercise e : exercises) {
            if (e.getExerciseMuscleGroup().equals(criteria)) {
                result.add(e);
            }
        }
        return result;
    }
}