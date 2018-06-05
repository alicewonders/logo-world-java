import field.LogoField;

import javax.swing.*;
import java.awt.*;

public class LogoWorldGame extends JFrame {
    private final int FRAME_SIZE = 600;

    public LogoWorldGame(int size) {
        super();
        this.setSize(FRAME_SIZE, FRAME_SIZE + 22);
        this.setTitle("LogoWorld v0.1");
        ImageIcon imgIcon = new ImageIcon("images\\lilturtle2.png");
        this.setIconImage(imgIcon.getImage());
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel field = new LogoField(size);
        this.add(field);
    }
}
