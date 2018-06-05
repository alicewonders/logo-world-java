import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static java.awt.Color.*;

public class Turtle extends JPanel implements KeyListener {
    private final int UP_OFFSET = 30;
    private final int LEFT_OFFSET = 40;
    private final int GAME_FIELD_SIZE = 500;

    private int x = 0, y = 0, velx = 0, vely = 0, cell, cage;
    private Color color = WHITE;
    private boolean isDrawing;
    private int drawingFlag = 0;
    private float grad = 0;
    private ArrayList<Coordinates> turtleDrawHistory = null;

    public Turtle(int startX, int startY, int cellSize, int cageNum, ArrayList<Coordinates> turtleDrawHistory){
        addKeyListener(this);
        setFocusable(true);
        setOpaque(false);
        setFocusTraversalKeysEnabled(false);
        this.turtleDrawHistory = turtleDrawHistory;
        x = startX;
        y = startY;
        cell = cellSize;
        cage = cageNum;
        isDrawing = false;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Image turt = null;
        try {
            turt = ImageIO.read(new File(
                "D:\\Documents\\Education\\OOP\\lilturtle2.png"));
        }
        catch (IOException exception) {
            System.err.println("Error while opening image");
        }
        g.drawImage(turt, x, y, null);
    }

    private void move(char direction, int offset) {
        if (direction == 'L') {
            vely = 0;
            velx = (x < LEFT_OFFSET + cell) ? (cage - offset) * cell : -cell;
            if (x < LEFT_OFFSET + cell && isDrawing) {
                isDrawing = !isDrawing;
                drawingFlag = 1;
            }
        }

        if (direction == 'R'){
            vely = 0;
            velx = (x > GAME_FIELD_SIZE - cell) ? -(cage - offset) * cell : cell;
            if (x > GAME_FIELD_SIZE - cell && isDrawing) {
                isDrawing = !isDrawing;
                drawingFlag = 1;
            }
        }

        if (direction == 'D') {
            velx = 0;
            vely = (y > GAME_FIELD_SIZE - cell) ? -(cage - offset) * cell : cell;
            if (y > GAME_FIELD_SIZE - cell && isDrawing) {
                isDrawing = !isDrawing;
                drawingFlag = 1;
            }
        }

        if (direction == 'U') {
            velx = 0;
            vely = (y < UP_OFFSET + cell) ? (cage - offset) * cell : -cell;
            if (y < UP_OFFSET + cell && isDrawing) {
                isDrawing = !isDrawing;
                drawingFlag = 1;
            }
        }
        int oldy = y;
        int oldx = x;
        y += vely;
        x += velx;
        if (isDrawing) {
            turtleDrawHistory.add(new Coordinates(oldx, oldy, x, y, color));
        }
        if (drawingFlag == 1) {
            isDrawing = !isDrawing;
            drawingFlag = 0;
        }
    }

    private void getMyColor(char c){
        switch (c) {
            case 'P':
                color = PINK;
                break;
            case 'R':
                color = RED;
                break;
            case 'B':
                color = BLUE;
                break;
        }
    }

    public Color setNewColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
//        float r = 1.00F;
//        float g = r - grad;
//        float b = r - grad;
        Color newColor;
        newColor = new Color(r, g, b);
        color = newColor;
        return color;
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code){
            case KeyEvent.VK_D:
                isDrawing = !isDrawing;
                break;
            case KeyEvent.VK_UP:
                move('U', 1);
                break;
            case KeyEvent.VK_DOWN:
                move('D', 1);
                break;
            case KeyEvent.VK_LEFT:
                move('L', 1);
                break;
            case KeyEvent.VK_RIGHT:
                move('R', 1);
                break;
            case KeyEvent.VK_1:
                if (grad < 0.98) {
                    grad += 0.02;
                    setNewColor();
                }
                break;
        }
        repaint();
    }

    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
}
