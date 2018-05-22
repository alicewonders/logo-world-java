import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class Turtle extends JPanel implements KeyListener {
    int x = 0, y = 0, velx = 0, vely = 0;

    public Turtle(int startX, int startY){
        addKeyListener(this);
        setFocusable(true);
        setOpaque(false);
        setFocusTraversalKeysEnabled(false);
        x = startX;
        y = startY;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Image turt = null;
        try {
            turt = ImageIO.read(new File(
                "D:\\Documents\\Education\\OOP\\lilturtle.png"));
        }
        catch (IOException exception) {
            System.err.println("Error while opening image");
        }
        g.drawImage(turt, x, y, null);
    }

    public void up() {
        velx = 0;
        vely = -44;
    }
    public void down() {
        velx = 0;
        vely = 44;
    }
    public void left() {
        vely = 0;
        velx = -52;
    }
    public void right() {
        vely = 0;
        velx = 52;
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
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
