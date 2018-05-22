import javax.swing.*;
import java.awt.*;

public class Bground extends JFrame {
    private final int FRAME_SIZE = 600;

    Bground(int size) {
        super();
        this.setSize(FRAME_SIZE, FRAME_SIZE + 40);
        this.setResizable(false); //фиксация размера окна
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel field = new LogoField(size);
        getContentPane().add(field);
    }
}
