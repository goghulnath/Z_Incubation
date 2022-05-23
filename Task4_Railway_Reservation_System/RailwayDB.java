package com.ZohoIncubation.Task4NewDesign;

import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;

enum Credentials{
    user, pwd
}

public class RailwayDB {

    ArrayList<User> user_credentials;
    ArrayList<User> admin_credential;

    Queue<Passenger> waiting_list;
    Map<UserType, Passenger> booked_details;

}
