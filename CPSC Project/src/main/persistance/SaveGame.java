package persistance;

import model.Celestial;
import model.SolarSystem;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SaveGame {

    private static FileWriter file;

    public SaveGame() {
    }

    //modifies: this
    //effects: Saves the list of planets in a text file in JSON format
    public static boolean savePlanetList(SolarSystem planetaryBodiesList, String fileName) {
        JSONObject saveGame = new JSONObject();
        JSONArray savePlanetList = new JSONArray();
        for (Celestial p : planetaryBodiesList.getSolarSystem()) {
            savePlanetList.add(p.getPlanetID());
        }
        saveGame.put("Planets", savePlanetList);
        try {
            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter(fileName);
            file.write("{Planets : " + savePlanetList + "}");
            file.flush();
            file.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    //effects: reads from desired file and returns the contents as a string
    public static String readFile(String fileName) {
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            String fileData = "";
            while (myReader.hasNextLine()) {
                fileData += myReader.nextLine(); // https://www.w3schools.com/java/java_files_read.asp
            }
            myReader.close();
            return fileData;
        } catch (FileNotFoundException e) {
            return "fail";
        }
    }

    //effects: loads a default list of planets in data file to be read on startup
    public static boolean loadDefault(String fileName) {

        try {
            file = new FileWriter(fileName);
            file.write("{Planets : [\"mercure\",\"venus\",\"terre\",\"mars\",\"jupiter\",\"saturne\","
                    + "\"uranus\",\"neptune\"]}");
            file.flush();
            file.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}




