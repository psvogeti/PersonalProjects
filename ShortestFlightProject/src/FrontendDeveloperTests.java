import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Scanner;

/**
 * tester class containing methods to test the fronted functionality
 */
public class FrontendDeveloperTests {

    /**
     * tests frontend's handling of invalid user input for loading a data file (wrong file
     * extension, invalid file, empty file)
     */
    @Test
    public void invalidLoadDataFile() {
        TextUITester tester = new TextUITester("1\nwrong.txt\n1\ninvalid.dot\n4");
        Scanner scnr = new Scanner(System.in);
        Frontend frontend = new Frontend(new BackendPlaceholder(), scnr);
        frontend.startCommandLoop();
        String output = tester.checkOutput();
        Assertions.assertTrue(output.contains("Please enter the filepath") &&
            output.contains("This is not a dot file") &&
            output.contains("This is an invalid filepath"), output);
        scnr.close();
    }

    /**
     * tests frontend's handling of valid input for loading a data file
     */
    @Test
    public void validLoadDataFile() {
        TextUITester tester = new TextUITester("1\nflights.dot\n4"); // do src/ if testing in intellij
        Scanner scnr = new Scanner(System.in);
        Frontend frontend = new Frontend(new BackendPlaceholder(), scnr);
        frontend.startCommandLoop();
        String output = tester.checkOutput();
        Assertions.assertTrue(output.contains("Please enter the filepath") &&
                output.contains("File loaded successfully"), output);
        scnr.close();
    }

    /**
     * tests frontend's handling of invalid user input for finding the shortest path between airports
     */
    @Test
    public void invalidShortestPath() {
        TextUITester tester = new TextUITester("2\n1\nflights.dot\n3\nAAA\nZZZ\n4");
        Scanner scnr = new Scanner(System.in);
        Frontend frontend = new Frontend(new BackendPlaceholder(), scnr);
        frontend.startCommandLoop();
        String output = tester.checkOutput();
        Assertions.assertTrue(output.contains("No data file loaded") &&
                output.contains("Invalid starting or ending airport"), output);
        scnr.close();
    }

    /**
     * tests frontend's ability to handle invalid inputs
     */
    @Test
    public void randomInputs() {
        try {
            TextUITester tester = new TextUITester("1\n230jr(#\naaa\n3\n17\nSmplingdfse\n4");
            Scanner scnr = new Scanner(System.in);
            Frontend frontend = new Frontend(new BackendPlaceholder(), scnr);
            frontend.startCommandLoop();
            String output = tester.checkOutput();
            scnr.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Assertions.fail("frontend failed to handle invalid inputs");
        }
    }

    /**
     * tests frontend's handling of invalid user input in the main menu
     */
    @Test
    public void invalidCommandLoop() {
        TextUITester tester = new TextUITester("a\n6\n4\n");
        Scanner scnr = new Scanner(System.in);
        Frontend frontend = new Frontend(new BackendPlaceholder(), scnr);
        frontend.startCommandLoop();
        String output = tester.checkOutput();
        Assertions.assertTrue(output.contains("Please enter your selection as a number") &&
                output.contains("Please make sure your selection is in the range of commands"));
        scnr.close();
    }
}
