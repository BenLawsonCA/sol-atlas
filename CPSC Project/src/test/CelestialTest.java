import model.Celestial;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CelestialTest {

    private Celestial testCelestial1;
    private String testApiData;


    @BeforeEach
    public void runBefore() {
        testApiData = "{\"id\":\"terre\",\"name\":\"La Terre\",\"englishName\":\"Earth\",\"isPlanet\":true,\""
                + "moons\":[{\"moon\":\"La Lune\",\"rel\":\"https://api.le-systeme-solaire.net/rest/bodies/lune\"}],"
                + "\"semimajorAxis\":149598262,\"perihelion\":147095000,\"aphelion\":152100000,\"eccentricity\""
                + ":0.01670,\"inclination\":0,\"mass\":{\"massValue\":5.97237, \"massExponent\":24},\"vol\":{\""
                + "volValue\":1.08321, \"volExponent\":12},\"density\":5.51360,\"gravity\":9.80000,\"escape\""
                + ":11190.00000,\"meanRadius\":6371.00840,\"equaRadius\":6378.13660,\"polarRadius\":6356.80000,"
                + "\"flattening\":0.00335,\"dimension\":\"\",\"sideralOrbit\":365.25600,\"sideralRotation\""
                + ":23.93450,\"aroundPlanet\":null,\"discoveredBy\":\"\",\"discoveryDate\":\"\",\"alternativeName"
                + "\":\"\"}";
        testCelestial1 = new Celestial(testApiData);
    }

    @Test
    public void testPlanet() {
        testCelestial1 = new Celestial(testApiData);
        assertEquals("Earth", testCelestial1.getPlanetName());
        assertEquals(24, testCelestial1.getMassExponent());
        assertFalse(testCelestial1.getPlanetName().equals("Moon"));
    }

    @Test
    public void testDisplayCelestialData() {
        assertEquals("Name:                       Earth\n"
                + "Density:                    5.5136 g/cm^3\n"
                + "Mass:                        5.9723703E24 kg\n"
                + "Orbital Period:         365.256 days\n"
                + "Radius:                     6371 kilometers\n"
                + "Semi-Major Axis:     1.49598256E8 kilometers\n"
                + "Rotational Period:   23.9345 hours\n",
                testCelestial1.displayCelestialData());
    }


    @Test
    public void testGetPlanetName() {
        assertEquals("Earth", testCelestial1.getPlanetName());
        assertFalse(testCelestial1.getPlanetName().equals("LaLaLand"));
    }

    @Test
    public void testRadiusKm() {
        assertEquals(6371, testCelestial1.getRadiusKm());
        assertFalse(testCelestial1.getRadiusKm() == 300);
    }

    @Test
    public void testGetMassExponent() {
        assertEquals(24, testCelestial1.getMassExponent());
        assertFalse(testCelestial1.getMassExponent() == 3000);
    }

    @Test
    public void testGetMassValue() {
        assertEquals(5.972370147705078, testCelestial1.getMassValue());
        assertFalse(testCelestial1.getMassValue() == 6);
    }

    @Test
    public void testGetSemiMajorAxis() {
        assertEquals(1.49598256E8, testCelestial1.getSemiMajorAxis());
        assertFalse(1.5 == testCelestial1.getSemiMajorAxis());
    }

    @Test
    public void testGetOrbitalPeriod() {
        assertEquals(365.2560119628906, testCelestial1.getOrbitalPeriod());
        assertFalse(testCelestial1.getOrbitalPeriod() == 365);
    }

    @Test
    public void testGetRotationalPeriod() {
        assertEquals(23.934499740600586, testCelestial1.getRotationalPeriod());
        assertFalse(testCelestial1.getRotationalPeriod() == 24);
    }

    @Test
    public void testGetDensity() {
        assertEquals(5.513599872589111, testCelestial1.getDensity());
        assertFalse(testCelestial1.getDensity() == 5.5);

    }

    @Test
    public void testGetMassFinal(){
        assertEquals(5.972370319232664E24, testCelestial1.getMassFinal());
    }
}