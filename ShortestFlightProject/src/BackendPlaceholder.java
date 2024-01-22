public class BackendPlaceholder implements BackendInterface {

    public BackendPlaceholder() {

    }

    @Override
    public boolean readDOTFile(String dotFilePath) {
        return false;
    }

    @Override
    public Route getShortestTripRoute(String departureSite, String arrivalSite) {
        return null;
    }

    /**
     * Displays statistics about our flight network including
     * <ol>
     *  <li>Number of Airports</li>
     *  <li>Number of Flights</li>
     *  <li>Total Flight Network Miles</li>
     * </ol>
     *
     * @return the statistics display as a String
     */
    @Override
    public String displayFlightNetworkStatistics() {
        return "";
    }
}
