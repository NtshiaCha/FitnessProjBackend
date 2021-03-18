package com.ex.models.calendar;

import com.ex.models.entities.Exercise;
import com.ex.models.entities.Recipes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@Component
public class CalendarDay {
    ArrayList<Exercise> exercise;
    ArrayList<Recipes> recipes;
}
