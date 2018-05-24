import javafx.util.Pair;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LogoField extends JPanel {
    private final int UP_OFFSET = 30;
    private final int LEFT_OFFSET = 40;
    private final int GAME_FIELD_SIZE = 500;

    private Turtle turtle;
    private ArrayList<Coordinates> turtleDrawHistory = new ArrayList<>();
    private int fieldSize;
    private int cellSize;
    private Image bg = null;

    {
        try {
            bg = ImageIO.read(new File(
                    "D:\\Documents\\Education\\OOP\\bg.png"));
        } catch (IOException exception) {
            System.err.println("Error while opening image");
        }
    }

    LogoField(int size) {
        this.fieldSize = size;
        this.cellSize = GAME_FIELD_SIZE / fieldSize;
        turtle = new Turtle(3 * cellSize + LEFT_OFFSET, 3 * cellSize + UP_OFFSET, cellSize, size, turtleDrawHistory);
        this.setLayout(new BorderLayout());
        this.add(turtle);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, null);

        g.setColor(Color.BLACK);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(1.0f));

        int lineOffset = GAME_FIELD_SIZE / fieldSize;
        for (int i = 0; i <= fieldSize; i++) {
            g2.drawLine(LEFT_OFFSET + i * lineOffset, UP_OFFSET,
                    LEFT_OFFSET + i * lineOffset, UP_OFFSET + fieldSize * lineOffset); //vertical
            g2.drawLine(LEFT_OFFSET, UP_OFFSET + i * lineOffset,
                    LEFT_OFFSET + fieldSize * lineOffset, UP_OFFSET + i * lineOffset); //horizontal
        }

        g2.setColor(Color.PINK);
        g2.setStroke(new BasicStroke(4.0f));
        // Drawing path
        for (Coordinates coordinates : turtleDrawHistory) {
            g2.drawLine(coordinates.getX1() + cellSize / 2, coordinates.getY1() + cellSize / 2,
                        coordinates.getX2() + cellSize / 2, coordinates.getY2() + cellSize / 2);
        }
    }
}