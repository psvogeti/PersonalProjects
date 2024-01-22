import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class runs the Shopping List program and works with user generated input
 * to execute the appropriate tasks
 * @author 715st
 *
 */
public class Main {

	/**
	 * Runs the Shopping List program
	 * @param args unused
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        boolean applicationOn = true;

        String welcome = "Welcome to the Song Searcher App! What would you like to do?";
        String csv = "1) Create a new Shopping List";
        String exit = "2) Exit the Application";

        String mainMenuStr = welcome + "\n" + csv + "\n" + exit;

        while (applicationOn) {
            System.out.println(mainMenuStr);
            if (scanner.hasNextInt()) {
            	int userRequest = scanner.nextInt();
                if (userRequest >= 1 && userRequest < 3) {
                    switch (userRequest) {
                        case 1:
                            System.out.println("Enter the name of this new Shopping List");
                            if (scanner.hasNextLine()) {
                                String shopListName = scanner.nextLine();
                                ShoppingList shoppingList = new ShoppingList("shopListName");
                                System.out.println("You created a new shopping list with the name:" + "\n" + shopListName);
                            }
                            break;
                        case 2:
                            scanner.close();
                            applicationOn = false;
                            break;
                    }
                    
                    applicationOn = false;
                } else {
                    throw new InputMismatchException("Input must be an integer from 1-4");
                }
            }
        }

    }

}