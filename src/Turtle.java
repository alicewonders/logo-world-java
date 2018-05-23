import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class Turtle extends JPanel implements KeyListener {
    private int x = 0, y = 0, velx = 0, vely = 0, cell;
    private boolean isDrawing;

    public Turtle(int startX, int startY, int cellSize){
        addKeyListener(this);
        setFocusable(true);
        setOpaque(false);
        setFocusTraversalKeysEnabled(false);
        x = startX;
        y = startY;
        cell = cellSize;
        isDrawing = false;
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

    private void up() {
        if (y < 30 + cell) {
            vely = 0;
            velx = 0;
        }
        else {
            velx = 0;
            vely = -cell;
        }
    }
    private void down() {
        if (y > 500 - cell) {
            vely = 0;
            velx = 0;
        }
        else {
            velx = 0;
            vely = cell;
        }
    }
    private void left() {
        if (x < 40 + cell) {
            velx = 0;
            vely = 0;
        }
        else {
            vely = 0;
            velx = -cell;
        }
    }
    private void right() {
        if (x > 500 - cell){
            velx = 0;
            vely = 0;
        }
        else {
            vely = 0;
            velx = cell;
        }
    }

    private void draw() {

    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_D){
            if (!isDrawing) {
                draw();
            }
            isDrawing = !isDrawing;
        }
        if (code == KeyEvent.VK_UP) {
            up();
        }
        if (code == KeyEvent.VK_DOWN) {
            down();
        }
        if (code == KeyEvent.VK_LEFT) {
            left();
        }
        if (code == KeyEvent.VK_RIGHT) {
            right();
        }
        x += velx;
        y += vely;
        repaint();
    }

    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
}
