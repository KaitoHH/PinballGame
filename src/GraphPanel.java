import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;

/**
 * Created by Hehongliang on 2016/11/13.
 */
public class GraphPanel extends JPanel {
    private int rowNum = 20;
    private int colNum = 20;
    private int length=400;
    Graphics2D g2;

    public GraphPanel() {
        super();
        //setBackground(Color.PINK);
        length = getMinLenth();
    }

    public int getMinLenth() {
        return Math.min(getHeight(), getWidth()) - 15;
    }

    public void paintComponent(Graphics g) {
        length = getMinLenth();
        length=length<0?400:length;
        g2 = (Graphics2D) g;
        double rowHeight = 1.0 * length / rowNum;
        for (int i = 0; i <= rowNum; i++) {
            Line2D row = new Line2D.Double(0, rowHeight * i, length, rowHeight * i);
            Line2D col = new Line2D.Double(rowHeight * i, 0, rowHeight * i, length);
            g2.draw(row);     //绘画横线
            g2.draw(col);     //绘画纵线
        }
    }


}
