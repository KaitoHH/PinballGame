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
	private JButton button1;
	private JButton button2;
	private JPanel paintBoard;
	private JToolBar menu;
	private JPanel toolMenu;

	public static void main(String[] args) {
		new GameWindow();
	}

	public GameWindow() {
		GraphPanel graphpanel = new GraphPanel();
		paintBoard.setLayout(new BorderLayout());
		paintBoard.add(graphpanel, BorderLayout.CENTER);

		JFrame frame = new JFrame("GameWindow");
		frame.setJMenuBar(new GameMenu());
		frame.setSize(700, 500);
		frame.setContentPane(panel1);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
