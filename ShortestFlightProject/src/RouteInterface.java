// --== CS400 Fall 2023 File Header Information ==--
// Name: Pranav Vogeti, Carter Powelson, Ryan Porter
// Email: vogeti@wisc.edu
// Group: G35
// TA: Alexander Peseckis
// Lecturer: Florian Heimerl
// Notes to Grader: N/A

import java.util.List;

/**
 * An interface that outlines how the data from the Backend class will be used
 * to store route information
 */
public interface RouteInterface {

    /* THE NECESSARY FIELDS
        Backend backend;
        List<String> route;
    */

    /**
     * Constructs a new route from a previously computed shortest path
     * search between two airports
     * @param route the result of a shortest path search
     * @param backend the backend this will use
     */
    /*public Route(List<String> route, Backend backend) {
        this.route = route;
        this.backend = backend;
    }*/


    /**
     * returns the route determined by the shortest path algorithm from the backend
     *
     * @return the route
     */
    public List<String> getRoute();

    /**
     * makes a list of the distance for each flight (edge weight between each pair
     * of directly connected nodes)
     * @return a list of the flight lengths
     */
    public List<Integer> getDistances();

    /**
     * returns the total distance (edge weight) on the shortest path
     * @return the route's total distance
     */
    public int getTotalDistance();
}