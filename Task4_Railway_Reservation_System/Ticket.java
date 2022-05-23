package com.ZohoIncubation.Task4NewDesign;

import java.util.Map;

enum Berth{
    berth, col
}

public class Ticket {
    String ticket_id;
    Coach coach_position;
    Map<Berth, Integer> assigned_berth;
}
