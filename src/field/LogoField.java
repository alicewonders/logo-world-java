package field;

import lineEvent.CommandLine;
import lineEvent.CommandLineEvent;
import lineEvent.CommandLineListener;
import commandFactory.*;

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

    public LogoField(int size) {
        this.fieldSize = size;
        this.cellSize = GAME_FIELD_SIZE / fieldSize;
        turtle = new Turtle(3 * cellSize + LEFT_OFFSET, 3 * cellSize + UP_OFFSET, cellSize, size, turtleDrawHistory);
        this.setLayout(new BorderLayout());
        CommandLine commandPanel = new CommandLine();
        commandPanel.setBounds(123, 540, 330, 35);
        commandPanel.setOpaque(true);
        commandPanel.setBackground(new Color(179, 240, 173));
        commandPanel.addCommandLineListener(new CommandLineListener() {
            @Override
            public void commandLineEventOccurred(CommandLineEvent e) {
                super.commandLineEventOccurred(e);
                reactOnCommand(e.getCommand(), e.getArgs());
            }
        });
        this.add(commandPanel);
        this.add(turtle);

    }

    public Turtle getTurtle() {
        return turtle;
    }

    private void reactOnCommand(String command, String[] args) {
        CommandExecutor.executeCommand(command, args, this);
        repaint();
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

        g2.setStroke(new BasicStroke(6.0f));
//        g2.setColor(turtle.getNewColor());
        // Drawing path
        // g2.setColor(turtle.getNewColor());
//        turtle.setNewColor();
//        turtle.setNewColor();
        for (Coordinates coordinates : turtleDrawHistory) {
//            turtle.setNewColor();
            g2.setColor(turtle.setNewColor());
//            g2.setColor(coordinates.getNewColor());
            g2.drawLine(coordinates.getX1() + cellSize / 2, coordinates.getY1() + cellSize / 2,
                    coordinates.getX2() + cellSize / 2, coordinates.getY2() + cellSize / 2);
        }
    }
}