import javax.swing.*;
import java.awt.event.*;

public class AboutDialog extends JDialog {
	private JPanel contentPane;
	private JButton buttonOK;

	public AboutDialog() {
		setContentPane(contentPane);
		setModal(true);
		getRootPane().setDefaultButton(buttonOK);
		setResizable(false);

		buttonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onOK();
			}
		});
		pack();
		setVisible(true);
	}

	private void onOK() {
		dispose();
	}

}
