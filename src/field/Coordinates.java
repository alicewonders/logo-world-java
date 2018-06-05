package field;

import java.awt.Color;
import java.util.Random;

public class Coordinates {
    private int x1, x2, y1, y2;
    private Color lineColor;

    Coordinates(int x1, int y1, int x2, int y2, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.lineColor = color;
    }

    public int getX1() {
        return x1;
    }
    public int getX2() {
        return x2;
    }
    public int getY1() {
        return y1;
    }
    public int getY2() {
        return y2;
    }

    public Color getNewColor() {
//        Random rand = new Random();
//        float r = rand.nextFloat();
//        float g = rand.nextFloat();
//        float b = rand.nextFloat();
//        lineColor = Color.getHSBColor(r, g, b);
        return lineColor;
    }

}
