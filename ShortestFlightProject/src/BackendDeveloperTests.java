// --== CS400 Fall 2023 File Header Information ==--
// Name: Pranav Vogeti
// Email: vogeti@wisc.edu
// Group: G35
// TA: Alexander Peseckis
// Lecturer: Florian Heimerl
// Notes to Grader: N/A

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * A class to hold all the necessary tests that expose Backend functionality for P2
 */
public class BackendDeveloperTests {

    //use on local machine
    String validFilePath = "src/flights.dot";
    //use on VM (change as necessary)
    //String validFilePath = "/home/vogeti/p2/flights.dot";

    /**
     * A JUnit test that tests whether a file was properly read in or not,
     * specifically, the flights.dot file.
     *
     * Passes if the file was read in, false otherwise
     */
    @Test
    public void testValidRead() {
        DijkstraGraph airportNetwork = new DijkstraGraph(new PlaceholderMap());
        Backend backend = new Backend(airportNetwork);

        //try to read in the file
        try {
            boolean expectedRead = true;
            boolean actualRead = backend.readDOTFile(validFilePath);

            //ensure that we did read the file
            Assertions.assertEquals(expectedRead, actualRead);
        } catch (Exception e) {
            //should not catch any exceptions
            Assertions.fail(e.getMessage());
        }
    }

    /**
     * A JUnit test that tests when an invalid file path is given.
     * Specifically, the flights.dot file has an incorrect path
     * which prevents our file reader from reading in the data
     *
     * Passes if we correctly fail to read the file, false otherwise
     */
    @Test
    public void testInvalidRead() {
        DijkstraGraph airportNetwork = new DijkstraGraph(new PlaceholderMap());
        Backend backend = new Backend(airportNetwork);

        //should not read in anything
        boolean expectedRead = false;
        boolean actualRead = backend.readDOTFile("F:/invalid/path/file");

        Assertions.assertEquals(expectedRead, actualRead);
    }

