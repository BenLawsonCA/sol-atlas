package model;


//Reads from the API or File into a SolarSystem ( List<Planet> )

public interface CelestialDataLoader {

    String getPlanetaryData(String targetURL);

    SolarSystem getSolarSystem();
}


