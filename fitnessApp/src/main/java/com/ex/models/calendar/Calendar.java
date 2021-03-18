package com.ex.models.calendar;

import com.ex.models.entities.Exercise;
import com.ex.models.entities.Recipes;
import com.ex.models.users.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Getter
@Setter
@NoArgsConstructor
@Component
public class Calendar {
    private User user;
    private ArrayList<Recipes> recipesArr;
    private ArrayList<Exercise> exerciseArr;
    private String benchPyramid;
    private String squatPyramid;
}
