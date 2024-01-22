// --== CS400 File Header Information ==--
// Name: Pranav Vogeti
// Email: vogeti@wisc.edu
// Group and Team: G35
// Group TA: Alexander Peseckis
// Lecturer: Florian Heimerl

import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This Backend class houses the necessary functions for the FlightRouter
 * application to work. It contains a function to read in a valid DOT file,
 * a function to get the shortest route between two airports,
 * and a summary statistic display for the flight network this application
 * is based off of
 */
public class Backend implements BackendInterface {

    DijkstraGraph flightNetwork;
    private int totalMiles = 0;

    private Route shortestRoute;

    /**
     * Creates a backend object that can retrieve information
     * about our graph of flights as primitive data
     * @param flightNetwork the graph that has airports as nodes and miles as edge weights
     */
    public Backend(DijkstraGraph flightNetwork) {
        this.flightNetwork = flightNetwork;
    }

    /**
     * Reads and stores data from DOT files only into a
     * flight network operated by a Dijkstra Graph
     *
     * @param dotFilePath the user-inputted path to the DOT file
     * @return true if the file was read, false otherwise
     */
    @Override
    public boolean readDOTFile(String dotFilePath) {
        //the scanner object that will read in this file
        Scanner fileReader;

        File dotFile = new File(dotFilePath);

        try {
            //initialized when a valid file is read
            fileReader = new Scanner(dotFile);
        } catch (Exception e) {
            //otherwise we can not do anything
            return false;
        }

        //regex patterns to look for
        Pattern airportsPattern = Pattern.compile("\\\"\\w\\w\\w\\\"\\s\\--\\s\\\"\\w\\w\\w");
        Pattern milesPattern = Pattern.compile("\\d+");

        //go through the complete file
        while (fileReader.hasNextLine()) {
            //save the line that we just read
            String lineInFile = fileReader.nextLine();
            Matcher airportsMatcher = airportsPattern.matcher(lineInFile);
            Matcher milesMatcher = milesPattern.matcher(lineInFile);

            boolean foundAirports = airportsMatcher.find();
            boolean foundRouteMiles = milesMatcher.find();

            //for the matches we have
            if (foundAirports && foundRouteMiles) {
                //string: departure (DPT) + arrival (ARV) airports = "DPT" -- "ARV
                String airports = airportsMatcher.group();
                //DPT
                String departAirport = airports.substring(1, 4);
                //ARV
                String arrivalAirport = airports.substring(10);
                //get the route miles as a string that we extract the integer from
                int routeMiles = Integer.parseInt(milesMatcher.group());
                //add each route miles to the running total
                totalMiles += routeMiles;
                //add these data to the graph
                flightNetwork.insertNode(departAirport);
                flightNetwork.insertNode(arrivalAirport);
                //add the round trip edges
                flightNetwork.insertEdge(departAirport, arrivalAirport, routeMiles);
                flightNetwork.insertEdge(arrivalAirport, departAirport, routeMiles);
            }
        }

        return true;
    }

    /**
     * Gets the shortest possible path between a starting airport site and the destination site
     *
     * @param departureSite the departing airport as an object to start at as a String
     * @param arrivalSite   the destination airport as an object to end at as a String
     * @return the shortest possible path between the departing airport and the destination airport
     */
    @Override
    public Route getShortestTripRoute(String departureSite, String arrivalSite) {
        List<String> routePath = flightNetwork.shortestPathData(departureSite, arrivalSite);
        shortestRoute = new Route(routePath, this);
        return shortestRoute;
    }

    /**
     * Displays statistics about our flight network including
     * <ol>
     *  <li>Number of Airports</li>
     *  <li>Number of Flights</li>
     *  <li>Total Flight Network Miles</li>
     * </ol>
     *
     * The Display will look like this
     * <p>
     *  <p>*************************</p>
     *  <p>FLIGHT NETWORK STATISTICS</p>
     *  <p>-------------------------</p>
     *  <p>Number of Airports: ###</p>
     *  <p>Number of Flights: ###</p>
     *  <p>Total Network Miles: ###</p>
     *  <p>-------------------------</p>
     *  <p>*************************</p>
     * </p>
     * @return the statistics display as a String
     */

    public int getTotalMiles() {
        return totalMiles;
    }
    @Override
    public String displayFlightNetworkStatistics() {
        //line markers
        String asteriskLine = "*************************" + "\n";
        String title = "FLIGHT NETWORK STATISTICS" + "\n";
        String hyphenLine = "-------------------------" + "\n";

        //statistics body building
        int numAirports = flightNetwork.getNodeCount();
        int numFlights = flightNetwork.getEdgeCount() / 2;
        int totalNetworkMiles = getTotalMiles();
        String numberOfAirports = "Number of Airports: " + numAirports + "\n";
        String numberOfFlights = "Number of Flights: " + numFlights + "\n";
        String totalMiles = "Total Network Miles: " + totalNetworkMiles + "\n";
        String statisticsBody = numberOfAirports + numberOfFlights + totalMiles;


        //final format
        String statistics =
                asteriskLine + title + hyphenLine + statisticsBody + hyphenLine + asteriskLine;

        return statistics;
    }


    /**
     * This will run the flight router program by using the
     * added Frontend and the Backend
     * @param args unused
     */
    public static void main(String[] args) {
        //The objects needed for the program
        DijkstraGraph airportNetwork = new DijkstraGraph(new PlaceholderMap());
        Backend backend = new Backend(airportNetwork);
        Scanner scanner = new Scanner(System.in);
        Frontend frontend = new Frontend(backend, scanner);

        //runs the program
        frontend.startCommandLoop();
    }
}
