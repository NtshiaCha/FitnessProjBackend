package com.ex.services;

import com.ex.dao.ExerciseDAO;
import com.ex.dao.UserDAO;
import com.ex.models.entities.Exercise;
import com.ex.models.Pyramid.*;
import com.ex.models.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
@Service("workoutService")
@Scope("prototype")
@Transactional(
        isolation = Isolation.REPEATABLE_READ,
        propagation = Propagation.REQUIRED
)
public class WorkoutService {
    ExerciseDAO dao;
    UserService userService;


    //setting up the DAO
    @Autowired
    public void setDao(ExerciseDAO dao){
        this.dao = dao;
    }

    @Autowired
    public void setUserService(UserService serv) {
        this.userService = serv;
    }

    public ArrayList<Exercise> getWorkout(int userID){
        User user = userService.getById(userID);
        ArrayList<Exercise> exercises;

        if (user.isStrength()) {
            exercises = (ArrayList<Exercise>)(dao.getByExerciseType("Strength"));
            return exercises;

        } else if (user.isWeightLoss()) {
            exercises = (ArrayList<Exercise>)(dao.getByExerciseType("Weight Loss"));
            return exercises;
        }
        else if(user.isBalance()) {
            exercises = (ArrayList<Exercise>)(dao.getByExerciseType("Balanced"));
            return exercises;
        }
        return null;
    }

    public String getBenchPyramid(int userID){

        BenchPyramid benchPyramid = new BenchPyramid();
        User user = userService.getById(userID);
        //10s : 55%
        //8s : 65%
        //6s : 75%
        //4s : 85%
        //2s :92.5%

        benchPyramid.setTensBen(5*(Math.round((user.getRepMaxBench() * .55)/5)));
        benchPyramid.setEightsBen(5*(Math.round((user.getRepMaxBench() * .65)/5)));
        benchPyramid.setSixesBen(5*(Math.round((user.getRepMaxSquat() * .75)/5)));
        benchPyramid.setFoursBen(5*(Math.round((user.getRepMaxSquat() * .85)/5)));
        benchPyramid.setTwosBen(5*(Math.round((user.getRepMaxSquat() * .92)/5)));
        return benchPyramid.toString();
    }

    public String getSquatPyramid(int userID){
        Exercise exercise;
        SquatPyramid squatPyramid = new SquatPyramid();
        User user = userService.getById(userID);
        double userBenchPyramidValue = user.getRepMaxBench();
        //10s : 45%
        //8s : 60%
        //6s : 80%
        //4s : 88%
        //2s :92%
//        (5*(Math.round(x/5))); this rounds to nearest multiple of 5. Ex: 4.35 = 4
        //
        squatPyramid.setTensSquat(5*(Math.round((user.getRepMaxSquat() * .45)/5)));
        squatPyramid.setEightsSquat(5*(Math.round((user.getRepMaxSquat() * .60)/5)));
        squatPyramid.setSixesSquat(5*(Math.round((user.getRepMaxSquat() * .80)/5)));
        squatPyramid.setFoursSquat(5*(Math.round((user.getRepMaxSquat() * .88)/5)));
        squatPyramid.setTwosSquat(5*(Math.round((user.getRepMaxSquat() * .92)/5)));
        return squatPyramid.toString();
    }
    //
    // Take in User's bodyweight and grab what exerciseType they are working with.
    // Then generate the number of calories burned for those routines using the bodyweight field. (Most exercises will burn around the
    // same calories.)
    // For Balanced and Cardio I will check the user's preferences and add those calories burned to the total that the rest
    //of the workout should burn on average.

