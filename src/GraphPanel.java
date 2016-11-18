import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Hehongliang on 2016/11/13.
 */
public class GraphPanel extends JPanel implements MouseListener {
	private static final Vec2 graivty = new Vec2(0, -10);
	private RunThread thread;
	float timeStep = 1.0f / 60.0f;
	int velocityIterations = 6;
	int positionIterations = 2;

	private World world;

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
        length = getMinLength();
        rowHeight = 1.0 * length / rowNum;
        int x = (int) (1.0 * e.getX() / rowHeight);
        int y = (int) (1.0 * e.getY() / rowHeight);
        if(dataSource.curmode== toolBoxPanel.mode.gizmo) {
            Gizmo temp = new Gizmo(x, y, 2, dataSource.getShape(), dataSource.getGizmoColor());
            components.add(temp);
        }else if(dataSource.curmode== toolBoxPanel.mode.rotate){
            for(int i=0;i<components.size();i++)
            {
                Gizmo temp=components.get(i);
                int tempX=temp.getX();
                int tempY=temp.getY();
                int sizeRate=temp.getSizeRate();
                if(x>=tempX&&x<tempX+sizeRate&&y>=tempY&&y<tempY+sizeRate)
                {
                    //world.destroyBody(components.get(i).getBody());
                    temp.setAngle(temp.getAngle()+90);
                    //TODO
                }
            }
        }

		getGraphics().clearRect(0, 0, getWidth(), getHeight());
		//repaint(0,0,getWidth(),getHeight());
		paintComponent(getGraphics());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	enum Shape {
		Triangle, Rectangle, Circle, Paddle, Ball
	}

	private final static int rowNum = 20;
	private java.util.List<Gizmo> components = new ArrayList();
	private int length = 200;
	private double rowHeight = 10.0;
	private toolBoxPanel dataSource;

	public GraphPanel() {
		world = new World(graivty);
		Gizmo.setWorld(world);
		Gizmo.setRowNum(rowNum);
		for (int i = 0; i <= 1; i++)
			for (int j = 0; j <= 1; j++)
				Gizmo.addSingleBoarder(i, j);
		addMouseListener(this);
	}

	public void setDataSource(toolBoxPanel panel) {
		dataSource = panel;
	}

	public void build() {
		if (dataSource.isBuildMode()) {
			thread.interrupt();
		} else {
			thread = new RunThread();
			thread.start();
		}
	}

	public int getMinLength() {
		return Math.min(getHeight(), getWidth()) - 15;
	}

	@Override
	public void paintComponent(Graphics g) {
		length = getMinLength();
		Graphics2D g2D = (Graphics2D) g;
		rowHeight = 1.0 * length / rowNum;
		for (int i = 0; i <= rowNum; i++) {
			Line2D row = new Line2D.Double(0, rowHeight * i, length, rowHeight * i);
			Line2D col = new Line2D.Double(rowHeight * i, 0, rowHeight * i, length);
			g2D.draw(row);     //绘画横线
			g2D.draw(col);     //绘画纵线
		}

		double px, py;
		int x, y;
		int sizeRate;
		for (int i = 0; i < components.size(); i++) {
			Gizmo gizmo = components.get(i);
			g2D.setColor(gizmo.getColor());
			x = gizmo.getX();
			y = gizmo.getY();
            sizeRate=gizmo.getSizeRate();
			g2D.setTransform(getTransform(0, 0, 0));
			if (dataSource.isBuildMode()) {
				px = Coordinate(x);
				py = Coordinate(y);
                if(gizmo.getShape()==Shape.Triangle){
                   g2D.setTransform(getTransform(px+0.5*sizeRate*rowHeight,py+0.5*sizeRate*rowHeight,gizmo.getAngle()));
                }
			} else {
				Vec2 position = gizmo.getBody().getPosition();
				if (gizmo.getShape() != Shape.Ball) {
					px = position.x / Gizmo.getLength() * length - gizmo.getSizeRate() * rowHeight / 2.0f;
					py = position.y / Gizmo.getLength() * length + gizmo.getSizeRate() * rowHeight / 2.0f;
				} else {
					px = position.x / Gizmo.getLength() * length - rowHeight / 4.0f;
					py = position.y / Gizmo.getLength() * length + rowHeight / 4.0f;
				}
				py = length - py;
				g2D.setTransform(getTransform(px, py, gizmo.getBody().getAngle()));
			}
			sizeRate = gizmo.getSizeRate();
			switch (gizmo.getShape()) {
				case Triangle:
					g2D.fill(paintTriangle(px, py, sizeRate));
					break;
				case Rectangle:
					g2D.fill(paintSquare(px, py, sizeRate));
					break;
				case Circle:
					g2D.fill(paintCircle(px, py, sizeRate));
					break;
				case Paddle:
					g2D.fill(paintPaddle(px, py));
					break;
				case Ball:
					g2D.fill(paintBall(px, py));
					break;
			}
		}
	}

	private AffineTransform getTransform(double x, double y, double angle) {
		AffineTransform transform = new AffineTransform();
		transform.rotate(angle * Math.PI / 180, x, y);
		return transform;
	}

	private double Coordinate(int i)//获取真实坐标
	{
		return i * rowHeight;
	}

	private Ellipse2D paintCircle(double x, double y, int size)
	//(x,y)是圆形左上角的坐标，diameter是直径
	{
		double diameter = Coordinate(size);
		Ellipse2D circle = new Ellipse2D.Double(x, y, diameter, diameter);
		return circle;
	}

	private Rectangle2D paintSquare(double x, double y, int size)
	//(x,y)是正方形左上角的坐标，height是边长
	{
		double height = Coordinate(size);
		Rectangle2D square = new Rectangle2D.Double(x, y, height, height);
		return square;
	}

	private GeneralPath paintTriangle(double x, double y, int size)
	//(x,y)是直角三角形左上角的坐标，length是直角边长默认直角在左下角
	{
		double height = Coordinate(size);
		GeneralPath triangle = new GeneralPath();
		triangle.moveTo(x, y);
		triangle.lineTo(x, y + height);
		triangle.lineTo(x + height, y + height);
		triangle.lineTo(x, y);
		return triangle;
	}


	private RoundRectangle2D paintPaddle(double x, double y) {
		double X = x + 0.75 * rowHeight;
		double Y = y;
		double length = 0.25 * rowHeight;
		double weight = Coordinate(2);
		RoundRectangle2D d = new RoundRectangle2D.Double(X, Y, length, weight, 0.5 * length, 0.5 * length);
		return d;
	}

	private Ellipse2D paintBall(double x, double y) {
		Ellipse2D circle = new Ellipse2D.Double(x, y, rowHeight / 2, rowHeight / 2);
		return circle;
	}

	class RunThread extends Thread {
		@Override
		public void run() {
			while (!dataSource.isBuildMode()) {
				world.step(timeStep, velocityIterations, positionIterations);
                getGraphics().clearRect(0, 0, getWidth(), getHeight());
                paintComponent(getGraphics());
				try {
					Thread.sleep((long) (timeStep * 1000));
				} catch (InterruptedException e) {
					break;
				}
			}
		}
	}
}
