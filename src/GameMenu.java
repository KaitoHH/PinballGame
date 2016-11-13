import javax.swing.*;

/**
 * Project: PinballGame
 * Author: KaitoHH
 * Create Date: 2016/11/13
 * Description:
 * All rights reserved.
 */
public class GameMenu extends JMenuBar {
	public GameMenu() {
		JMenu fileMenu = new JMenu("文件");
		JMenu aboutMenu = new JMenu("帮助");
		JMenuItem newItem = new JMenuItem("新建");
		JMenuItem saveItem = new JMenuItem("保存");
		JMenuItem loadItem = new JMenuItem("载入");
		JMenuItem aboutItem = new JMenuItem("关于");
		fileMenu.add(newItem);
		fileMenu.add(saveItem);
		fileMenu.add(loadItem);
		aboutMenu.add(aboutItem);
		add(fileMenu);
		add(aboutMenu);

		newItem.addActionListener(e -> System.out.println("new"));
	}


}
