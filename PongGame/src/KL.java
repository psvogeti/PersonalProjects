import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * A class designed to contain functionalities depending
 * on the type of key event that takes place
 */
public class KL implements KeyListener {

    private boolean keyPressed[] = new boolean[128]; //contains all 128 ASCII key event booleans


    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    /**
     * Listens for if keyEvent is pressed and sets that keyEvent's
     * status to true
     *
     * @param keyEvent the key event to be processed
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        keyPressed[keyEvent.getKeyCode()] = true;
    }

    /**
     * Listens for if a keyEvent is released and sets that keyEvent's
     * status to false
     *
     * @param keyEvent the key event to be processed
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        keyPressed[keyEvent.getKeyCode()] = false;
    }

    /**
     * A status getting method that informs if a key
     * has been pressed or not
     *
     * @param keyCode the code of the key to check
     * @return whether the key with this keyCode has been pressed
     */
    public boolean isKeyPressed(int keyCode) {
        return keyPressed[keyCode];
    }
}
