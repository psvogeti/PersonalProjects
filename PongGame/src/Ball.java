/**
 * A class dedicated for the ball's functionalities
 */
public class Ball {
    private final Rectangle ballRectangle; //this ball as a rectangle
    private final Rectangle leftPaddle; //the left paddle in this pong game
    private final Rectangle rightPaddle; //the right paddle in this pong game

    private double velY = 10.0; // the y-velocity of this ball
    private double velX = -1.0 * Constants.BALL_SPEED; //the x-velocity of this ball (-1 to go to the player)

    private Text leftScoreText, rightScoreText;


    /**
     * Constructs a new Ball object
     *
     * @param ballRectangle the rectangle that this ball uses
     * @param leftPaddle    the left paddle of this pong game
     * @param rightPaddle   the right paddle of this pong game
     */
    public Ball(Rectangle ballRectangle, Rectangle leftPaddle, Rectangle rightPaddle, Text leftScoreText, Text rightScoreText) {
        this.ballRectangle = ballRectangle;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
        this.leftScoreText = leftScoreText;
        this.rightScoreText = rightScoreText;
    }

    /**
     * A method to constantly update the ball's position and velocity
     * with respect to the left and right paddle's and their collision
     * behavior
     *
     * @param dt the frame rate at which this method is called
     */
    public void update(double dt) {
        double ballX = ballRectangle.getX();
        double ballY = ballRectangle.getY();

        if (velX < 0) { //ball is moving to the left
            if (isBallWithinLeftPaddle()) {
                calculateNewVelocity(leftPaddle);
            }
        } else if (velX > 0) { //ball is moving to the right
            if (isBallWithinRightPaddle()) {
                calculateNewVelocity(rightPaddle);
            }
        }

        if (velY > 0) { //moving towards top
            if (isBallPastScreenTop()) {
                velY *= -1; //bounce it
            }
        } else if (velY < 0) { //moving towards bottom
            if (isBallPastScreenBottom()) {
                velY *= -1; //bounce it
            }
        }

        // position = position + velocity
        // velocity = velocity + acceleration
        //we will move the ball with respect to how fast it is moving each frame
        ballX += velX * dt;
        ballY += velY * dt;

        ballRectangle.setX(ballX);
        ballRectangle.setY(ballY);

        if (isBallPastLeftPaddle()) {
            int rightScore = Integer.parseInt(rightScoreText.getText());
            rightScore++;
            rightScoreText.setText(rightScore + "");
            ballRectangle.setX(Constants.SCREEN_WIDTH / 2.0);
            ballRectangle.setY(Constants.SCREEN_HEIGHT / 2.0);
            velX = Constants.BALL_SPEED;
            velY = 10.0;

            if (rightScore == Constants.WIN_SCORE) {
                Main.changeState(2);
            }
        } else if (isBallPastRightPaddle()) {
            int leftScore = Integer.parseInt(leftScoreText.getText());
            leftScore++;
            leftScoreText.setText(leftScore + "");
            ballRectangle.setX(Constants.SCREEN_WIDTH / 2.0);
            ballRectangle.setY(Constants.SCREEN_HEIGHT / 2.0);
            velX = Constants.BALL_SPEED * -1;
            velY = 10.0;

            if (leftScore == Constants.WIN_SCORE) {
                Main.changeState(2);
            }
        }

    }


    /**
     * A private helper method to check if this ball is
     * within the x and y constraints of the left paddle
     *
     * @return whether this ball is within the left paddle
     */
    private boolean isBallWithinLeftPaddle() {
        double ballX = ballRectangle.getX();
        double ballY = ballRectangle.getY();
        double ballWidth = ballRectangle.getWidth();

        double leftPaddleX = leftPaddle.getX();
        double leftPaddleY = leftPaddle.getY();
        double leftPaddleWidth = leftPaddle.getWidth();
        double leftPaddleHeight = leftPaddle.getHeight();

        //first check if we are within the paddle's x-range
        if (ballX <= leftPaddleX + leftPaddleWidth && ballX + ballWidth >= leftPaddleX) {
            //then check if we are within the paddle's y-range
            if (ballY <= leftPaddleY + leftPaddleHeight && ballY >= leftPaddleY) {
                //we are within the paddle!
                return true;
            }
        }

        //otherwise, we are not within this paddle
        return false;
    }


