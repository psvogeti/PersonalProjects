// --== CS400 Fall 2023 File Header Information ==--
// Name: Pranav Vogeti, Carter Powelson, Ryan Porter
// Email: vogeti@wisc.edu
// Group: G35
// TA: Alexander Peseckis
// Lecturer: Florian Heimerl
// Notes to Grader: N/A
import java.util.List;

/**
 * An interface for the backend that takes an instance of the GraphADT as a constructor parameter
 * and exposes the following functionality to the frontend:
 * <ol>
 *   <li>Reading DOT files and retrieving the data</li>
 *   <li>Getting the shortest route from a start to a destination airport</li>
 *   <li>A string that has statistics about the dataset such as the number of airports, number of flights, and total airport network miles</li>
 * </ol>
 */
public interface BackendInterface {
    /*
     *   //anticipated reference to our airport graph data
     *   GraphADT flightNetwork;
     *   public IndividualBackendInterface(GraphADT flightNetwork) {
     *       this.flightNetwork = flightNetwork
     *   }
     */

    /**
     * Reads and stores data from DOT files only
     *
     * @param dotFilePath the user-inputted path to the DOT file
     * @return true if the file was read, false otherwise
     */
    public boolean readDOTFile(String dotFilePath);


    /**
     * Gets the shortest possible path between a starting airport site and the destination site
     *
     * @param departureSite the departing airport as an object to start at as a String
     * @param arrivalSite   the destination airport as an object to end at as a String
     * @return the shortest possible path between the departing airport and the destination airport
     */
    public Route getShortestTripRoute(String departureSite, String arrivalSite);


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
     *  <p>************************* </p>
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
    public String displayFlightNetworkStatistics();
}
