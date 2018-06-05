import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EventListener;

public class CommandLine extends JPanel implements EventListener{
    private int FIELD_COLUMNS = 20;

    public CommandLine () {
        super();
        this.setOpaque(false);
        this.setVisible(true);
        this.setSize(330, 545);

        final JLabel commandLabel = new JLabel("Command:");
        commandLabel.setLayout(null);
        commandLabel.setFont(new Font("Courier New", Font.BOLD, 16));
//        commandLabel.setBounds(117, 530, 70, 40);
        this.add(commandLabel);

        final JTextField commandInput = new JTextField(FIELD_COLUMNS);
        commandInput.setLayout(null);
        commandInput.setFont(new Font("Courier New", Font.PLAIN, 16));
//        commandInput.setBackground(new Color(179, 240, 173));
//        commandInput.setBounds(191, 540, 250, 20);
        this.add(commandInput);

        commandInput.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                switch (code) {
                    case KeyEvent.VK_ENTER:
                        //return to the turtle
                }
            }
        });
    }
}
