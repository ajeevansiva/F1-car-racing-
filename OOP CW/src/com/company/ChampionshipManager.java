package com.company;
import java.io.IOException;

interface ChampionshipManager {

    void saveData() throws IOException;
    void loadData() throws IOException;
    void seasonWinner();
    void pointsAscOrder();
}
