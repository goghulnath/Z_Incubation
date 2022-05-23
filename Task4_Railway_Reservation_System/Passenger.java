package com.ZohoIncubation.Task4NewDesign;

enum Gender{
    M, F
}

enum BirthPreference{
    UB, MB, LB
}


public class Passenger {
    String name;
    int age;
    Gender gender;
    BirthPreference birth_preference;
    boolean special_care;
    Ticket ticket;
}
