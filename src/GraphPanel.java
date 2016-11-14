import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.*;
//import java.awt.geom

/**
 * Created by Hehongliang on 2016/11/13.
 */
public class GraphPanel extends JPanel {
    private final static int rowNum = 20;

    public int getMinLength() {
        return Math.min(getHeight(), getWidth()) - 15;
    }

    public void paintComponent(Graphics g) {
        //setBackground(Color.PINK);
        int length = getMinLength();
        Graphics2D g2D = (Graphics2D) g;
        double rowHeight = 1.0 * length / rowNum;
        for (int i = 0; i <= rowNum; i++) {
            Line2D row = new Line2D.Double(0, rowHeight * i, length, rowHeight * i);
            Line2D col = new Line2D.Double(rowHeight * i, 0, rowHeight * i, length);
            g2D.draw(row);     //绘画横线
            g2D.draw(col);     //绘画纵线
        }
        g2D.setColor(Color.CYAN);
        //g2D.draw(paintCircle(0.0, 0.0,rowHeight));
        g2D.fill(paintCircle(0.0, 0.0,rowHeight));
    }

    public Ellipse2D paintCircle(double x, double y, double diameter)   //(x,y)是圆形左上角的坐标，diameter是直径
    {
        Ellipse2D circle = new Ellipse2D.Double(x, y, diameter, diameter);
        return circle;
    }

    public Rectangle2D paintSquare(double x, double y, double height) {
        Rectangle2D square = new Rectangle2D.Double(x, y, height, height);
        return square;
    }

    public GeneralPath paintTriangle(double x, double y,double length) {
		GeneralPath triangle=new GeneralPath();
        triangle.moveTo(0, 0);
        triangle.lineTo(0, length);
        triangle.lineTo(length, length);
        triangle.lineTo(0, 0);
        return triangle;
    }
}
