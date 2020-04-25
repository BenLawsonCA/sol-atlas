import model.FileCelestialDataLoader;
import model.SolarSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileCelestialDataLoaderTest {

    private static List<String> testList;
    private SolarSystem solarSystemTest;
    private FileCelestialDataLoader fileCelestialDataLoaderTest;

    @BeforeEach
    public void runBefore() {
        testList = new ArrayList<>();
        testList.add("mercure");
        testList.add("terre");
        testList.add("venus");
        solarSystemTest = new SolarSystem();
        fileCelestialDataLoaderTest = new FileCelestialDataLoader(testList);
    }


    @Test
    public void testFilePlanetDataLoader() {
        assertTrue(3 == fileCelestialDataLoaderTest.getSolarSystem().getSolarSystemSize());
        testList.add("potato");
        assertEquals("fail", fileCelestialDataLoaderTest.getPlanetaryData("potato"));

    }


}
