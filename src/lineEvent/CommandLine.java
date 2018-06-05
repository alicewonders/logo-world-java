package lineEvent;

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
        this.add(commandLabel);

        final JTextField commandInput = new JTextField(FIELD_COLUMNS);
        commandInput.setLayout(null);
        commandInput.setFont(new Font("Courier New", Font.PLAIN, 16));
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
                        fireCommandLineEvent(new CommandLineEvent(this, commandInput.getText().toUpperCase()));
                }
            }
        });

    }

    private void fireCommandLineEvent(CommandLineEvent e) {
        Object[] listeners = listenerList.getListenerList();

        for (int i = 0; i < listeners.length; i +=2) {
            if (listeners[i] == CommandLineListener.class) {
                ((CommandLineListener)listeners[i + 1]).commandLineEventOccurred(e);
            }
        }
    }

    public void addCommandLineListener(CommandLineListener listener) {
        listenerList.add(CommandLineListener.class, listener);
    }

    public void removeCommandLineListener(CommandLineListener listener) {
        listenerList.remove(CommandLineListener.class, listener);
    }
}
