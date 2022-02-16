package com.example.tripplanner;

import java.util.ArrayList;
import java.util.Set;

public class TripHandler {

    // Data types
    private String arrival;
    private String departure;
    private String leaveDate;
    private String returnDate;
    private ArrayList<String> members;

    public TripHandler(String arrival, String departure, String leaveDate, String returnDate, ArrayList<String> members) {
        this.arrival = arrival;
        this.departure = departure;
        this.leaveDate = leaveDate;
        this.returnDate = returnDate;
        this.members = members;
    }

    // Getters and Setters
    // Arrival
    public String getArrival() {
        return this.arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    // Departure
    public String getDeparture() {
        return this.departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    // LeaveDate
    public String getLeaveDate() {
        return this.leaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }

    // ReturnDate
    public String getReturnDate () {
        return this.returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    // Members
    public ArrayList<String> getMembers() {
        return this.members;
    }

    public void setMembers(ArrayList<String> members) {
        for (int i = 0; i < members.size(); i++) {
            this.members.set(i, members.get(i));
        }
    }
}
