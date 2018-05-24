import javafx.util.Pair;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Turtle extends JPanel implements KeyListener {
    private final int UP_OFFSET = 30;
    private final int LEFT_OFFSET = 40;
    private final int GAME_FIELD_SIZE = 500;

//    private int oldx, oldy, newx, newy;
    private int x = 0, y = 0, velx = 0, vely = 0, cell, cage;
    private boolean isDrawing;
    private ArrayList<Coordinates> turtleDrawHistory = null;

    public Turtle(int startX, int startY, int cellSize, int cageNum, ArrayList<Coordinates> turtleDrawHistory){
        addKeyListener(this);
        setFocusable(true);
        setOpaque(false);
        setFocusTraversalKeysEnabled(false);
        this.turtleDrawHistory = turtleDrawHistory;
        x = startX;
        y = startY;
//        newx = x;
//        newy = y;
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
//        if (isDrawing) {
//            g.setColor(Color.ORANGE);
//            Graphics2D g2 = (Graphics2D) g;
//            g2.setStroke(new BasicStroke(3.0f));
//            g2.drawLine(oldx + cell / 2, oldy + cell / 2,
//                        newx + cell / 2, newy + cell /2);
//        }
    }

    private void move(char direction, int offset) {
        if (direction == 'L') {
            vely = 0;
            velx = (x < LEFT_OFFSET + cell) ? (cage - offset) * cell : -cell;
        }

        if (direction == 'R'){
            vely = 0;
            velx = (x > GAME_FIELD_SIZE - cell) ? -(cage - offset) * cell : cell;
        }

        if (direction == 'D') {
            velx = 0;
            vely = (y > GAME_FIELD_SIZE - cell) ? -(cage - offset) * cell : cell;
        }

        if (direction == 'U') {
            velx = 0;
            vely = (y < UP_OFFSET + cell) ? (cage - offset) * cell : -cell;
        }
        int oldy = y;
        int oldx = x;
        y += vely;
        x += velx;
        if (isDrawing) {
            turtleDrawHistory.add(new Coordinates(oldx, oldy, x, y));
        }
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
        }
        repaint();
    }

    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
}
