package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static persistance.SaveGame.readFile;

// A SolarSystem consists of a list of Planets
// List<Planet>

public class SolarSystem {


    private List<Celestial> solarSystem;
    private List<String> solarSystemIdList;

    // Creates a SolarSystem
    // Effects: Creates a SolarSystem with empty ArrayList<>
    public SolarSystem() {
        solarSystem = new ArrayList<>();
    }

    //effects: adds planet to the list if it isnt already in the list
    //modifies: solarSystem
    public void addPlanetaryBody(Celestial p) {
        boolean addPlanet = true;
        for (Celestial existingCelestial : solarSystem) {
            if (existingCelestial.getPlanetName().equals(p.getPlanetName())) {
                addPlanet = false;
            }
        }
        if (addPlanet) {
            solarSystem.add(p);
        }
    }

    // Returns list of default planets the API will call at start of program
    // Modifies: this
    // Effects: Returns list of strings containing the 8 planets in solar system
    public List<String> getSolarSystemList(String fileName) {
        String result = readFile(fileName);
        JSONObject updatedPlanetListObj = new JSONObject(readFile(fileName));
        JSONArray updatedPlanetListArr;
        updatedPlanetListArr = (JSONArray) updatedPlanetListObj.get("Planets");
        List<String> savedPlanetList = new ArrayList<>();
        if (result.length() > 85) {
            for (int i = 0; i < updatedPlanetListArr.length(); i++) {
                savedPlanetList.add((String) updatedPlanetListArr.get(i));
            }
            return savedPlanetList;
        } else {
            return defaultPlanetList();
        }
    }

    public List<String> defaultPlanetList() {
        solarSystemIdList = new ArrayList<>();
        solarSystemIdList.add("mercure");
        solarSystemIdList.add("venus");
        solarSystemIdList.add("terre");
        solarSystemIdList.add("mars");
        solarSystemIdList.add("jupiter");
        solarSystemIdList.add("saturne");
        solarSystemIdList.add("uranus");
        solarSystemIdList.add("neptune");
        return solarSystemIdList;
    }

    public void removePlanetaryBody(String planetName) {
        for (Celestial p : solarSystem) {
            if (p.getPlanetName() == planetName) {
                solarSystem.remove(p);
            }
        }
    }

    public List<Celestial> getSolarSystem() {
        return solarSystem;
    }

    public Integer getSolarSystemSize() {
        return solarSystem.size();
    }

    public List<String> getSolarSystemIdList() {
        return solarSystemIdList;
    }
}
