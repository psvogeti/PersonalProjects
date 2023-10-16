import javax.swing.*;
import java.awt.*;

/**
 * A class that draws and displays the game on a pre-defined window frame
 */
public class MainMenu extends JFrame implements Runnable {

    Graphics2D g2D; //2D Graphics for this window
    KL keyListener = new KL(); //a keyboard listener object
    ML mouseListener = new ML();
    Text startGameButton;
    Text exitGameButton;
    Text pongTitle;
    boolean isRunning = true;

    /**
     * Sets up the default configurations for this Window object
     */
    public MainMenu() {
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT); //window dimensions
        this.setTitle(Constants.SCREEN_TITLE); //the window tile
        this.setResizable(false); //avoids user configuration
        this.setVisible(true); //always show the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(keyListener); //how this window can listen to key events
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);

        startGameButton = new Text("Start Game", new Font("Times New Roman", Font.PLAIN, 40), Constants.SCREEN_WIDTH / 2.0 - 90.0, Constants.SCREEN_HEIGHT / 2.0, Constants.TEXT_COLOR);
        exitGameButton = new Text("Exit Game", new Font("Times New Roman", Font.PLAIN, 40), Constants.SCREEN_WIDTH / 2.0 - 80.64, Constants.SCREEN_HEIGHT / 2.0 + 120, Constants.TEXT_COLOR);
        pongTitle = new Text("pong", new Font("Times New Roman", Font.ROMAN_BASELINE, 60), Constants.SCREEN_WIDTH / 2.0 - 63.0, Constants.SCREEN_HEIGHT / 2.0 - 100, Constants.TEXT_COLOR);

        g2D = (Graphics2D) getGraphics();
    }

    /**
     * Responsible for continuously running the game events and
     * updating status of each object in the game
     *
     * @param dt the delta time to use to continuously run this method
     */
    public void update(double dt) {
        //Create an image in the background that is the window
        Image doubleBufferImage = createImage(getWidth(), getHeight());
        //get the graphics of this copied image
        Graphics doubleBufferGraphics = doubleBufferImage.getGraphics();

        //draw this new image to the user
        this.draw(doubleBufferGraphics);
        g2D.drawImage(doubleBufferImage, 0, 0, this);

        if (mouseOverButton(startGameButton)) {
            startGameButton.setTextColor(new Color(0x64646E));
            if (mouseListener.isMousePressed()) {
                Main.changeState(1);
            }
        } else if (mouseOverButton(exitGameButton)) {
            exitGameButton.setTextColor(new Color(0x64646E));
            if (mouseListener.isMousePressed()) {
                Main.changeState(2);
            }
        } else {
            startGameButton.setTextColor(Constants.TEXT_COLOR);
            exitGameButton.setTextColor(Constants.TEXT_COLOR);
        }




    }

    /**
     * Hosts the drawing events to be projected onto the user window
     *
     * @param graphics the graphics that will be used to draw each object
     */
    public void draw(Graphics graphics) {
        Graphics2D g2D = (Graphics2D) graphics;

        //Set the window background color and paint it through the dimensions of the window
        g2D.setColor(Constants.BACKGROUND_COLOR);
        g2D.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        startGameButton.drawText(g2D);
        exitGameButton.drawText(g2D);
        pongTitle.drawText(g2D);
    }

    /**
     * Method for printing out frame rate and callback time
     * Helps illustrate the game loop
     *
     * @param dt the change in time between each call of this method
     */
    public void printFPS(double dt) {
        System.out.println("" + dt + " seconds passed since the last frame"); //prints dt in seconds
        System.out.println((1 / dt) + " fps"); //prints fps
    }


    /**
     * Runs the window constantly everytime this method is called
     */
    public void run() {
        double lastFrameTime = 0.0;
        while (isRunning) {
            double time = Time.getTimeSeconds();
            double deltaTime = time - lastFrameTime;
            lastFrameTime = time;

            //printFPS(deltaTime); //prints the game loop
            update(deltaTime); //this will run our window where the game is stored
        }
        this.dispose();
        return;

    }

    public void stop() {
        isRunning = false;
    }

    private boolean mouseOverButton(Text textButton) {
        if (mouseListener.getMouseX() > textButton.getX() && mouseListener.getMouseX() < textButton.getX() + textButton.getTextWidth()) {
            if (mouseListener.getMouseY() > textButton.getY() - textButton.getTextHeight() / 2.0 && mouseListener.getMouseY() < textButton.getY() + textButton.getTextHeight() / 2.0) {
                return true;
            }
        }
        return false;
    }
}