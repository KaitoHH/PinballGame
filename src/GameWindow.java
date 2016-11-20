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
	private JPanel panelC;
	private GraphPanel graphPanel;
	private toolBoxPanel toolPanel;

	public static void main(String[] args) {
		GameWindow gameWindow = new GameWindow();
	}

	public GameWindow() {
		paintBoard.setLayout(new BorderLayout());
		graphPanel = new GraphPanel();
		paintBoard.add(graphPanel, BorderLayout.CENTER);


		toolPanel = new toolBoxPanel(graphPanel);
		panelC.setLayout(new BorderLayout());
		panelC.add(toolPanel, BorderLayout.CENTER);

		graphPanel.setDataSource(toolPanel);


		JFrame frame = new JFrame("GameWindow");
		frame.setJMenuBar(new GameMenu(graphPanel));
		frame.setSize(800, 800);
		frame.setContentPane(panel1);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	public void draw(){
	    graphPanel.build();
    }
}
