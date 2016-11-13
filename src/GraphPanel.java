import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

/**
 * Created by Hehongliang on 2016/11/13.
 */
public class GraphPanel extends JPanel {
	private final static int rowNum = 20;

	public int getMinLength() {
		return Math.min(getHeight(), getWidth()) - 15;
	}

	public void paintComponent(Graphics g) {
		int length = getMinLength();
		Graphics2D g2D = (Graphics2D) g;
		double rowHeight = 1.0 * length / rowNum;
		for (int i = 0; i <= rowNum; i++) {
			Line2D row = new Line2D.Double(0, rowHeight * i, length, rowHeight * i);
			Line2D col = new Line2D.Double(rowHeight * i, 0, rowHeight * i, length);
			g2D.draw(row);     //绘画横线
			g2D.draw(col);     //绘画纵线
		}
	}

}
