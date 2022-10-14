package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

    abstract class Driver{

        public static final int  maxDrivers = 10;
        public int currentTrack = 1;
        public int driverPoints;
        public int driverCompletedRaces;
        public int driverWins;

        public String driverFirstname;
        public String driverLastname;
        public String driverTeamname;
        public String driverCountry;
        public String[] teams = {"Colombo_lions", "Jaffna_Stallions","Batticalo_Tiger","Hatton_Vampire","Galle_Gladiators","Matale_Bears","Trinco_Warriors","Chillaw_Riders","Ampara_Spartans","Negambo_Bolts"};

        public ArrayList<Driver> drivers = new ArrayList<>(maxDrivers);
        public ArrayList<String> f1Teams = new ArrayList<>(Arrays.asList(teams));

       public void ValidatingString (){

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter your name: ");
            String str = sc.next();
            if((!str.equals(null))&&str.matches("^[a-zA-Z]*$"))
                System.out.println("Given string is a proper name.");
            else
                System.out.println("Given string is a proper string is not a proper name.");
        }



        public void setDriverFirstname(String driverFirstname){

            Scanner input = new Scanner(System.in);
            System.out.println(" Please enter Race Drivers first name : ");
            ValidatingString();


        }

        public void setDriverLastname(String driverLastname){
            Scanner input = new Scanner(System.in);
            System.out.println(" Please enter Racers last name : ");
        }

        public void setDriverTeamname(String driverTeamname){
            Scanner input = new Scanner(System.in);
            System.out.println(" Please enter Racers team name : ");
        }


        public void setDriverpoints(int driverPoints){this.driverPoints += driverPoints;}
        public void setDriverCompletedRaces(int driverCompletedRaces){this.driverCompletedRaces += driverCompletedRaces;}
        public void setDriverWins(int driverWins){this.driverWins += driverWins;}

        public int getDriverpoints(){ return driverPoints;}
        public int getDriverCompletedRaces(){ return driverCompletedRaces;}
        public int getDriverWins(){ return driverWins;}

        public String getDriverFirstname(){ return driverFirstname;}
        public String getDriverLastname(){ return driverLastname;}
        public String getDriverTeamname(){ return driverTeamname;}
        public String getDriverCountry(){ return driverCountry;}




    }
