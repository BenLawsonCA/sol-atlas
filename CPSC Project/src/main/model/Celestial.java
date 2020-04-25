package model;

import org.json.JSONObject;

// Creates a new Planet containing descriptive information
// about different characteristics Parses this information from
// API call.

public class Celestial {

    String planetName;
    String planetID;
    int radiusKm;              // Radius of planet in kilometers
    int massExponent;          // Mass exponent for value in kilograms
    float massValue;           // Mass value for value in kilograms
    float semiMajorAxis;       // Mean distance from Sun in kilometers
    float orbitalPeriod;       // Time for planet to orbit sun in Earth Days
    float rotationalPeriod;    // Time for planet to rotate once in Earth Days
    float density;             // Density in g/cm^3
    float massFinal;


    // Constructs a Planet with information parsed from API
    // Effects: Planet consists of 8 descriptive characteristics
    public Celestial(String planetData) {
        JSONObject obj = new JSONObject(planetData);
        planetName = obj.getString("englishName");
        radiusKm = obj.getInt("meanRadius");
        massValue = obj.getJSONObject("mass").getFloat("massValue");
        massExponent = obj.getJSONObject("mass").getInt("massExponent");
        massFinal = (float) (massValue * Math.pow(10, massExponent));
        semiMajorAxis = obj.getFloat("semimajorAxis");
        orbitalPeriod = obj.getFloat("sideralOrbit");
        rotationalPeriod = obj.getFloat("sideralRotation");
        density = obj.getFloat("density");
        planetID = obj.getString("id");
    }

    public String displayCelestialData() {
        String data = "";
        data = ("Name:                       " + this.getPlanetName() + "\n")
                + ("Density:                    " + this.getDensity() + " g/cm^3\n")
                + ("Mass:                        " + this.getMassFinal() + " kg\n")
                + ("Orbital Period:         " + this.getOrbitalPeriod() + " days\n")
                + ("Radius:                     " + this.getRadiusKm() + " kilometers" + "\n")
                + ("Semi-Major Axis:     " + this.getSemiMajorAxis() + " kilometers" + "\n")
                + ("Rotational Period:   " + this.getRotationalPeriod() + " hours" + "\n");
        return data;
    }


    public String getPlanetName() {
        return planetName;
    }

    public int getRadiusKm() {
        return radiusKm;
    }

    public int getMassExponent() {
        return massExponent;
    }

    public float getMassValue() {
        return massValue;
    }

    public float getSemiMajorAxis() {
        return semiMajorAxis;
    }

    public float getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public float getRotationalPeriod() {
        return rotationalPeriod;
    }

    public float getDensity() {
        return density;
    }

    public float getMassFinal() {
        return massFinal;
    }

    public String getPlanetID() {
        return planetID;
    }

}
