import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * Project: PinballGame
 * Author: KaitoHH
 * Create Date: 2016/11/13
 * Description:
 * All rights reserved.
 */
public class GameMenu extends JMenuBar {
	public GameMenu(GraphPanel panel) {
		JMenu fileMenu = new JMenu("文件");
		JMenu aboutMenu = new JMenu("帮助");
		JMenu optionMenu = new JMenu("设置");
		JMenuItem newItem = new JMenuItem("新建");
		JMenuItem saveItem = new JMenuItem("保存");
		JMenuItem loadItem = new JMenuItem("载入");
		JMenuItem aboutItem = new JMenuItem("关于");
		JCheckBoxMenuItem moveItem = new JCheckBoxMenuItem("碰撞位移");
		moveItem.setState(true);
		optionMenu.add(moveItem);
		fileMenu.add(newItem);
		fileMenu.add(saveItem);
		fileMenu.add(loadItem);
		aboutMenu.add(aboutItem);
		add(fileMenu);
		add(optionMenu);
		add(aboutMenu);

		newItem.addActionListener(e -> {
			panel.newScene();
		});
		saveItem.addActionListener(e -> {
			panel.setCanFocus(false);
			JFileChooser chooser = new JFileChooser();
			chooser.showSaveDialog(this);
			File file = chooser.getSelectedFile();
			String fileName = file.getAbsolutePath();
			if (!fileName.endsWith(".psc")) {
				fileName += ".psc";
			}
			panel.saveScene(fileName);
			panel.setCanFocus(true);
		});
		loadItem.addActionListener(e -> {
			panel.setCanFocus(false);
			JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter(new PinballFileFilter());
			chooser.showOpenDialog(this);
			File file = chooser.getSelectedFile();
			panel.loadScene(file.getAbsolutePath());
			panel.setCanFocus(true);
		});
		moveItem.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean state = ((JCheckBoxMenuItem) e.getSource()).getState();
				Gizmo.setMove(state);
			}
		});
		aboutItem.addActionListener(e -> {
			new AboutDialog();
		});
	}

}

class PinballFileFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) return true;
		return f.getName().endsWith(".psc");
	}

	@Override
	public String getDescription() {
		return ".psc";
	}
}