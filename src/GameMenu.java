import javax.swing.*;
import javax.swing.filechooser.FileFilter;
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

		newItem.addActionListener(e -> {
			panel.newScene();
		});
		saveItem.addActionListener(e -> {
			JFileChooser chooser = new JFileChooser();
			chooser.showSaveDialog(this);
			File file = chooser.getSelectedFile();
			String fileName = file.getName();
			if (!fileName.endsWith(".psc")) {
				fileName += ".psc";
			}
			System.out.println(file.getAbsoluteFile());
		});
		loadItem.addActionListener(e -> {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter(new PinballFileFilter());
			chooser.showOpenDialog(this);
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