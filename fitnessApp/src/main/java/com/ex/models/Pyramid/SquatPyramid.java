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
public class SquatPyramid {
    private double tensSquat;
    private double eightsSquat;
    private double sixesSquat;
    private double foursSquat;
    private double twosSquat;

    @Override
    public String toString() {
        return "10s " + tensSquat + "8s " + eightsSquat + "6s " + sixesSquat + "4s " + foursSquat + "2s";
    }
}