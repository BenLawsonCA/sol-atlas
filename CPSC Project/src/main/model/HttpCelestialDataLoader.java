package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

// Calls API with list of Celestial to populate the SolarSystem with Celestials to load
public class HttpCelestialDataLoader implements CelestialDataLoader {

    private String apiUrl;

    private String planetaryApiData;
    private List<String> solarSystemIdList;
    private SolarSystem solarSystem;

    // Modifies: this
    // Effects: Creates a new instance of HttpCelestialDataLoader
    public HttpCelestialDataLoader() {
        solarSystemIdList = new ArrayList<>();
        solarSystem = new SolarSystem();
        solarSystemIdList = solarSystem.getSolarSystemList("./data/planets.txt");
        planetaryApiData = "";

        for (String s : solarSystemIdList) {
            planetaryApiData = getPlanetaryData(s);
            solarSystem.addPlanetaryBody(new Celestial(planetaryApiData));
        }

    }

    // Modifies: this
    // Effects: Calls API with specific query. Returns JSON as String if successful, "fail" if unsuccessful
    public String getPlanetaryData(String targetURL) {
        apiUrl = "https://api.le-systeme-solaire.net/rest/bodies/";
        String dataAPI = null;
        try {
            URL url = new URL(apiUrl + targetURL);        // New URL with target URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();// Creates URL connection with URL
            connection.setRequestMethod("GET");                                         // Sets request to "GET"
            connection.setRequestProperty("Accept", "application/json");                // Sets response formatting

            if (connection.getResponseCode() != 200) {                                  // Checks the response code
                throw new RuntimeException("Failed: HTTP error code : "             // Prints fail message if not 200
                        + connection.getResponseCode());
            }

            BufferedReader responseToString = new BufferedReader(new InputStreamReader(
                    (connection.getInputStream())));                               //Makes buffer to read the response

            String requestOutput;                                                  //Makes string for buffered read
            while ((requestOutput = responseToString.readLine()) != null) {
                dataAPI = requestOutput;                                            //Stores output in dataAPI
            }

        } catch (Exception e) {
            //e.printStackTrace();
            return "fail";
        }
        return dataAPI; // returns the output string to the user
    }

    public SolarSystem getSolarSystem() {
        return solarSystem;
    }

}
