import model.HttpCelestialDataLoader;
import model.Celestial;
import model.SolarSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class HttpCelestialDataLoaderTest {

    private static List<String> testList;
    private Celestial testCelestial1;
    private Celestial testCelestial2;
    private String testApiData1;
    private String testApiData2;
    private SolarSystem solarSystemTest;
    private HttpCelestialDataLoader planetDataLoaderTest;
    @BeforeEach
    public void runBefore() {
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
        testCelestial1 = new Celestial(testApiData1);
        testCelestial2 = new Celestial(testApiData2);
        solarSystemTest = new SolarSystem();
        testList = new ArrayList<>();
        planetDataLoaderTest = new HttpCelestialDataLoader();

    }

    @Test
    public void testPlanetDataLoader() {
        List<String> solarSystemIdList = new ArrayList<>();
        solarSystemIdList.add("Earth");
        solarSystemIdList.add("Venus");
        solarSystemTest = new SolarSystem();
        solarSystemTest.addPlanetaryBody(testCelestial1);
        solarSystemTest.addPlanetaryBody(testCelestial2);
        planetDataLoaderTest = new HttpCelestialDataLoader();

        assertTrue(8 <= planetDataLoaderTest.getSolarSystem().getSolarSystemSize());
        assertEquals(testCelestial1.getPlanetName(),
                planetDataLoaderTest.getSolarSystem().getSolarSystem().get(2).getPlanetName());
        assertEquals(testCelestial2.getPlanetName(),
                planetDataLoaderTest.getSolarSystem().getSolarSystem().get(1).getPlanetName());
        assertFalse(planetDataLoaderTest.getSolarSystem().getSolarSystem().equals(solarSystemTest));
    }

    @Test
    public void testGetPlanetaryData() {
        assertEquals(testApiData1,
                planetDataLoaderTest.getPlanetaryData("earth"));
        assertEquals("fail",
                planetDataLoaderTest.getPlanetaryData("penguins"));
    }


    @Test
    public void testGetSolarSystem() {
        solarSystemTest.addPlanetaryBody(testCelestial1);
        solarSystemTest.addPlanetaryBody(testCelestial2);
        assertEquals(2, solarSystemTest.getSolarSystemSize());
    }
}
