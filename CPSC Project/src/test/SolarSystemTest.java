import model.Celestial;
import model.SolarSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistance.SaveGame;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static persistance.SaveGame.savePlanetList;

public class SolarSystemTest {

    private List<String> testSystem1;
    private SolarSystem testSystem2;
    private String testApiData1;
    private String testApiData2;
    private String testApiData3;
    private Celestial testCelestial1;
    private Celestial testCelestial2;

    @BeforeEach
    public void runBefore() {
        testSystem1 = new ArrayList<>();
        testSystem2 = new SolarSystem();
        testApiData1 = "{\"id\":\"terre\",\"name\":\"La Terre\",\"englishName\":\"Earth\",\"isPlanet\":true,\""
                + "moons\":[{\"moon\":\"La Lune\",\"rel\":\"https://api.le-systeme-solaire.net/rest/bodies/lune\"}],"
                + "\"semimajorAxis\":149598262,\"perihelion\":147095000,\"aphelion\":152100000,\"eccentricity\""
                + ":0.01670,\"inclination\":0,\"mass\":{\"massValue\":5.97237, \"massExponent\":24},\"vol\":{\""
                + "volValue\":1.08321, \"volExponent\":12},\"density\":5.51360,\"gravity\":9.80000,\"escape\""
                + ":11190.00000,\"meanRadius\":6371.00840,\"equaRadius\":6378.13660,\"polarRadius\":6356.80000,"
                + "\"flattening\":0.00335,\"dimension\":\"\",\"sideralOrbit\":365.25600,\"sideralRotation\""
                + ":23.93450,\"aroundPlanet\":null,\"discoveredBy\":\"\",\"discoveryDate\":\"\",\"alternativeName"
                + "\":\"\"}";
        testApiData2 = "{\"id\":\"venus\",\"name\":\"Vénus\",\"englishName\":\"Venus\",\"isPlanet\":true,\"moons"
                + "\":null,\"semimajorAxis\":108208475,\"perihelion\":107477000,\"aphelion\":108939000,\"eccentricity"
                + "\":0.00670,\"inclination\":3.39000,\"mass\":{\"massValue\":4.86747, \"massExponent\":24},\"vol\":"
                + "{\"volValue\":9.28430, \"volExponent\":11},\"density\":5.24300,\"gravity\":8.87000,\"escape\""
                + ":10360.00000,\"meanRadius\":6051.80000,\"equaRadius\":6051.80000,\"polarRadius\":6051.80000,\""
                + "flattening\":0,\"dimension\":\"\",\"sideralOrbit\":224.70100,\"sideralRotation\":-5832.50000,\""
                + "aroundPlanet\":null,\"discoveredBy\":\"\",\"discoveryDate\":\"\",\"alternativeName\":\"\"}";
        testApiData3 = "{\"id\":\"earthearthearthearthearthearthearthearthearthearthearthearthearthearthearth"
                + "earthearthearthearthearthearthearthearthearth"
                + "\",\"name\":\"Vénus\",\"englishName\":\"Jupiter\",\"isPlanet\":true,\"moons"
                + "\":null,\"semimajorAxis\":108208475,\"perihelion\":107477000,\"aphelion\":108939000,\"eccentricity"
                + "\":0.00670,\"inclination\":3.39000,\"mass\":{\"massValue\":4.86747, \"massExponent\":24},\"vol\":"
                + "{\"volValue\":9.28430, \"volExponent\":11},\"density\":5.24300,\"gravity\":8.87000,\"escape\""
                + ":10360.00000,\"meanRadius\":6051.80000,\"equaRadius\":6051.80000,\"polarRadius\":6051.80000,\""
                + "flattening\":0,\"dimension\":\"\",\"sideralOrbit\":224.70100,\"sideralRotation\":-5832.50000,\""
                + "aroundPlanet\":null,\"discoveredBy\":\"\",\"discoveryDate\":\"\",\"alternativeName\":\"\"}";
        testCelestial1 = new Celestial(testApiData1);
        testCelestial2 = new Celestial(testApiData2);
    }

    @Test
    public void testSolarSystem() {
        testSystem1 = new ArrayList<>();
        testSystem2 = new SolarSystem();
        assertEquals(testSystem1, testSystem2.getSolarSystem());
    }

    @Test
    public void testAddPlanetaryBody() {
        assertEquals(0, testSystem2.getSolarSystem().size());
        testSystem2.addPlanetaryBody(testCelestial1);
        testSystem2.addPlanetaryBody(testCelestial2);
        testSystem2.addPlanetaryBody(testCelestial2);
        assertEquals(2, testSystem2.getSolarSystem().size());
    }

    @Test
    public void testGetSolarSystem() {

        testSystem2 = new SolarSystem();
        assertEquals(0, testSystem2.getSolarSystem().size());
        testSystem2.addPlanetaryBody(testCelestial1);
        testSystem2.addPlanetaryBody(testCelestial2);
        assertEquals(2, testSystem2.getSolarSystem().size());

    }

    @Test
    public void testGetSolarSystemSize() {
        testSystem2 = new SolarSystem();
        assertEquals(0, testSystem2.getSolarSystemSize());
        testSystem2.addPlanetaryBody(testCelestial1);
        testSystem2.addPlanetaryBody(testCelestial2);
        assertEquals(2, testSystem2.getSolarSystemSize());
    }

    @Test
    public void testDefaultPlanetList() {
        testSystem1 = testSystem2.defaultPlanetList();
        assertEquals(8, testSystem1.size());
    }

    @Test
    public void testRemovePlanetaryBody() {
        testSystem2.addPlanetaryBody(testCelestial1);
        testSystem2.addPlanetaryBody(testCelestial2);
        assertEquals(2, testSystem2.getSolarSystemSize());
        testSystem2.removePlanetaryBody("notAPlanet");
        assertEquals(2, testSystem2.getSolarSystemSize());
        testSystem2.removePlanetaryBody(testCelestial1.getPlanetName());
        assertEquals(1, testSystem2.getSolarSystemSize());
    }

    @Test
    public void testGetSolarSystemList() {
        testSystem2 = new SolarSystem();
        testSystem1 = new ArrayList<>();
        testSystem2.addPlanetaryBody(new Celestial(testApiData1));
        testSystem2.addPlanetaryBody(new Celestial(testApiData2));
        testSystem2.addPlanetaryBody(new Celestial(testApiData3));

        savePlanetList(testSystem2, "./data/testPlanets.txt");
        assertEquals(3, testSystem2.getSolarSystemList("./data/testPlanets.txt").size());

        SaveGame.loadDefault("./data/testPlanets.txt");
        testSystem2.getSolarSystemList("./data/testPlanets.txt");
        testSystem1 = testSystem2.getSolarSystemIdList();
        assertEquals(8, testSystem1.size());

    }
}
