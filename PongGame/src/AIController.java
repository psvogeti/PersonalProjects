public class AIController {
    private PlayerController playerController;
    private Rectangle ball;

    public AIController(PlayerController playerController, Rectangle ball) {
        this.playerController = playerController;
        this.ball = ball;
    }


    public void update(double dt) {
        playerController.update(dt);

        if (ball.getY() < playerController.getRectangle().getY()) {
            playerController.moveUp(dt);
        } else if ((ball.getY() + ball.getHeight()) >  playerController.getRectangle().getY() + playerController.getRectangle().getHeight()) {
            playerController.moveDown(dt);
        }
    }

    /**
     * Getter for this AI Controller's player controller
     * @return this AI's player controller
     */
    public PlayerController getPlayerController() {
        return playerController;
    }

    /**
     * Setter for this AI Controller's player controller
     * @param playerController the new player controller for this AI to use
     */
    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }

    /**
     * Getter for this ball
     *
     * @return the current ball
     */
    public Rectangle getBall() {
        return ball;
    }

    /**
     * Setter for this ball
     * @param ball the new ball to be set to
     */
    public void setBall(Rectangle ball) {
        this.ball = ball;
    }



}
