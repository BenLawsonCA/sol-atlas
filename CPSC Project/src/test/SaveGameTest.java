import model.Celestial;
import model.SolarSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistance.SaveGame;

import java.io.FileWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static persistance.SaveGame.savePlanetList;

public class SaveGameTest {

    private FileWriter testFile;
    private SolarSystem solarSystemTest;
    private String testApiData1;
    private String testApiData2;
    private String testApiData3;
    private Celestial testCelestial1;
    private Celestial testCelestial2;
    SaveGame saveTest = new SaveGame();

    @BeforeEach
    public void runBefore() {
        testApiData1 = "{\"id\":\"terre\",\"name\":\"La Terre\",\"englishName\":\"Earth\",\"isPlanet\":true,"
                + "\"moons\":[{\"moon\":\"La Lune\",\"rel\":\"https://api.le-systeme-solaire.net/rest/bodies/lune\"}],"
                + "\"semimajorAxis\":149598262,\"mass\":{\"massValue\":5.97237, \"massExponent\":24},"
                + "\"vol\":{\"volValue\":1.08321, \"volExponent\":12},\"density\":5.51360,\"gravity\":9.80000,"
                + "\"escape\":11190.00000,\"meanRadius\":6371.00840,\"sideralOrbit\":365.25600,\""
                + "sideralRotation\":23.93450}";
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
        solarSystemTest = new SolarSystem();
        solarSystemTest.addPlanetaryBody(new Celestial(testApiData1));
        solarSystemTest.addPlanetaryBody(new Celestial(testApiData2));
    }

    @Test
    public void testSavePlanetList() {
        savePlanetList(solarSystemTest, "./data/testPlanets.txt");
        assertEquals("{Planets : [\"terre\",\"venus\"]}",SaveGame.readFile("./data/testPlanets.txt"));
        solarSystemTest.addPlanetaryBody(new Celestial(testApiData3));
        savePlanetList(solarSystemTest, "./data/testPlanets.txt");
        assertEquals("{Planets : [\"terre\",\"venus\",\"earthearthearthearthearthearthearthearthearthear"
                + "thearthearthearthearthearthearthearthearthearthearthearthearthearthearth\"]}",
                SaveGame.readFile("./data/testPlanets.txt"));

        assertEquals(false, savePlanetList(solarSystemTest, "testFail/testPlanets.txt"));

        // try and write a file to a directory with restricted access??
    }

    @Test
    public void testReadFile() {
        SaveGame.loadDefault("./data/testPlanets.txt");
        assertEquals("fail", SaveGame.readFile("./data/testFail.txt"));
        //need to test the failing exception. success handled above.

    }

    @Test
    public void testLoadDefault() {
        SaveGame.loadDefault("./data/testPlanets.txt");
        assertEquals("{Planets : [\"mercure\",\"venus\",\"terre\",\"mars\",\"jupiter\",\"saturne\","
                + "\"uranus\",\"neptune\"]}",SaveGame.readFile("./data/testPlanets.txt"));
        SaveGame.loadDefault("./data/testPlanets.txt");

        assertEquals(false, SaveGame.loadDefault("/'/testFail.txt"));

        //Need test for exception.
    }
}
