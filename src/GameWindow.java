import javax.swing.*;

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
		JFrame frame = new JFrame("GameWindow");
		frame.setContentPane(new GameWindow().panel1);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
