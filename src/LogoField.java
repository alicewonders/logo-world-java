import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class LogoField extends JPanel {
    private final int PANEL_SIZE = 600;
    private final int FIELD_LRU_OFFSET = PANEL_SIZE / 15;
    private final int UP_OFFSET = 30;
    private final int LEFT_OFFSET = 40;

    private Turtle turtle;
    private int fieldSize;
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
        turtle = new Turtle(3 * 52 + FIELD_LRU_OFFSET, 6 * 44 + FIELD_LRU_OFFSET);
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

//        int cellWidth = (PANEL_SIZE - 2 * FIELD_LRU_OFFSET) / fieldSize;
//        int cellHeight = (PANEL_SIZE - FIELD_DOWN_OFFSET - FIELD_LRU_OFFSET - WINDOW_OFFSET) / fieldSize;
//        System.out.println(cellHeight);
//        System.out.println(cellWidth);

        int lineOffset = 500 / fieldSize;
        for (int i = 0; i <= fieldSize; i++) {
            g2.drawLine(LEFT_OFFSET + i * lineOffset, UP_OFFSET,
                    LEFT_OFFSET + i * lineOffset, UP_OFFSET + fieldSize * lineOffset); //vertical
            g2.drawLine(LEFT_OFFSET, UP_OFFSET + i * lineOffset, LEFT_OFFSET + fieldSize * lineOffset, UP_OFFSET + i * lineOffset);
        }
    }
}