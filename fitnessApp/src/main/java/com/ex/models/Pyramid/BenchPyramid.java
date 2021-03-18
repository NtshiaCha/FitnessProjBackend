package com.ex.models.Pyramid;

import com.ex.models.entities.Exercise;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Getter
@Setter
@NoArgsConstructor
@Component
public class BenchPyramid {
    private double tensBen;
    private double eightsBen;
    private double sixesBen;
    private double foursBen;
    private double twosBen;

    @Override
    public String toString() {
        return "10s " + tensBen + "8s " + eightsBen + "6s " + sixesBen + "4s " + foursBen + "2s";
    }

}