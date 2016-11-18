import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Hehongliang on 2016/11/13.
 */
public class GraphPanel extends JPanel{

	enum Shape {
		Triangle, Rectangle, Circle
	}
	private final static int rowNum = 20;
	private java.util.List<Item> components = new ArrayList();
	private int length=200;
	private double rowHeight=10.0;
	public GraphPanel(){
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				length = getMinLength();
				rowHeight = 1.0 * length / rowNum;
				int x=(int) (1.0*e.getX()/rowHeight);
				int y=(int) (1.0*e.getY()/rowHeight);
				System.out.println(e.getX());
				System.out.println(rowHeight);
				System.out.println(x);
				Item circle=new Item(x,y,1,Shape.Circle,Color.BLUE);
				components.add(circle);
				getGraphics().clearRect(0,0,getWidth(),getHeight());
				paintComponent(getGraphics());
			}
		});
	}
	public int getMinLength() {
		return Math.min(getHeight(), getWidth()) - 15;
	}

	@Override
	public void paintComponent(Graphics g) {
		//setBackground(Color.PINK);
		length = getMinLength();
		Graphics2D g2D = (Graphics2D) g;
		rowHeight = 1.0 * length / rowNum;
		for (int i = 0; i <= rowNum; i++) {
			Line2D row = new Line2D.Double(0, rowHeight * i, length, rowHeight * i);
			Line2D col = new Line2D.Double(rowHeight * i, 0, rowHeight * i, length);
			g2D.draw(row);     //绘画横线
			g2D.draw(col);     //绘画纵线
		}

		int x, y;int sizeRate;
		for (int i = 0; i < components.size(); i++) {
			g2D.setColor(components.get(i).getColor());
			x = components.get(i).getX();
			y = components.get(i).getY();
			sizeRate = components.get(i).getSizeRate();
			switch (components.get(i).getShape()) {
				case Triangle:
					g2D.fill(paintTriangle(x, y, rowHeight * sizeRate));
					break;
				case Rectangle:
					g2D.fill(paintSquare(x, y, rowHeight * sizeRate));
					break;
				case Circle:
					g2D.fill(paintCircle(x, y, rowHeight * sizeRate));
					break;
			}
		}
	}

	private double Coordinate(int i)//获取真实坐标
	{
		return i*rowHeight;
	}


	private Ellipse2D paintCircle(int x, int y, double diameter)
	//(x,y)是圆形左上角的坐标，diameter是直径
	{
		Ellipse2D circle = new Ellipse2D.Double(Coordinate(x), Coordinate(y), diameter, diameter);
		return circle;
	}

	private Rectangle2D paintSquare(int x, int y, double height)
	//(x,y)是正方形左上角的坐标，height是边长
	{
		Rectangle2D square = new Rectangle2D.Double(Coordinate(x), Coordinate(y), height, height);
		return square;
	}

	private GeneralPath paintTriangle(int x, int y, double length)
	//(x,y)是直角三角形左上角的坐标，length是直角边长默认直角在左下角
	{
		GeneralPath triangle = new GeneralPath();
		triangle.moveTo(Coordinate(x), Coordinate(y));
		triangle.lineTo(Coordinate(x), length);
		triangle.lineTo(length, length);
		triangle.lineTo(Coordinate(x), Coordinate(y));
		return triangle;
	}

	public void drawTest() {
		Graphics2D g2D = (Graphics2D) getGraphics();
		g2D.setColor(Color.blue);
		Rectangle2D rectangle = new Rectangle.Double(0, 0, 100, 100);
		g2D.draw(rectangle);
		repaint();
		Item item = new Item(3, 5, 1, Shape.Circle, Color.black);
	}



	class Item implements Serializable {
		private int x;
		private int y;
		private int sizeRate = 1;
		private Shape shape;
		private Color color;
		private double angle = 0 * Math.PI / 180;

		public Item(int x, int y, int sizeRate, Shape shape, Color color) {
			this.x = x;
			this.y = y;
			this.sizeRate = sizeRate;
			this.shape = shape;
			this.color = color;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public int getSizeRate() {
			return sizeRate;
		}

		public void setSizeRate(int sizeRate) {
			this.sizeRate = sizeRate;
		}

		public Shape getShape() {
			return shape;
		}

		public void setShape(Shape shape) {
			this.shape = shape;
		}

		public Color getColor() {
			return color;
		}

		public void setColor(Color color) {
			this.color = color;
		}

		public double getAngle() {
			return angle;
		}

		public void setAngle(double angle) {
			this.angle = angle;
		}
	}
}
