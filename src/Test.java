import org.jbox2d.collision.AABB;
import org.jbox2d.testbed.framework.*;
import org.jbox2d.testbed.framework.j2d.TestPanelJ2D;

import javax.swing.*;

/**
 * Project: PinballGame
 * Author: KaitoHH
 * Create Date: 2016/11/7
 * Description:
 * All rights reserved.
 */
public class Test {
	public static void main(String[] args) {
		TestbedModel model = new TestbedModel();         // create our model
		model.addTest(new MJWTest2());                   // add our test
		TestbedPanel panel = new TestPanelJ2D(model);    // create our testbed panel
		JFrame testbed = new MyFrame(model, panel, TestbedController.UpdateBehavior.UPDATE_CALLED); // put both into our testbed frame
		testbed.setVisible(true);
		testbed.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
