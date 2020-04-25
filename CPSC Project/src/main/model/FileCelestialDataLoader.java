package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

// Reads from File to populate the SolarSystem with planets to load
public class FileCelestialDataLoader implements CelestialDataLoader {

    private String planetaryApiData;
    private List<String> solarSystemIdList;
    private SolarSystem solarSystem;

    // Modifies: this
    // Effects: Creates a new instance of FileCelestialDataLoader
    public FileCelestialDataLoader(List<String> testList) {
        solarSystemIdList = testList;
        solarSystem = new SolarSystem();
        planetaryApiData = "";

        for (String s : solarSystemIdList) {
            planetaryApiData = getPlanetaryData(s);
            solarSystem.addPlanetaryBody(new Celestial(planetaryApiData));
        }

    }

    // Modifies: this
    // Effects: Returns String of JSON from specific folder
    @Override
    public String getPlanetaryData(String celestialID) {
        String dataAPI = null;

        try {
            BufferedReader responseToString = new BufferedReader(new InputStreamReader(
                    (new FileInputStream("./data/" + celestialID + ".txt"))));//Makes buffer to read the response

            String requestOutput;                   //Makes string for buffered read
            while ((requestOutput = responseToString.readLine()) != null) {
                dataAPI = requestOutput;               //Stores output in dataAPI
            }

        } catch (Exception e) {
            //e.printStackTrace();
            return "fail";
        }
        return dataAPI; // returns the output string to the user
    }

    @Override
    public SolarSystem getSolarSystem() {
        return solarSystem;
    }

}
