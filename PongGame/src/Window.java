import javax.swing.*;
import java.awt.*;

/**
 * A class that draws and displays the game on a pre-defined window frame
 */
public class Window extends JFrame implements Runnable {

    Graphics2D g2D; //2D Graphics for this window
    KL keyListener = new KL(); //a keyboard listener object
    Rectangle playerOne;
    Rectangle ai;
    Rectangle ballRectangle;
    Ball ball;
    PlayerController playerController;
    AIController aiController;

    Text leftScoreText, rightScoreText;

    public boolean isRunning = true;


    /**
     * Sets up the default configurations for this Window object
     */
    public Window() {
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT); //window dimensions
        this.setTitle(Constants.SCREEN_TITLE); //the window tile
        this.setResizable(false); //avoids user configuration
        this.setVisible(true); //always show the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(keyListener); //how this window can listen to key events
        Constants.TOOLBAR_HEIGHT = this.getInsets().top; //fetches the toolbar height of the window (from very top of window)
        Constants.INSETS_BOTTOM = this.getInsets().bottom; //fetches the bottom of the window's border

        Font scoreFont = new Font("Times New Roman", Font.PLAIN, Constants.TEXT_SIZE);
        leftScoreText = new Text(0, scoreFont, Constants.TEXT_X_POS, Constants.TEXT_Y_POS);
        rightScoreText =  new Text(0, scoreFont, Constants.SCREEN_WIDTH - Constants.TEXT_Y_POS - Constants.TEXT_SIZE, Constants.TEXT_Y_POS);

        g2D = (Graphics2D) this.getGraphics(); //gets graphics as a graphics2D object

        //create the game pieces and their controllers
        playerOne = new Rectangle(Constants.HZ_PADDING, Constants.VT_PADDING, Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT, Constants.PADDLE_COLOR);
        playerController = new PlayerController(playerOne, keyListener);

        ai = new Rectangle(Constants.SCREEN_WIDTH - Constants.PADDLE_WIDTH - Constants.HZ_PADDING, Constants.VT_PADDING, Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT, Constants.PADDLE_COLOR);

        ballRectangle = new Rectangle(Constants.SCREEN_WIDTH / 2.0, Constants.SCREEN_HEIGHT / 2.0, Constants.BALL_WIDTH, Constants.BALL_HEIGHT, Constants.BALL_COLOR);
        ball = new Ball(ballRectangle, playerOne, ai, leftScoreText, rightScoreText);

        aiController = new AIController(new PlayerController(ai), ballRectangle);
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



        //continue to listen for key events and update
        playerController.update(dt);
        aiController.update(dt);
        ball.update(dt);
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

        leftScoreText.drawText(g2D);
        rightScoreText.drawText(g2D);

        //draw the game pieces
        playerOne.draw(g2D);
        ai.draw(g2D);
        ballRectangle.draw(g2D);
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
    }

    public void stop() {
        isRunning = false;
    }
}
