package com.ex.models.users;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "USERINFO")
@NoArgsConstructor
@ToString

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column( name = "FirstName")
    private String firstName;

    @Column( name = "LastName")
    private String lastName;

    @Column( name = "Email")
    private String email;

    @Column( name = "UserName")
    private String userName;

    @Column( name = "PassId")
    private String password;

    @Column( name = "UserWeight")
    private double weight;

    @Column( name = "UserHeight")
    private double height;

    @Column( name = "Sex")
    private String gender;

    @Column( name = "Created")
    private String dateTime;

    @Column(name = "SitUp")
    private double situp;

    @Column(name = "PullUp")
    private double pullup;

    @Column( name = "RepMaxBench")
    private int repMaxBench;

    @Column( name = "RepMaxSquat")
    private int repMaxSquat;

    @Column(name = "Imperial")
    private boolean imperial;

    @Column( name = "UserAge")
    private int age;

    @Column( name = "Strength")
    private boolean strength;

    @Column( name = "WeightLoss")
    private boolean weightLoss;

    @Column( name = "Balance")
    private boolean balance;

    @Column( name = "Bike")
    private boolean bike;

    @Column( name = "Swim")
    private boolean Swim;

    @Column( name = "Run")
    private boolean run;

    @Column( name = "Walk")
    private boolean walk;

}
