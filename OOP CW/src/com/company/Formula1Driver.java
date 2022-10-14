package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Formula1Driver extends Driver {
    int startDate = 1;
    int startMonth = 11;
    int championshipYear = 2021;

    HashMap<Integer, String> results = new HashMap<Integer, String>();
    ArrayList<Integer> positions = new ArrayList<Integer>();

    public Formula1Driver(
            String driverFName,
            String driverLName,
            String driverCountry,
            String teamName,
            int driverPoints,
            int driverCompletedRaces,
            int driverWins) {

        this.driverPoints = driverPoints;
        this.driverCompletedRaces = driverCompletedRaces;
        this.driverWins = driverWins;
        this.driverFirstname = driverFName;
        this.driverLastname = driverLName;
        this.driverCountry = driverCountry;
        this.driverTeamname = teamName;
    }

    public void changeDate() {
        switch (currentTrack) {
            case 0:
                startDate = 1;
                startMonth = 11;
                championshipYear = 2021;
                break;
            case 1:
                startDate = 2;
                startMonth = 11;
                championshipYear = 2021;
                break;
            case 2:
                startDate = 3;
                startMonth = 11;
                championshipYear = 2021;
                break;
            case 3:
                startDate = 4;
                startMonth = 11;
                championshipYear = 2021;
                break;
            case 4:
                startDate = 5;
                startMonth = 11;
                championshipYear = 2021;
                break;
            case 5:
                startDate = 6;
                startMonth = 11;
                championshipYear = 2021;
                break;
            case 6:
                startDate = 7;
                startMonth = 11;
                championshipYear = 2021;
                break;
            case 7:
                startDate = 8;
                startMonth = 11;
                championshipYear = 2021;
                break;
            case 8:
                startDate = 9;
                startMonth = 11;
                championshipYear = 2021;
                break;
            case 9:
                startDate = 10;
                startMonth = 11;
                championshipYear = 2021;
                break;
        }
    }
    public void sortByPointsDec(){
        if(drivers.isEmpty()){
            System.out.println("There are no drivers!");
        }else {
            //int driverNum = 0;
            Collections.sort(drivers, new Comparator<Driver>() {
                public int compare(Driver driver1, Driver driver2) {
                    return driver1.getDriverpoints() - driver2.getDriverpoints();
                }
            }
            );

        }

    }
}