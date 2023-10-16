import java.awt.*;

/**
 * A class designed to hold universal game constants so that inherently,
 * cohesive and understandable code is achieved.
 */
public final class Constants {

    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final String SCREEN_TITLE = "Pong";
    public static final Color BACKGROUND_COLOR = Color.BLACK;

    public static final double PADDLE_WIDTH = 10;
    public static final double PADDLE_HEIGHT = 100;
    public static final Color PADDLE_COLOR = Color.WHITE;
    public static final double PADDLE_SPEED = 200;
    public static final double BALL_WIDTH = 10;
    public static final double BALL_HEIGHT = 10;
    public static final Color BALL_COLOR = Color.WHITE;
    public static final double BALL_SPEED = 200;
    public static final double HZ_PADDING = 40;
    public static final double VT_PADDING = 40;

    public static double TOOLBAR_HEIGHT;
    public static double INSETS_BOTTOM;

    public static final double MAX_ANGLE_DEG = 45;


    public static final Color TEXT_COLOR = Color.WHITE;
    public static final int TEXT_Y_POS = 70;
    public static final int TEXT_X_POS = 10;
    public static final int TEXT_SIZE = 40;

    public static final int WIN_SCORE = 10;
}
