package com.ex.models.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "STATINFO")
@NoArgsConstructor
@ToString

public class StatTrackingObj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statId;
    @Column(name = "userID")
    private int userID;
    @Column( name = "CALINTAKE")
    private int calIntake;
    @Column( name = "WEIGHT")
    private int weight;
    @Column( name = "REPMAXBENCH")
    private String repMaxBench;
    @Column( name = "REPMAXSQUAT")
    private String repMaxSquat;
    @Column( name = "SITUP")
    private String sitUp;
    @Column( name = "PULLUP")
    private String pullUp;
    @Column( name = "MILES")
    private String miles;

}