    /**
     * A private helper method to check if this ball is beyond the
     * left paddle
     *
     * @return whether this ball is beyond the left paddle
     */
    private boolean isBallPastLeftPaddle() {
        double ballX = ballRectangle.getX();
        double ballWidth = ballRectangle.getWidth();
        double leftPaddleX = leftPaddle.getX();

        //right side of the ball is less than the left of the paddle
        return ballX + ballWidth < leftPaddleX;
    }

    /**
     * A private helper method to check if this ball is
     * within the x and y constraints of the right paddle
     *
     * @return whether this ball is within the right paddle
     */
    private boolean isBallWithinRightPaddle() {
        double ballX = ballRectangle.getX();
        double ballY = ballRectangle.getY();
        double ballWidth = ballRectangle.getWidth();

        double rightPaddleX = rightPaddle.getX();
        double rightPaddleY = rightPaddle.getY();
        double rightPaddleHeight = rightPaddle.getHeight();


        //first check if we are within the paddle's x-range
        if (ballX <= rightPaddleX && ballX + ballWidth >= rightPaddleX) {
            //then check if we are within the paddle's y-range
            if (ballY <= rightPaddleY + rightPaddleHeight && ballY >= rightPaddleY) {
                //we are within the paddle!
                return true;
            }
        }
        //otherwise, we are not within this paddle
        return false;
    }

    /**
     * A private helper method to check if this ball is beyond the
     * right paddle
     *
     * @return whether this ball is beyond the right paddle
     */
    private boolean isBallPastRightPaddle() {
        double ballX = ballRectangle.getX();
        double ballWidth = ballRectangle.getWidth();

        double rightPaddleX = rightPaddle.getX();
        double rightPaddleWidth = rightPaddle.getWidth();

        //if the right side of the ball is greater than the right-most of the paddle
        return ballX + ballWidth > rightPaddleX + rightPaddleWidth;
    }


    /**
     * A private helper method to check if this ball is past the
     * window's visible range at the top
     *
     * @return whether this ball is outside the top part of the visible window
     */
    private boolean isBallPastScreenTop() {
        double ballY = ballRectangle.getY();
        double ballHeight = ballRectangle.getHeight();

        return ballY + ballHeight > Constants.SCREEN_HEIGHT;
    }


    /**
     * A private helper method to check if this ball is past the
     * window's visible range at the bottom
     *
     * @return whether this ball is outside the bottom part of the visible window
     */
    private boolean isBallPastScreenBottom() {
        double ballY = ballRectangle.getY();

        return ballY < 0;
    }

    /**
     * A private helper method to calculate the exit angle of this ball
     * based on where it bounces on the given paddle
     * @param paddle the paddle this ball will bounce off of
     * @return the exit angle in radians at which this ball will bounce
     */
    private double calculateNewVelocityAngle(Rectangle paddle) {
        double ballY = ballRectangle.getY();
        double ballHeight = ballRectangle.getHeight();

        double paddleY = paddle.getY();
        double paddleHeight = paddle.getHeight();

        //calculate the center y of the paddle
        double relativeIntersectY = ((paddleY  +  (paddleHeight) / 2.0)) - ((ballY  +  (ballHeight) / 2.0));
        //normalize this so that the y-extremes of the paddle are between:
        // -1.0 (bottom of the paddle)
        // 0.0 (middle of the paddle)
        // 1.0 (top of the paddle)
        double normalIntersectY = relativeIntersectY / (paddleHeight / 2.0);

        //the exit angle is then the angle it arrives at proportional to the max angle (45 deg)
        return Math.toRadians(normalIntersectY * Constants.MAX_ANGLE_DEG);
    }

    /**
     * A private helper method that calculates the exit velocity
     * at which this ball should travel after colliding with this paddle
     * @param paddle the paddle this ball will bounce off of
     */
    private void calculateNewVelocity(Rectangle paddle) {
        double theta = calculateNewVelocityAngle(paddle);
        //calculate the component exit velocities
        double newVx = Math.abs(Math.cos(theta)) * Constants.BALL_SPEED;
        double newVy = (-Math.sin(theta)) * Constants.BALL_SPEED;


        double oldVelXSign = Math.signum(velX);

        //opposite x-velocity sign makes sure it goes in opposite direction
        this.velX = newVx * (-1.0 * oldVelXSign);
        this.velY = newVy;

    }
}
