public interface FrontendInterface {

	/*
	//constructor for Frontend class which accepts reference to backend
	public Frontend(Backend backend, Scanner scnr);
	*/

    /**
     * starts the command loop and handles functionality for the main menu
     */
    public void startCommandLoop();

    /**
     * sends the data file to the backend if the filepath is valid
     * @param filepath the path to the data file
     * @return True if the file path is valid and the data is loaded, false otherwise
     */
    public boolean loadDataFile(String filepath);

    /**
     * displays statistics about the dataset like number of airports, number of flights, and
     * number of miles
     */
    public void displayStats();

    /**
     * finds the shortest path between a starting and ending airports, specified by the user
     * prints the shortest route, including all airpots on the path, distance for each flight,
     * and the total distance in miles
     * @param start the name of the starting airport
     * @param end the name of the ending airport
     * @return True if a path exists, false otherwise
     */
    public boolean findShortestPath(String start, String end);

    /**
     * handles exiting the command loop
     */
    public void exit();

}
