package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Formula1ChampionshipManager extends JFrame implements ChampionshipManager, ActionListener {

    Formula1Driver driver = new Formula1Driver("", "", "", "", 0, 0, 0);
    String[] raceDates = {"01/11/2021", "02/11/2021", "03/11/2021", "04/11/2021", "05/11/2021", "06/11/2021", "07/11/2021", "08/11/2021", "09/11/2021", "10/11/2021"};
    JButton buttonDecOrder, buttonAscOrder, buttonRace, buttonWinsDec, buttonReset, buttonRaceHistory, buttonSubmit;
    JLabel[] showDrivers = new JLabel[10];
    JPanel panel = new JPanel(); //container to store components
    JPanel buttons = new JPanel();
    GridBagConstraints gridEditor = new GridBagConstraints();
    JMenuItem loadData, saveData; // menu bar displayed in frame
    JLabel textOnScreen = new JLabel(); //placing text in a container
    JLabel[] raceResults = new JLabel[10];
    JTextField search = new JTextField(10);
    ArrayList<JLabel> racesHistory = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new Formula1ChampionshipManager();
    }

    Formula1ChampionshipManager() throws IOException {
        this.setLayout(new GridBagLayout());
        // creating a title for the application
        this.setTitle("Formula 1 Simulator");
        // When this button is pressed it will exit
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Making sure with layout size is fixed
        this.setResizable(true);
        // setting the size for the layout
        this.setSize(1920, 1080);

        this.getContentPane().setBackground(Color.white);
        // set favicon icon and change it to fix accordingly

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(1080, 750));
        panel.setMaximumSize(new Dimension(600, 900));
        panel.setBackground(Color.WHITE);
        ImageIcon logo = new ImageIcon(new ImageIcon("src/com/company/Img/logo.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        this.setIconImage(logo.getImage());

        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        buttons.setAlignmentY(Component.RIGHT_ALIGNMENT);
        buttons.setBackground(Color.WHITE);
        this.getContentPane().add(panel);
        menuBar();


        this.getContentPane().add(buttons);
        intro();
        ascOrderBtn();
        decOrderBtn();
        decOrderWinBtn();
        raceHistoryBtn();
        raceBtn();
        resetBtn();
        resetProgress();
        pointsDecOrder();
        searchBar();

        this.setVisible(true);
    }
    //Clearing screen to display other options
    public void clearScreen() {
        textOnScreen.setText("");
        if (showDrivers[0] != null) {
            for (int i = 0; i < showDrivers.length; i++) {
                showDrivers[i].setText("");
            }
        }
        for (int i = 0; i < racesHistory.size(); i++) {
            racesHistory.get(i).setText("");
        }

        if (raceResults[0] != null) {
            for (int i = 0; i < raceResults.length; i++) {
                raceResults[i].setText("");
            }
        }
        this.add(textOnScreen);
    }
    // ascending order button
    public void ascOrderBtn() {

        buttonAscOrder = new JButton("Sort Table (Asc)");
        buttonAscOrder.setBounds(40, 100, 150, 30);
        buttonAscOrder.addActionListener(this);
        buttonAscOrder.setFocusable(false);
        buttonAscOrder.setForeground(Color.BLACK);
        buttonAscOrder.setBackground(Color.WHITE);
        buttonAscOrder.setOpaque(true);
        buttonAscOrder.setBorderPainted(false);
        buttonAscOrder.setHorizontalTextPosition(JButton.RIGHT);
        buttonAscOrder.setVerticalTextPosition(JButton.CENTER);

        buttons.add(buttonAscOrder);

    }
    public  void intro(){
        JLabel label = new JLabel();
        label.setText("Formula 1 2022 Championship");
        label.setBounds(100,100,150,150);
        label.setFont(label.getFont().deriveFont(12f));

        ImageIcon icon = new ImageIcon(new ImageIcon
                ("src/com/company/Img/logo.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        label.setIcon(icon);
        gridEditor.anchor = GridBagConstraints.CENTER;
        this.add(label, gridEditor);
    }
    //menu bar displayed in the frame
    public void menuBar() {
        JMenuBar menu = new JMenuBar();

        this.setJMenuBar(menu);
        JMenu fileMenu = new JMenu("File");


        loadData = new JMenuItem("Load");


        saveData = new JMenuItem("Save");


        saveData.addActionListener(this);
        loadData.addActionListener(this);

        fileMenu.add(saveData);
        fileMenu.add(loadData);

        menu.add(fileMenu);
    }
    // Search Bar
    public void searchBar(){
        buttonSubmit = new JButton("Search");
        buttonSubmit.setSize(5,5);
        buttonSubmit.addActionListener(this);
        buttonSubmit.setFocusable(false);
        buttonSubmit.setHorizontalTextPosition(JButton.RIGHT);
        buttonSubmit.setVerticalTextPosition(JButton.CENTER);

        gridEditor.anchor = GridBagConstraints.PAGE_END;
        gridEditor.weightx = 0;
        gridEditor.weighty = 0;
        gridEditor.gridx = 0;
        gridEditor.gridy = 1;
        this.add(buttonSubmit, gridEditor);
        gridEditor.anchor = GridBagConstraints.LAST_LINE_END;
        gridEditor.weightx = 0;
        gridEditor.weighty = 1;
        gridEditor.gridx = 0;
        gridEditor.gridy = 1;
        this.add(search, gridEditor);
    }


    //Descending order button
    public void decOrderBtn() {

        buttonDecOrder = new JButton("Sort Table (Dec)");
        buttonDecOrder.setForeground(Color.BLACK);
        buttonDecOrder.setBackground(Color.WHITE);
        buttonDecOrder.setOpaque(true);
        buttonDecOrder.setBorderPainted(false);
        buttonDecOrder.addActionListener(this);
        buttonDecOrder.setFocusable(false);
        buttonDecOrder.setHorizontalTextPosition(JButton.RIGHT);
        buttonDecOrder.setVerticalTextPosition(JButton.CENTER);
        buttonDecOrder.setPreferredSize(new Dimension(buttonAscOrder.getSize()));

        buttons.add(buttonDecOrder);


    }
        //Descending order by wins button
    public void decOrderWinBtn() {

        buttonWinsDec = new JButton("Sort By Wins (Dec)");
        buttonWinsDec.addActionListener(this);
        buttonWinsDec.setFocusable(false);
        buttonWinsDec.setForeground(Color.BLACK);
        buttonWinsDec.setBackground(Color.WHITE);
        buttonWinsDec.setOpaque(true);
        buttonWinsDec.setBorderPainted(false);
        buttonWinsDec.setHorizontalTextPosition(JButton.RIGHT);
        buttonWinsDec.setVerticalTextPosition(JButton.CENTER);
        buttonWinsDec.setPreferredSize(new Dimension(buttonAscOrder.getSize()));

        buttons.add(buttonWinsDec);

    }
    //race history button to view race dates
    public void raceHistoryBtn() {

        buttonRaceHistory = new JButton("Race History");
        buttonRaceHistory.setBounds(40, 100, 150, 30);
        buttonRaceHistory.addActionListener(this);
        buttonRaceHistory.setFocusable(false);
        buttonRaceHistory.setForeground(Color.BLACK);
        buttonRaceHistory.setBackground(Color.WHITE);
        buttonRaceHistory.setOpaque(true);
        buttonRaceHistory.setBorderPainted(false);
        buttonRaceHistory.setHorizontalTextPosition(JButton.RIGHT);
        buttonRaceHistory.setVerticalTextPosition(JButton.CENTER);
        buttonRaceHistory.setPreferredSize(new Dimension(buttonAscOrder.getSize()));

        buttons.add(buttonRaceHistory);

    }
        //race button to create random races
    public void raceBtn() {

        buttonRace = new JButton("Race");
        buttonRace.addActionListener(this);
        buttonRace.setFocusable(false);
        buttonRace.setForeground(Color.BLACK);
        buttonRace.setBackground(Color.WHITE);
        buttonRace.setOpaque(true);
        buttonRace.setBorderPainted(false);
        buttonRace.setHorizontalTextPosition(JButton.RIGHT);
        buttonRace.setVerticalTextPosition(JButton.CENTER);
        buttonRace.setPreferredSize(new Dimension(buttonAscOrder.getSize()));

        buttons.add(buttonRace);

    }
        //reset race progress button
    public void resetBtn(){

        buttonReset = new JButton("Reset Progress");
        buttonReset.setBounds(40, 100, 150, 30);
        buttonReset.addActionListener(this);
        buttonReset.setFocusable(false);
        buttonReset.setForeground(Color.BLACK);
        buttonReset.setBackground(Color.WHITE);
        buttonReset.setOpaque(true);
        buttonReset.setBorderPainted(false);
        buttonReset.setHorizontalTextPosition(JButton.RIGHT);
        buttonReset.setVerticalTextPosition(JButton.CENTER);
        buttonReset.setPreferredSize(new Dimension(buttonAscOrder.getSize()));

        buttons.add(buttonReset);

    }
    // descending order by points
    public void pointsDecOrder() {

        clearScreen(); //calling out method to erase screen and display new content

        this.add(textOnScreen);

        if (driver.drivers.isEmpty()) {
            textOnScreen.setText("There are no drivers!");
            textOnScreen.setForeground(Color.orange);
            textOnScreen.setOpaque(true);
            gridEditor.anchor = GridBagConstraints.PAGE_START;
            gridEditor.weightx = 0;
            gridEditor.weighty = 0;
            gridEditor.gridx = 1;
            gridEditor.gridy = 1;
            panel.add(textOnScreen, gridEditor);

        } else {

            int driverNum = 0;
            Collections.sort(driver.drivers, new Comparator<Driver>() {     //Sorting the driver descending order this sorting method is used for list in java it sorts points
                public int compare(Driver driver1, Driver driver2) {
                    return driver2.getDriverpoints() - driver1.getDriverpoints();
                }

            });

            while (driverNum < driver.drivers.size()) {

                panel.add(showDrivers[driverNum] = new JLabel("<html><font color=black>Driver " + (driverNum + 1) + "<BR><html><font color=orange>Full Name - " + driver.drivers.get(driverNum).getDriverFirstname()  + " " + driver.drivers.get(driverNum).getDriverLastname() +
                        "<BR>Team - " + driver.drivers.get(driverNum).getDriverTeamname() + "<BR> Points - " + driver.drivers.get(driverNum).getDriverpoints() + "<BR>" + "</font></html>"));

                driverNum++;
            }
        }
    }
    //Descending order by wins
    public void winsDecOrder() {

        clearScreen(); //calling out method to erase screen and display new content

        if (driver.drivers.isEmpty()) {

            textOnScreen.setText("There are no drivers!");
            textOnScreen.setForeground(Color.orange);
            gridEditor.anchor = GridBagConstraints.PAGE_START;
            gridEditor.weightx = 0;
            gridEditor.weighty = 0;
            gridEditor.gridx = 1;
            gridEditor.gridy = 1;
            panel.add(textOnScreen, gridEditor);

        } else {

            int driverNum = 0;
            Collections.sort(driver.drivers, new Comparator<Driver>() {  //Sorting the wins in descending order this sorting method is used for list in java
                public int compare(Driver driver1, Driver driver2) {
                    return driver2.getDriverWins() - driver1.getDriverWins();
                }

            });

            while (driverNum < driver.drivers.size()) {

                panel.add(showDrivers[driverNum] = new JLabel("<html><font color=black>Driver " + (driverNum + 1) + "<BR><html><font color=orange>Full Name - " + driver.drivers.get(driverNum).getDriverFirstname() + " " + driver.drivers.get(driverNum).getDriverLastname() +
                        "<BR>Team - " + driver.drivers.get(driverNum).getDriverTeamname() + "<BR> Wins - " + driver.drivers.get(driverNum).getDriverWins() + "<BR>" + "</font></html>"));
                panel.setForeground(Color.orange);
                driverNum++;

            }
        }
    }
    // Ascending order by points
    public void pointsAscOrder() {

        clearScreen(); //calling out method to erase screen and display new content

        this.add(textOnScreen);

        if (driver.drivers.isEmpty()) {

            textOnScreen.setText("There are no drivers!");
            textOnScreen.setForeground(Color.orange);
            gridEditor.anchor = GridBagConstraints.PAGE_START;
            gridEditor.weightx = 0;
            gridEditor.weighty = 0;
            gridEditor.gridx = 1;
            gridEditor.gridy = 1;
            panel.add(textOnScreen, gridEditor);

        } else {

            int driverNum = 0;
            Collections.sort(driver.drivers, new Comparator<Driver>() { //Sorting the driver Ascending order this sorting method is used for list in java
                public int compare(Driver driver1, Driver driver2) {
                    return driver1.getDriverpoints() - driver2.getDriverpoints();

                }
            });

            while (driverNum < driver.drivers.size()) {

                panel.add(showDrivers[driverNum] = new JLabel("<html><font color=black>Driver " + (driverNum + 1) + "<BR><html><font color=orange>Full Name - " + driver.drivers.get(driverNum).getDriverFirstname() + " " + driver.drivers.get(driverNum).getDriverLastname() +
                        "<BR>Team - " + driver.drivers.get(driverNum).getDriverTeamname() + "<BR> Points - " + driver.drivers.get(driverNum).getDriverpoints() + " <BR>" + "</font></html>"));

                driverNum++;

            }
        }
    }
    //resetting race progress
    public void resetProgress() throws IOException {

        driver.drivers.clear();
        driver.currentTrack = 1;

        String dataFile = "src/com/company/Data/driverstatReset.txt";
        String raceDateResetFile = "src/com/company/Data/DateReset.txt";
        String raceDriverPosFile = "src/com/company/Data/positions.txt";

        String line1, line2, line3, line4, line5, line6, line7;

        Scanner readDateResetFile = null;
        Scanner readDataResetFile = null;

        BufferedWriter writeRaceDriverPosFile = new BufferedWriter(new FileWriter(raceDriverPosFile));
        Scanner readRaceDriverPosFile = null;

        try {

            readDataResetFile = new Scanner(new File(dataFile));
            readDateResetFile = new Scanner(new File(raceDateResetFile));
            readRaceDriverPosFile = new Scanner(new File(raceDriverPosFile));

        } catch (Exception e) {

            System.out.println("File not found.");

        }

        int driverLine = 0;
        while (readDataResetFile.hasNext()) {

            line1 = readDataResetFile.nextLine();
            line2 = readDataResetFile.nextLine();
            line3 = readDataResetFile.nextLine();
            line4 = readDataResetFile.nextLine();
            line5 = readDataResetFile.nextLine();
            line6 = readDataResetFile.nextLine();
            line7 = readDataResetFile.nextLine();

            driver.drivers.add(new Formula1Driver(line1, line2, line3, line4, Integer.parseInt(line5), Integer.parseInt(line6), Integer.parseInt(line7)));

            driverLine++;

        }

        if (!(driverLine < 1)) {

            for (int i = 0; i < driverLine; i++) {
                driver.f1Teams.remove(driver.drivers.get(driverLine - 1).getDriverTeamname());

            }
        }

        while (readDateResetFile.hasNext()) {

            driver.startDate = Integer.parseInt(readDateResetFile.nextLine());
            driver.startMonth = Integer.parseInt(readDateResetFile.nextLine());
            driver.championshipYear = Integer.parseInt(readDateResetFile.nextLine());

        }

        while(readRaceDriverPosFile.hasNext()){

            writeRaceDriverPosFile.flush();
        }

        if (showDrivers[0] != null) {

            for (int i = 0; i < 10; i++) {

                showDrivers[i].setText("");

            }
        }

        textOnScreen.setText("Progress has been reset");
        textOnScreen.setForeground(Color.orange);
        gridEditor.anchor = GridBagConstraints.PAGE_START;
        gridEditor.weightx = 0;
        gridEditor.weighty = 0;
        gridEditor.gridx = 1;
        gridEditor.gridy = 1;
        panel.add(textOnScreen, gridEditor);
        readDateResetFile.close();
        readDataResetFile.close();

    }
    //generating random race
    public void race() throws IOException {

        Random rand = new Random();
        String raceData = "src/com/company/Data/positions.txt";
        BufferedWriter writeToFile = new BufferedWriter(new FileWriter(raceData, true));
        clearScreen();

        if(driver.currentTrack == 11){
            seasonWinner();
        }
        else{

            if(driver.drivers.size() == 10) {

                textOnScreen.setText("Position" + "       " + driver.startDate + "/" + driver.startMonth + "/" + driver.championshipYear);
                textOnScreen.setForeground(Color.orange);
                gridEditor.anchor = GridBagConstraints.PAGE_START;
                gridEditor.weightx = 0;
                gridEditor.weighty = 0;
                gridEditor.gridx = 1;
                gridEditor.gridy = 1;
                panel.add(textOnScreen, gridEditor);
                driver.changeDate();

                HashMap<Integer, String> results = new HashMap<>();
                ArrayList<Integer> positions = new ArrayList<>();

                for (int pos = 0; pos < driver.maxDrivers; pos++) {

                    int randomPos = rand.nextInt(10);

                    if (results.containsKey(randomPos)) {

                        while (results.containsKey(randomPos)) {

                            randomPos = rand.nextInt(10);

                        }

                        results.put(randomPos, driver.drivers.get(randomPos).getDriverFirstname());

                    } else {

                        results.put(randomPos, driver.drivers.get(randomPos).getDriverFirstname());

                    }

                    panel.add(raceResults[pos] = new JLabel("<html> <font color=orange>" +
                            "P" + (pos + 1) + " - " + results.get(randomPos) +
                            " " + driver.drivers.get(randomPos).getDriverTeamname() + "<BR> <BR>" + "</font></html>"));

                    positions.add(randomPos);

                }

                for (int i = 0; i < results.size(); i++) {

                    writeToFile.write(driver.drivers.get(positions.get(i)).getDriverFirstname() + "\n");


                }
                System.out.println(positions);

                for (int i = 0; i < positions.size(); i++) {

                    racePositions(i, positions);

                }
                if(driver.currentTrack == 11){

                    seasonWinner();

                }
            }

            else{

                textOnScreen.setText("Not enough drivers to start the race!");
                textOnScreen.setForeground(Color.orange);

            }

            writeToFile.close();
            driver.currentTrack++;

        }
    }
    // calculating and allocating required points for the race positions
    public void racePositions(int position, ArrayList<Integer> positions){

        switch (position) {

            case 0:
                driver.drivers.get(positions.get(position)).setDriverWins(1);
                driver.drivers.get(positions.get(position)).setDriverpoints(25);
                driver.drivers.get(positions.get(position)).setDriverCompletedRaces(1);
                break;

            case 1:
                driver.drivers.get(positions.get(position)).setDriverpoints(18);
                driver.drivers.get(positions.get(position)).setDriverCompletedRaces(1);
                break;

            case 2:
                driver.drivers.get(positions.get(position)).setDriverpoints(5);
                driver.drivers.get(positions.get(position)).setDriverCompletedRaces(1);
                break;

            case 3:
                driver.drivers.get(positions.get(position)).setDriverpoints(12);
                driver.drivers.get(positions.get(position)).setDriverCompletedRaces(1);
                break;

            case 4:
                driver.drivers.get(positions.get(position)).setDriverpoints(10);
                driver.drivers.get(positions.get(position)).setDriverCompletedRaces(1);
                break;

            case 5:
                driver.drivers.get(positions.get(position)).setDriverpoints(8);
                driver.drivers.get(positions.get(position)).setDriverCompletedRaces(1);
                break;

            case 6:
                driver.drivers.get(positions.get(position)).setDriverpoints(6);
                driver.drivers.get(positions.get(position)).setDriverCompletedRaces(1);
                break;

            case 7:
                driver.drivers.get(positions.get(position)).setDriverpoints(4);
                driver.drivers.get(positions.get(position)).setDriverCompletedRaces(1);
                break;

            case 8:
                driver.drivers.get(positions.get(position)).setDriverpoints(2);
                driver.drivers.get(positions.get(position)).setDriverCompletedRaces(1);
                break;

            case 9:
                driver.drivers.get(positions.get(position)).setDriverpoints(1);
                driver.drivers.get(positions.get(position)).setDriverCompletedRaces(1);
                break;

        }
    }
    //calculating season winner from all random races
    public void seasonWinner(){

        driver.sortByPointsDec();
        textOnScreen.setText("<html> <font color=orange>" + "- Winner of the 2022 Formula Championship - <BR><BR>" +
                "         " + driver.drivers.get(9).getDriverFirstname() + " " + driver.drivers.get(9).getDriverLastname()
                + " with " + driver.drivers.get(9).getDriverTeamname() + "<BR> <BR></font>");

        gridEditor.anchor = GridBagConstraints.CENTER;
        gridEditor.weightx = 0;
        gridEditor.weighty = 0;
        gridEditor.gridx = 1;
        gridEditor.gridy = 1;

        panel.add(textOnScreen, gridEditor);

    }
    // displaying all the race dates according to the random races
    public void raceHistory() {

        clearScreen();

        if(driver.currentTrack == 1){

            textOnScreen.setText("No races have taken place");
            textOnScreen.setForeground(Color.orange);
            gridEditor.anchor = GridBagConstraints.PAGE_START;
            gridEditor.weightx = 0;
            gridEditor.weighty = 0;
            gridEditor.gridx = 1;
            gridEditor.gridy = 1;

            panel.add(textOnScreen, gridEditor);

        }else{

            int driverNum = 0;
            while (driverNum < driver.currentTrack - 1) {
                JLabel date = new JLabel(raceDates[driverNum]+"\n");
                JLabel line = new JLabel("\n");
                racesHistory.add(date);
                racesHistory.add(line);                 //Adding a line between
                date.setForeground(Color.orange);
                driverNum++;

            }
            for(JLabel loc : racesHistory){

                panel.add(loc);
                System.out.println("");

            }
        }
    }
    public void saveData() throws IOException {

        String dataFile = "src/com/company/Data/driverstats.txt";
        String raceDateFile = "src/com/company/Data/Date.txt";
        String raceDriverFile = "src/com/company/Data/positionSave.txt";


        BufferedWriter writeDataFile = new BufferedWriter(new FileWriter(dataFile));
        BufferedWriter writeRaceDateFile = new BufferedWriter(new FileWriter(raceDateFile));
        BufferedWriter writeRaceDriverFile = new BufferedWriter(new FileWriter(raceDriverFile));
        // save data on driver
        for (int i = 0; i < driver.drivers.size(); i++) {

            writeDataFile.write(driver.drivers.get(i).getDriverFirstname() + "\n");
            writeDataFile.write(driver.drivers.get(i).getDriverLastname() + "\n");
            writeDataFile.write(driver.drivers.get(i).getDriverCountry() + "\n");
            writeDataFile.write(driver.drivers.get(i).getDriverTeamname() + "\n");
            writeDataFile.write(driver.drivers.get(i).getDriverpoints() + "\n");
            writeDataFile.write(driver.drivers.get(i).getDriverCompletedRaces() + "\n");
            writeDataFile.write(driver.drivers.get(i).getDriverWins() + "\n");

        }
        // saves the dates of the races
        writeRaceDateFile.write(driver.startDate + "\n");
        writeRaceDateFile.write(driver.startMonth + "\n");
        writeRaceDateFile.write(driver.championshipYear + "\n");
        String raceDriverPos = "src/com/company/Data/positions.txt";
        Scanner readDataFile = null;

        try {

            readDataFile = new Scanner(new File(raceDriverPos));

        } catch (Exception e) {

            textOnScreen.setText("File not found.");

        }

        while(readDataFile.hasNext()){

            writeRaceDriverFile.write(readDataFile.nextLine() + "\n");

        }

        if (showDrivers[0] != null) {

            for (int i = 0; i < showDrivers.length; i++) {

                showDrivers[i].setText("");

            }
        }

        textOnScreen.setText("Data has been saved");
        textOnScreen.setForeground(Color.black);

        gridEditor.anchor = GridBagConstraints.PAGE_START;

        gridEditor.weightx = 0;
        gridEditor.weighty = 0;
        gridEditor.gridx = 1;
        gridEditor.gridy = 1;

        panel.add(textOnScreen, gridEditor);

        writeDataFile.close();
        writeRaceDateFile.close();
        writeRaceDriverFile.close();
    }
    public void loadData() throws IOException {

        driver.drivers.clear();

        String dataFile = "src/com/company/Data/driverstats.txt";
        String raceDateFile = "src/com/company/Data/Date.txt";
        String raceDriverPos = "src/com/company/Data/positionsSave.txt";
        String raceDriverPosSwap = "src/com/company/Data/positions.txt";



        String line1, line2, line3, line4, line5, line6, line7;

        //Inheriting Writer Class
        BufferedWriter writeDriverPosSwap = new BufferedWriter(new FileWriter(raceDriverPosSwap));

        Scanner readDateFile = null;
        Scanner readDataFile = null;
        Scanner readRaceDriverPos = null;

        try {

            readDataFile = new Scanner(new File(dataFile));
            readDateFile = new Scanner(new File(raceDateFile));
            readRaceDriverPos = new Scanner(new File(raceDriverPos));

        } catch (Exception e) {

            textOnScreen.setText("File not found.");
            textOnScreen.setForeground(Color.BLACK);

        }

        int driverLine = 0;

        while (readDataFile.hasNext()) {

            line1 = readDataFile.nextLine();
            line2 = readDataFile.nextLine();
            line3 = readDataFile.nextLine();
            line4 = readDataFile.nextLine();
            line5 = readDataFile.nextLine();
            line6 = readDataFile.nextLine();
            line7 = readDataFile.nextLine();

            driver.drivers.add(new Formula1Driver(line1, line2, line3, line4, Integer.parseInt(line5), Integer.parseInt(line6), Integer.parseInt(line7)));

            driverLine++;

        }

        /*while(readRaceDriverPos.hasNext()){

            String name = readRaceDriverPos.nextLine() + "\n";

            System.out.print(name);

            writeDriverPosSwap.write(name);

        }*/

        if (!(driverLine < 1)) {

            for (int i = 0; i < driverLine; i++) {

                driver.f1Teams.remove(driver.drivers.get(driverLine - 1).getDriverTeamname());

            }
        }

        while (readDateFile.hasNext()) {

            driver.startDate = Integer.parseInt(readDateFile.nextLine());
            driver.startMonth = Integer.parseInt(readDateFile.nextLine());
            driver.championshipYear = Integer.parseInt(readDateFile.nextLine());

        }
        if (showDrivers[0] != null) {

            for (int i = 0; i < 10; i++) {

                showDrivers[i].setText("");

            }
        }

        textOnScreen.setText("Data has been loaded");
        textOnScreen.setForeground(Color.BLACK);
        gridEditor.anchor = GridBagConstraints.PAGE_START;
        gridEditor.weightx = 0;
        gridEditor.weighty = 0;
        gridEditor.gridx = 1;
        gridEditor.gridy = 1;
        panel.add(textOnScreen, gridEditor);

        readDateFile.close();
        readDataFile.close();
        //readRaceDriverPos.close();
        writeDriverPosSwap.close();

    }


    // handling all button components accordingly
    public void actionPerformed(ActionEvent e) {

        int buttonDecOrderClicks = 0, buttonAscOrderClicks = 0, buttonDecOrderWinsClicks = 0;
        if (e.getSource() == buttonDecOrder) {

            buttonAscOrderClicks = 0;

            if (buttonDecOrderClicks == 0) {

                pointsDecOrder();
                buttonDecOrderClicks++;
            }
        }

        if (e.getSource() == buttonAscOrder) {

            if (buttonAscOrderClicks == 0) {

                pointsAscOrder();
                buttonAscOrderClicks++;
            }
        }
        if (e.getSource() == saveData) {

            try {
                saveData();

            } catch (IOException ex) {

                ex.printStackTrace();
            }
        }
        if (e.getSource() == loadData) {
            try {
                loadData();
                                             // handling exceptions and errors
            } catch (IOException ex) {

                ex.printStackTrace();
            }
        }
        if(e.getSource() ==  buttonWinsDec){

            if (buttonDecOrderWinsClicks == 0) {

                winsDecOrder();
                buttonDecOrderWinsClicks++;
            }
        }

        if (e.getSource() == buttonRace) {

            try {

                race();
                                            // handling exceptions and errors
            } catch (IOException ex) {

                ex.printStackTrace();
            }
        }
        if (e.getSource() == buttonReset) {

            try {

                resetProgress();
                                            // handling exceptions and errors
            } catch (IOException ex) {

                ex.printStackTrace();
            }
        }

        if (e.getSource() == buttonRaceHistory) {

            raceHistory();
        }
            }
        }