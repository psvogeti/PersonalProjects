import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class Frontend implements FrontendInterface {
    private BackendInterface backend;
    private Scanner scnr;
    private boolean exit = false;
    private boolean fileLoaded = false;

    public Frontend(BackendInterface backend, Scanner scnr) {
        this.backend = backend;
        this.scnr = scnr;
    }

    @Override
    public void startCommandLoop() {
        while (!exit) {
            System.out.println(
                    "______________Main Menu_______________\n" +
                    "Enter a number to select an option    \n" +
                    "--------------------------------------\n" +
                    "1. Load Data File                     \n" +
                    "2. Display Flight Statistics          \n" +
                    "3. Find Shortest Path between airports\n" +
                    "4. Exit Program                       \n" +
                    "--------------------------------------\n"
            );
            String in = scnr.next();
            switch (in) {
                case "1":
                    System.out.println("Please enter the filepath");
                    String filepath = scnr.next();
                    if (loadDataFile(filepath)) fileLoaded = true;
                    break;
                case "2":
                    displayStats();
                    break;
                case "3":
                    System.out.println("Please enter the starting airport");
                    String start = scnr.next();
                    System.out.println("Please enter the ending airport");
                    String end = scnr.next();
                    findShortestPath(start, end);
                    break;
                case "4":
                    exit();
                    break;
                default:
                    try {
                        int selection = Integer.parseInt(in);
                    } catch (Exception e) {
                        System.out.println("Please enter your selection as a number");
                        break;
                    }
                    System.out.println("Please make sure your selection is in the range of commands");
                    break;
            }
        }
    }

    @Override
    public boolean loadDataFile(String filepath) {
        if (!filepath.endsWith(".dot")) {
            System.out.println("This is not a dot file");
            return false;
        }
        try {
            File file = new File(filepath);
            if (file.length() == 0) {
                System.out.println("This is an invalid filepath");
                return false;
            }
        } catch (Exception e) {
            System.out.println("This is an invalid filepath");
            return false;
        }
        backend.readDOTFile(filepath);
        System.out.println("File loaded successfully");
        return true;
    }

    @Override
    public void displayStats() {
        if (!fileLoaded) {
            System.out.println("No data file loaded");
        } else {
            System.out.println(backend.displayFlightNetworkStatistics());
        }
    }

    @Override
    public boolean findShortestPath(String start, String end) {
        try {
            Route route = backend.getShortestTripRoute(start, end);
            List<String> airports = route.getRoute();
            List<Integer> distances = route.getDistances();
            int i = 0;
            for (Integer dist : distances) {
                System.out.println(airports.get(i) + " ---- " +dist+" miles ---> " + airports.get(i+1));
                i++;
            }
            System.out.println("Total Distance: " + route.getTotalDistance());
        } catch (Exception e) {
            System.out.println("Invalid starting or ending airport");
            return false;
        }
        return true;
    }

    @Override
    public void exit() {
        exit = true;
        scnr.close();
    }
}