    //This method returns about
    public double getCalCount(int userID) {
        User user = userService.getById(userID);

        if (user.isStrength()) {
            double userWeight = user.getWeight();
            double strDivideNum = 2.56;
            //Take userweight divide by formula for calculating calories for exercise
            double result = (Math.round(userWeight / strDivideNum));
            //4 exercises each day. Roughly same tempo for all strength stuff.
            double strengthWeekKCal = result * 4 * 4;
            return strengthWeekKCal;

        } else if (user.isWeightLoss()) {
            double userWeight = user.getWeight();
            double wlossDivideNum = 1.89;
            //Take userweight divide by formula for calculating calories for exercise
            double dailyResult = (Math.round(userWeight / wlossDivideNum));
            double weeklyResultBeforeCardio = dailyResult * 4;
            //if statement that handles what the User's preferred cardio workout type is.

            if (user.isWalk()) {
                //This counts as the cardio session inside of their weight loss workout days.
                double tenMinCardioDayKcal = 51;
                //This counts as one of their workout days.
                double fortyMinCardioDayKcal = 204;
                //Ten Minute cardio for 4 days of workouts
                double weeklyResultWithWalk = (weeklyResultBeforeCardio + (tenMinCardioDayKcal * 4) + fortyMinCardioDayKcal);
                return Math.round(weeklyResultWithWalk);
            } else if (user.isRun()) {
                //This counts as the cardio session inside of their weight loss workout days.
                double tenMinCardioDayKcal = 116;
                //This counts as one of their workout days.
                double fortyMinCardioDayKcal = 464;
                //Ten Minute cardio for 4 days of workouts
                double weeklyResultWithRun = (weeklyResultBeforeCardio + (tenMinCardioDayKcal * 4) + fortyMinCardioDayKcal);
                return Math.round(weeklyResultWithRun);
            } else if (user.isSwim()) {
                //This counts as the cardio session inside of their weight loss workout days.
                double tenMinCardioDayKcal = 90;
                //This counts as one of their workout days.
                double fortyMinCardioDayKcal = 360;
                //Ten Minute cardio for 4 days of workouts
                double weeklyResultWithSwim = (weeklyResultBeforeCardio + (tenMinCardioDayKcal * 4) + fortyMinCardioDayKcal);
                return Math.round(weeklyResultWithSwim);
            } else if (user.isBike()) {
                double tenMinCardioDayKcal = 90;
                //This counts as one of their workout days.
                double fortyMinCardioDayKcal = 360;
                //Ten Minute cardio for 4 days of workouts
                double weeklyResultWithBike = (weeklyResultBeforeCardio + (tenMinCardioDayKcal * 4) + fortyMinCardioDayKcal);
                return Math.round(weeklyResultWithBike);
            }

        } else if (userService.getById(userID).isBalance()) {
            //User will have 2 10 minute cardio sessions with their normal workout
            //User will have a 40 minute cardio day.

            double userWeight = user.getWeight();
            double wlossDivideNum = 1.89;
            double strengthDivideNum = 2.56;
            //Take userweight divide by formula for calculating calories for exercise
            //Two days will have cardio stats for kcal
            //Two days will have Strength stats for kcal
            double dailyResultWLoss = (userWeight / wlossDivideNum);
            double weeklyResultBeforeCardioWL = dailyResultWLoss * 2;
            double dailyResultStr = (userWeight / strengthDivideNum);
            double weeklyResultBeforeCardioStr = dailyResultStr * 2;
            double weeklyResult = weeklyResultBeforeCardioStr + weeklyResultBeforeCardioWL;

            if (user.isWalk()) {
                //This counts as the cardio session inside of their weight loss workout days.
                double tenMinCardioDayKcal = 51;
                //This counts as one of their workout days.
                double fortyMinCardioDayKcal = 204;
                //Ten Minute cardio for 4 days of workouts
                double weeklyResultWithWalk = (weeklyResult + (tenMinCardioDayKcal * 2) + fortyMinCardioDayKcal);
                return Math.round(weeklyResultWithWalk);
            } else if (user.isRun()) {
                //This counts as the cardio session inside of their weight loss workout days.
                double tenMinCardioDayKcal = 116;
                //This counts as one of their workout days.
                double fortyMinCardioDayKcal = 464;
                //Ten Minute cardio for 4 days of workouts
                double weeklyResultWithRun = (weeklyResult + (tenMinCardioDayKcal * 2) + fortyMinCardioDayKcal);
                return Math.round(weeklyResultWithRun);
            } else if (user.isSwim()) {
                //This counts as the cardio session inside of their weight loss workout days.
                double tenMinCardioDayKcal = 90;
                //This counts as one of their workout days.
                double fortyMinCardioDayKcal = 360;
                //Ten Minute cardio for 4 days of workouts
                double weeklyResultWithSwim = (weeklyResult + (tenMinCardioDayKcal * 2) + fortyMinCardioDayKcal);
                return Math.round(weeklyResultWithSwim);
            } else if (user.isBike()) {
                double tenMinCardioDayKcal = 90;
                //This counts as one of their workout days.
                double fortyMinCardioDayKcal = 360;
                //Ten Minute cardio for 4 days of workouts
                double weeklyResultWithBike = (weeklyResult + (tenMinCardioDayKcal * 2) + fortyMinCardioDayKcal);
                return Math.round(weeklyResultWithBike);
            }
            return weeklyResult;
        }
        return 0;
    }



    public Object getById(int id) {
        return null;
    }


    public List getAll() {
        return dao.getAll();
    }


    public Object save(Object obj) {
        return null;
    }


    public void delete(Object obj) {
    }


    public void update(Object obj) {
    }
}

