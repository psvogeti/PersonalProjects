import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class ML extends MouseAdapter implements MouseMotionListener {
    private boolean isPressed = false;
    private double mouseX = 0.0, mouseY = 0.0;


    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        isPressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        isPressed = false;
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        this.mouseX = mouseEvent.getX();
        this.mouseY = mouseEvent.getY();
    }

    public boolean isMousePressed() {
        return isPressed;
    }

    public double getMouseX() {
        return mouseX;
    }

    public void setMouseX(double mouseX) {
        this.mouseX = mouseX;
    }

    public double getMouseY() {
        return mouseY;
    }

    public void setMouseY(double mouseY) {
        this.mouseY = mouseY;
    }


}
