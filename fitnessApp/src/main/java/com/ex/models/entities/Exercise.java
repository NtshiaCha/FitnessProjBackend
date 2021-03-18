package com.ex.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="exercises")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int exerciseID;
    @Column(name="exercisename")
    private String exerciseName;
    @Column(name="exercisetype")
    private String exerciseType;
    @Column(name="exercisemusclegroup")
    private String exerciseMuscleGroup;
    @Column(name="usespyramid")
    private boolean usesPyramid;
    @Column(name="exercisedescription")
    private String exerciseDescription;
    @Column (name="workoutset")
    private String workoutset;
}
