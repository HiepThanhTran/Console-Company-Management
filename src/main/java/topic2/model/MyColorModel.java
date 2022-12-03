package topic2.model;

import java.awt.Color;

public class MyColorModel {

    private Color foreGround;
    private Color background;
    private boolean opaque;
    private boolean borderPainted;

    public MyColorModel() {
        this.foreGround = Color.BLACK;
        this.background = Color.WHITE;
        this.opaque = false;
        this.borderPainted = false;
    }

    public Color getForeGround() {
        return foreGround;
    }

    public void setForeGround(Color foreGround) {
        this.foreGround = foreGround;
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public boolean isOpaque() {
        return opaque;
    }

    public void setOpaque(boolean opaque) {
        this.opaque = opaque;
    }

    public boolean isBorderPainted() {
        return borderPainted;
    }

    public void setBorderPainted(boolean borderPainted) {
        this.borderPainted = borderPainted;
    }
}