    /**
     * A Junit test that will test the retrieval of flight network information given
     * a valid file is read.
     *
     * This test looks at confirming certain airports in our graph, confirming certain distances,
     * and confirming non-existent airports in our graph
     */
    @Test
    public void testGraphDataAfterRead() {
        DijkstraGraph airportNetwork = new DijkstraGraph(new PlaceholderMap());
        Backend backend = new Backend(airportNetwork);

        try {
            //read the file
            backend.readDOTFile(validFilePath);

            //confirm random graph data (different digit lengths)
            double expectedDistance1 = 866; //3-digit
            double actualDistance1 = airportNetwork.getEdge("LAS", "SEA").doubleValue();
            Assertions.assertEquals(expectedDistance1, actualDistance1);

            double expectedDistance2 = 42; //2-digit
            double actualDistance2 = airportNetwork.getEdge("FLL", "PBI").doubleValue();
            Assertions.assertEquals(expectedDistance2, actualDistance2);

            double expectedDistance3 = 2400; //4-digit
            double actualDistance3 = airportNetwork.getEdge("RDU", "SFO").doubleValue();
            Assertions.assertEquals(expectedDistance3, actualDistance3);

            //confirm non-existent airports
            Assertions.assertThrows(
                    NoSuchElementException.class,
                    () -> backend.getShortestTripRoute("XXX", "ZZZ"));

        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }


    /**
     * A Junit test that will test the retrieval of flight network information given
     * a valid file is read.
     *
     * This test looks at the same routes as testGraphDataAfterRead() just for the opposite direction
     */
    @Test
    public void testReverseRoutes() {
        DijkstraGraph airportNetwork = new DijkstraGraph(new PlaceholderMap());
        Backend backend = new Backend(airportNetwork);

        try {
            //read the file
            backend.readDOTFile(validFilePath);

            //confirm random graph data (different digit lengths)
            double expectedDistance1 = 866; //3-digit
            double actualDistance1 = airportNetwork.getEdge("SEA", "LAS").doubleValue();
            Assertions.assertEquals(expectedDistance1, actualDistance1);

            double expectedDistance2 = 42; //2-digit
            double actualDistance2 = airportNetwork.getEdge("PBI", "FLL").doubleValue();
            Assertions.assertEquals(expectedDistance2, actualDistance2);

            double expectedDistance3 = 2400;
            double actualDistance3 = airportNetwork.getEdge("SFO", "RDU").doubleValue();
            Assertions.assertEquals(expectedDistance3, actualDistance3);

            //confirm non-existent airports
            Assertions.assertThrows(
                    NoSuchElementException.class,
                    () -> backend.getShortestTripRoute("XXX", "ZZZ"));

        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    /**
     * A JUnit test designed to retrieve the shortest path
     * from OAK to SJC from this big expansive flight network.
     * <p>
     * The shortest path is OAK -> (30) SJC
     * </p>
     *
     * Passes if the shortest route list is confirmed, false otherwise
     */
    @Test
    public void testShortestTripRoute() {
        DijkstraGraph airportNetwork = new DijkstraGraph(new PlaceholderMap());
        Backend backend = new Backend(airportNetwork);

        backend.readDOTFile(validFilePath);


        String expectedShortestPathList = "[OAK, SJC]";
        String actualShortestPathList = backend.getShortestTripRoute("OAK", "SJC").getRoute().toString();

        //confirm the route list as a string
        Assertions.assertEquals(expectedShortestPathList, actualShortestPathList);
    }

    /**
     * A JUnit test designed to retrieve the shortest path
     * from SJC to OAK from this big expansive flight network.
     * <p>
     * The shortest path is SJC -> (30) OAK
     * </p>
     *
     * This test is the reverse of testShortestTripRoute()
     * Passes if the shortest route list is confirmed, false otherwise
     */
    @Test
    public void testReverseShortestTripRoute() {
        DijkstraGraph airportNetwork = new DijkstraGraph(new PlaceholderMap());
        Backend backend = new Backend(airportNetwork);

        backend.readDOTFile(validFilePath);


        String expectedShortestPathList = "[SJC, OAK]";
        String actualShortestPathList = backend.getShortestTripRoute("SJC", "OAK").getRoute().toString();

        //confirm the route list as a string
        Assertions.assertEquals(expectedShortestPathList, actualShortestPathList);
    }

    /**
     * A JUnit test to test that the summary statistics
     * of this flight network are properly displayed and
     * formatted.
     *
     * Passes if the display thoroughly matches, false otherwise
     */
    @Test
    public void testDisplayStatistics() {
        DijkstraGraph airportNetwork = new DijkstraGraph(new PlaceholderMap());
        Backend backend = new Backend(airportNetwork);

        backend.readDOTFile(validFilePath);
        int numberOfAirports = airportNetwork.getNodeCount();
        int numberOfFlights = airportNetwork.getEdgeCount() / 2;
        int totalMiles = backend.getTotalMiles();

        String expectedDisplay =
                "*************************\n" +
                "FLIGHT NETWORK STATISTICS\n" +
                "-------------------------\n" +
                "Number of Airports: " + numberOfAirports + "\n" +
                "Number of Flights: " + numberOfFlights + "\n" +
                "Total Network Miles: " + totalMiles + "\n" +
                "-------------------------\n" +
                "*************************\n";

        String actualDisplay = backend.displayFlightNetworkStatistics();
        Assertions.assertEquals(expectedDisplay, actualDisplay);
    }

    /**
     * A JUnit test to test the getRoute() method of the Route class
     * and is tested on the shortest path from LGA -> SLC
     *
     * Passes if the route list matches, false otherwise
     */
    @Test
    public void testGetRoute() {
        DijkstraGraph airportNetwork = new DijkstraGraph(new PlaceholderMap());
        Backend backend = new Backend(airportNetwork);
        backend.readDOTFile(validFilePath);

        //the route from LGA to SLC
        String start = "LGA";
        String end = "SLC";

        Route route = backend.getShortestTripRoute(start, end);

        String expectedRoute = "[LGA, DTW, SLC]";
        String actualRoute = route.getRoute().toString();

        //confirm shortest path equality
        Assertions.assertEquals(expectedRoute, actualRoute, route.getTotalDistance() + "");
    }

    /**
     * A JUnit test to test the reverse of getRoute() method of the Route class
     * and is tested on the shortest path from SLC -> LGA
     *
     * Passes if the route list matches, false otherwise
     */
    @Test
    public void testReverseGetRoute() {
        DijkstraGraph airportNetwork = new DijkstraGraph(new PlaceholderMap());
        Backend backend = new Backend(airportNetwork);
        backend.readDOTFile(validFilePath);

        //the route from SLC to LGA
        String start = "SLC";
        String end = "LGA";

        Route route = backend.getShortestTripRoute(start, end);

        String expectedRoute = "[SLC, DTW, LGA]";
        String actualRoute = route.getRoute().toString();

        //confirm shortest path equality
        Assertions.assertEquals(expectedRoute, actualRoute);
    }


    /**
     * A JUnit test to test the getDistances() method of the Route class
     * and is tested on the shortest path between LGA -> SLC via DTW
     * where LGA -> (501) DTW-> (1481) -> SLC
     *
     * Passes if the intermediate distances match, false otherwise
     */
    @Test
    public void testGetDistances() {
        DijkstraGraph airportNetwork = new DijkstraGraph(new PlaceholderMap());
        Backend backend = new Backend(airportNetwork);
        backend.readDOTFile(validFilePath);

        String start = "LGA";
        String end = "SLC";

        Route route = backend.getShortestTripRoute(start, end);

        String expectedRouteDistances = "[501, 1481]";
        String actualRouteDistances = route.getDistances().toString();

        //confirm intermediate distances equality
        Assertions.assertEquals(expectedRouteDistances, actualRouteDistances);
    }


    /**
     * A JUnit test to test the getTotalDistance() method of the Route class
     * and is tested on the shortest path between LGA -> SLC via DTW
     * where LGA -> (501) DTW-> (1481) -> SLC
     * which has a total cost of 1982 miles
     *
     * Passes if the total distance matches, false otherwise
     */
    @Test
    public void testGetTotalDistance() {
        DijkstraGraph airportNetwork = new DijkstraGraph(new PlaceholderMap());
        Backend backend = new Backend(airportNetwork);
        backend.readDOTFile(validFilePath);

        String start = "LGA";
        String end = "SLC";

        Route route = backend.getShortestTripRoute(start, end);

        int expectedTotal = 1982;
        int actualTotal = route.getTotalDistance();

        //confirm total miles equality
        Assertions.assertEquals(expectedTotal, actualTotal);
    }


    /**
     * A Junit test that tests the integration aspect of loading in a valid
     * file and an invalid file
     *
     * Passes if all tests pass, false otherwise
     */
    @Test
    public void integrationTestReadFile() {
        //Define the objects
        DijkstraGraph airportNetwork = new DijkstraGraph(new PlaceholderMap());
        Backend backend = new Backend(airportNetwork);
        Scanner scanner = new Scanner(System.in);
        Frontend frontend = new Frontend(backend, scanner);

        //Test 1: Test loading an invalid proper data file
        try {
            boolean expectedRead = false;
            boolean actualRead = frontend.loadDataFile("src/invalid.dot");
            Assertions.assertEquals(expectedRead, actualRead, "Should not read anything!");
        } catch (Exception e) {
            //Should not catch any exceptions
            Assertions.fail(e.getMessage());
        }


        //Test 2: Test loading a proper data file
        try {
            boolean expectedRead = true;
            boolean actualRead = frontend.loadDataFile(validFilePath);
            Assertions.assertEquals(expectedRead, actualRead, "Valid file not read!");
        } catch (Exception e) {
            //Should not catch any exceptions
            Assertions.fail(e.getMessage());
        }
    }

    /**
     * A JUnit test that looks at the integration of the frontend's call to the backend
     * to find the shortest path between LGA and SLC
     *
     * Passes if the shortest path was called and found, false otherwise
     */
    @Test
    public void integrationTestFoundPath() {
        //Define the objects
        DijkstraGraph airportNetwork = new DijkstraGraph(new PlaceholderMap());
        Backend backend = new Backend(airportNetwork);
        Scanner scanner = new Scanner(System.in);
        Frontend frontend = new Frontend(backend, scanner);

        //load through the frontend
        frontend.loadDataFile(validFilePath);

        //the start and end path
        String start = "LGA";
        String end = "SLC";

        //see if we found the path
        boolean expectedFound = true;
        boolean actualFound = frontend.findShortestPath(start, end);
        Assertions.assertEquals(expectedFound, actualFound);
    }


    /**
     * A JUnit test that looks at the integration of the frontend's call to the backend
     * to find the shortest path between LGA and SLC
     *
     * Passes if the shortest path was called and found, false otherwise
     */
    @Test
    public void testFrontendGetRoute() {
        //Define the objects
        DijkstraGraph airportNetwork = new DijkstraGraph(new PlaceholderMap());
        Backend backend = new Backend(airportNetwork);
        Scanner scanner = new Scanner(System.in);
        Frontend frontend = new Frontend(backend, scanner);

        //load through the frontend
        frontend.loadDataFile(validFilePath);

        //the start and end path
        String start = "ATL";
        String end = "SFO";

        //see if we found the path
        boolean expectedFound = true;
        boolean actualFound = frontend.findShortestPath(start, end);
        Assertions.assertEquals(expectedFound, actualFound);
    }
}
