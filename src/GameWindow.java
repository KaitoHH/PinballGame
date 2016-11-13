import javax.swing.*;
import java.awt.*;

/**
 * Project: PinballGame
 * Author: KaitoHH
 * Create Date: 2016/11/13
 * Description:
 * All rights reserved.
 */
public class GameWindow {
    private JPanel panel1;
    private JButton playButton;
    private JPanel paintBoard;
    private JPanel toolMenu;
    private JButton button12;
    private JPanel panelC;

    public GameWindow() {
        panelC.setLayout(new BorderLayout());
        panelC.add(new toolBoxPanel(),BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GameWindow");
        frame.setSize(800,800);
        frame.setContentPane(new GameWindow().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        //frame.setLayout(null);
    }
}
