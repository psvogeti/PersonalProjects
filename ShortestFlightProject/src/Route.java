import java.util.LinkedList;
import java.util.List;

/**
 * This Route class holds functionalites to get the complete shortest route from
 * the starting airport to the final airport, and get the intermediate distances along the way, as
 * well as the total distance for the route
 */
public class Route implements RouteInterface{

    Backend backend;
    List<String> route;

    /**
     * Constructs a new route from a previously computed shortest path
     * search between two airports
     * @param route the result of a shortest path search
     * @param backend the backend this will use
     */
    public Route(List<String> route, Backend backend) {
        this.route = route;
        this.backend = backend;
    }

    /**
     * returns the route by the shortest path algorithm from the backend
     * @return the route
     */
    @Override
    public List<String> getRoute() {
        return route;
    }

    /**
     * makes a list of the distance for each flight (edge weight between each pair
     * of directly connected nodes)
     * @return a list of the flight lengths
     */
    @Override
    public List<Integer> getDistances() {
        //this list will hold the airports we visited along our shortest route
        List<String> airports = getRoute();
        //this will hold the intermediate distances (miles) along our shortest route
        List<Integer> milesAlongRoute = new LinkedList<>();

        //use the list of airports and calculate the shortest path cost between stops
        for (int i = 0; i < airports.size(); i++) {
            String departSite = airports.get(i);
            String arrivalSite = airports.get(airports.size() - 1);
            if (!departSite.equals(arrivalSite)) {
                String nextDestination = airports.get(i + 1);
                double intermediateCost = backend.flightNetwork.shortestPathCost(departSite, nextDestination);
                milesAlongRoute.add((int) intermediateCost);
                continue;
            }
            break;
        }

        //return the list of intermediate route miles
        return milesAlongRoute;
    }

    /**
     * returns the total distance (edge weight) on the shortest path
     * @return the route's total distance
     */
    @Override
    public int getTotalDistance() {
        String departureSite = route.get(0);
        String arrivalSite = route.get(route.size() - 1);

        return (int) backend.flightNetwork.shortestPathCost(departureSite, arrivalSite);
    }
}
