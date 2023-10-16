import java.awt.event.KeyEvent;

/**
 * A class designed to host the events to be executed
 * by players as Rectangle objects under key listening
 */
public class PlayerController {
    private Rectangle rectangle;
    private KL keyListener;

    public PlayerController(Rectangle rectangle, KL keyListener) {
        this.rectangle = rectangle;
        this.keyListener = keyListener;
    }


    public PlayerController(Rectangle rectangle) {
        this.rectangle = rectangle;
        this.keyListener = null;
    }

    /**
     * Getter for this player controller's key listener
     *
     * @return this player controller's key listener
     */
    public KL getKeyListener() {
        return keyListener;
    }

    /**
     * Setter for this player controller's key listener
     *
     * @param keyListener the new keyListener for this player controller to use
     */
    public void setKeyListener(KL keyListener) {
        this.keyListener = keyListener;
    }

    /**
     * Getter for the current rectangle this player controller is using
     *
     * @return the current rectangle this player controller is using
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * Setter for the current rectangle this player controller is using
     *
     * @param rectangle the new rectangle that this playerController will use
     */
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    /**
     * Harbors the key listening functionalities that this
     * player controller's rectangle responds to
     *
     * @param dt the change in time that will be responsible to run this in sync with the game loop
     */
    public void update(double dt) {

        if (keyListener != null) {
            if (keyListener.isKeyPressed(KeyEvent.VK_DOWN)) {
                moveDown(dt);
            } else if (keyListener.isKeyPressed(KeyEvent.VK_UP)) {
                moveUp(dt);
            }
        }
    }

    public void moveDown(double dt) {
        double rectY = rectangle.getY();
        double rectHeight = rectangle.getHeight();

        if ((rectY + (Constants.PADDLE_SPEED * dt)) + rectHeight < Constants.SCREEN_HEIGHT - Constants.INSETS_BOTTOM) {
            rectY += Constants.PADDLE_SPEED * dt;
            rectangle.setY(rectY);
        }

    }

    public void moveUp(double dt) {
        double rectY = rectangle.getY();

        if (rectY - (Constants.PADDLE_SPEED * dt) > Constants.TOOLBAR_HEIGHT) {
            rectY -= Constants.PADDLE_SPEED * dt;
            rectangle.setY(rectY);
        }
    }


}
