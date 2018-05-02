import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LogoField extends JFrame {
    private int FIELD_SIZE;
    private final int PANEL_SIZE = 600;
    private int WINDOW_OFFSET = 20; //верхняя "крышка" окна съедает часть клетки, нужно сместится
    private int FIELD_DOWN_OFFSET = PANEL_SIZE / 8;
    private int FIELD_LRU_OFFSET = PANEL_SIZE / 15;

    public LogoField(int size){
        super();
        this.FIELD_SIZE = size;
        this.setSize(PANEL_SIZE, PANEL_SIZE);
        this.setContentPane(new Bground());
        this.setResizable(false); //фиксация размера окна
        Container cont = getContentPane();
        cont.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        repaint();
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.BLACK);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2.0f));

        int cellWidth = (getWidth() - 2 * FIELD_LRU_OFFSET) / FIELD_SIZE;
        int cellHeight = (getHeight() - FIELD_DOWN_OFFSET - FIELD_LRU_OFFSET - WINDOW_OFFSET) / FIELD_SIZE;
        System.out.println(cellHeight);
        System.out.println(cellWidth);
        for (int i =0; i < FIELD_SIZE; i++){
            g2.drawLine(getWidth() - i * cellWidth - FIELD_LRU_OFFSET,WINDOW_OFFSET + FIELD_LRU_OFFSET,
                    getWidth() - i * cellWidth - FIELD_LRU_OFFSET, getHeight() - FIELD_DOWN_OFFSET); //vertical
            g2.drawLine(FIELD_LRU_OFFSET, getHeight() - FIELD_DOWN_OFFSET -  i * cellHeight,
                    getWidth() - FIELD_LRU_OFFSET, getHeight() - FIELD_DOWN_OFFSET -  i * cellHeight); //horizontal
        }
        g2.drawLine(FIELD_LRU_OFFSET, WINDOW_OFFSET + FIELD_LRU_OFFSET,
                FIELD_LRU_OFFSET, getHeight() - FIELD_DOWN_OFFSET);
        g2.drawLine(FIELD_LRU_OFFSET, WINDOW_OFFSET + FIELD_LRU_OFFSET,
                getWidth() - FIELD_LRU_OFFSET, WINDOW_OFFSET + FIELD_LRU_OFFSET);
    }

    class Bground extends JPanel {
        public void paintComponent(Graphics g){
            Image image = null;
            Image turt = null;
            try {
                image = ImageIO.read(new File(
                        "D:\\Documents\\Education\\OOP\\bg.png"));
                turt = ImageIO.read(new File(
                        "D:\\Documents\\Education\\OOP\\lilturtle.png"));
            } catch (IOException exception){
                System.err.println("Error while opening image");
            }
            g.drawImage(image, 0, 0, null);
            g.drawImage(turt, 52 + FIELD_LRU_OFFSET ,
                    4 * 46 + FIELD_LRU_OFFSET, null);
        }
    }

//    class Turtle extends JPanel {
//        public void Turtle(Graphics g, int cellWidth, int cellHeight) {
//            Image image = null;
//            try {
//                image = ImageIO.read(new File(
//                        "D:\\Documents\\Education\\OOP\\lilturtle.png"));
//            } catch (IOException exception){
//                System.err.println("Error while opening image");
//            }
//            g.drawImage(image, 2 * cellWidth + FIELD_LRU_OFFSET + cellWidth / 2,
//                    5 * cellHeight + WINDOW_OFFSET + FIELD_LRU_OFFSET + cellHeight / 2, null);
//        }
//    }
}
