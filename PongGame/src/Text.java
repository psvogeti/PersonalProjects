import java.awt.*;
public class Text {
    private String text;
    private Font font;
    private double x, y;
    private double width, height;

    private Color textColor = Color.WHITE;

    public Text(String text, Font font, double x, double y, Color textColor) {
        this.text = text;
        this.font = font;
        this.x = x;
        this.y = y;
        this.width = font.getSize() * text.length();
        this.height = font.getSize();

    }

    public Text(int text, Font font, double x, double y) {
        this.text =  "" + text;
        this.font = font;
        this.x = x;
        this.y = y;
    }

    public void drawText(Graphics2D g2D) {
        g2D.setColor(textColor);
        g2D.setFont(font);
        g2D.drawString(text, (float)x, (float)y);
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getTextWidth() {
        return width;
    }

    public void setTextWidth(double textWidth) {
        this.width = textWidth;
    }

    public double getTextHeight() {
        return height;
    }

    public void setTextHeight(double textHeight) {
        this.height = textHeight;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }
}
