import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.Contact;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.*;
import java.util.ArrayList;

/**
 * Created by Hehongliang on 2016/11/13.
 */
public class GraphPanel extends JPanel {
	private static final Vec2 gravity = new Vec2(0, -10);
	private RunThread thread;
	float timeStep = 1.0f / 60.0f;
	int velocityIterations = 6;
	int positionIterations = 2;

	private World world;

	enum Shape {
		Triangle, Rectangle, Circle, Paddle, Ball, Track, Absorber,Slider
	}

	private final static int rowNum = 20;
	private java.util.List<Gizmo> components = new ArrayList();
	private int length = 200;
	private double rowHeight = 10.0;
	private toolBoxPanel dataSource;

	public GraphPanel() {
		requestFocus();
		world = new World(gravity);
		Gizmo.setWorld(world);
		Gizmo.setRowNum(rowNum);
		for (int i = 0; i <= 1; i++)
			for (int j = 0; j <= 1; j++)
				Gizmo.addSingleBoarder(i, j);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				length = getMinLength();
				rowHeight = 1.0 * length / rowNum;
				int x = (int) (1.0 * e.getX() / rowHeight);
				int y = (int) (1.0 * e.getY() / rowHeight);
				int sizeRate = dataSource.getSizeRate();
				if (dataSource.curmode == toolBoxPanel.mode.gizmo) {
					if (dataSource.getShape() == Shape.Ball)
						sizeRate = 1;
					else if (dataSource.getShape() == Shape.Paddle)
						sizeRate = 2;
					if (canAdd(x, y, sizeRate)) {
						Gizmo temp = new Gizmo(x, y, sizeRate, dataSource.getShape(), dataSource.getGizmoColor(), dataSource.getRotate());
						components.add(temp);
					}
				} else if (dataSource.curmode == toolBoxPanel.mode.rotate) {
					Gizmo gizmo = getGizmo(x, y);
					if (gizmo != null) {
						gizmo.setAngle(gizmo.getAngle() + Math.PI / 2);
						gizmo.updateBody();
					}
				} else if (dataSource.curmode == toolBoxPanel.mode.delete) {
					Gizmo gizmo = getGizmo(x, y);
					if (gizmo != null) {
						world.destroyBody(gizmo.getBody());
						components.remove(gizmo);
					}
				}
				updateScreen();
			}
		});
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == ' ') {
					for (Gizmo gizmo : components) {
						if (gizmo.getShape() == Shape.Paddle) {
							gizmo.applyForce();
						}
					}
				}
			}
		});
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				requestFocus();
			}
		});
	}

	public boolean canAdd(int x, int y, int size) {
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (getGizmo(i, j) != null) {
					return false;
				}
			}
		}
		return true;
	}

	public Gizmo getGizmo(int x, int y) {
		for (int i = 0; i < components.size(); i++) {
			Gizmo temp = components.get(i);
			int tempX = temp.getX();
			int tempY = temp.getY();
			int sizeRate = temp.getSizeRate();
			if (x >= tempX && x < tempX + sizeRate && y >= tempY && y < tempY + sizeRate)
				return temp;
		}
		return null;
	}

	public void setDataSource(toolBoxPanel panel) {
		dataSource = panel;
	}

	public void build() {
		if (dataSource.isBuildMode()) {
			thread.interrupt();
			for (Gizmo gizmo : components) {
				gizmo.updateBody();
			}
		} else {
			thread = new RunThread();
			thread.start();
			this.requestFocus();
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
			if (gizmo.getBody().getUserData() == null) {
				world.destroyBody(gizmo.getBody());
				continue;
			}
			g2D.setColor(gizmo.getColor());
			x = gizmo.getX();
			y = gizmo.getY();
			sizeRate = gizmo.getSizeRate();
			g2D.setTransform(getTransform(0, 0, 0));
			if (dataSource.isBuildMode()) {
				px = Coordinate(x);
				py = Coordinate(y);
				if (gizmo.getShape() == Shape.Triangle) {
					g2D.setTransform(getTransform(px + 0.5 * sizeRate * rowHeight, py + 0.5 * sizeRate * rowHeight, gizmo.getAngle()));
				}
			} else {
				Vec2 position = gizmo.getBody().getPosition();
				px = position.x / Gizmo.getLength() * length;
				py = position.y / Gizmo.getLength() * length;
				if (gizmo.getShape() != Shape.Ball) {
					if (gizmo.getShape() == Shape.Paddle) {
						if (gizmo.getRotate() == toolBoxPanel.rotation.left) {
							px -= 0.875 * rowHeight;
							py += rowHeight;
						} else {
							px -= 0.125 * rowHeight;
							py += rowHeight;
						}
					} else {
						px -= gizmo.getSizeRate() * rowHeight / 2.0f;
						py += gizmo.getSizeRate() * rowHeight / 2.0f;
					}
				} else {
					px -= rowHeight / 4.0f;
					py += rowHeight / 4.0f;
				}
				py = length - py;
				//g2D.setTransform(getTransform(px, py, gizmo.getBody().getAngle()));
				if (gizmo.getShape() == Shape.Paddle)
					if (gizmo.getRotate() == toolBoxPanel.rotation.left)
						g2D.setTransform(getTransform(px + 0.875 * rowHeight, py + rowHeight, -gizmo.getBody().getAngle()));
					else
						g2D.setTransform(getTransform(px + 0.125 * rowHeight, py + rowHeight, -gizmo.getBody().getAngle()));
				else if (gizmo.getShape() != Shape.Ball)
					g2D.setTransform(getTransform(px + 0.5 * sizeRate * rowHeight, py + 0.5 * sizeRate * rowHeight, -gizmo.getBody().getAngle()));

			}
			sizeRate = gizmo.getSizeRate();
			switch (gizmo.getShape()) {
				case Triangle:
					g2D.fill(paintTriangle(px, py, sizeRate));
					break;
				case Rectangle:
                case Track:
				    g2D.fill(paintSquare(px, py, sizeRate));
                    break;
                case Slider:
					g2D.fill(paintSlider(px,py));
					break;
				case Circle:
					g2D.fill(paintCircle(px, py, sizeRate));
					break;
				case Paddle:
					g2D.fill(paintPaddle(px, py, gizmo.getRotate()));
					break;
				case Ball:
					g2D.fill(paintBall(px, py));
					break;
                case Absorber:
                    g2D.fill(paintSquare(px, py, sizeRate));
                    g2D.setColor(Color.red);
                    g2D.draw(paintSmallcircle(px,py,sizeRate*0.7,sizeRate));
                    break;
			}
		}
	}

	private AffineTransform getTransform(double x, double y, double angle) {
		AffineTransform transform = new AffineTransform();
		transform.rotate(angle, x, y);
		return transform;
	}

	private double Coordinate(double i)//获取真实坐标
	{
		return i * rowHeight;
	}

	private Ellipse2D paintCircle(double x, double y, double size)
	//(x,y)是圆形中心的坐标，diameter是直径
	{
		double diameter = Coordinate(size);
		Ellipse2D circle = new Ellipse2D.Double(x, y, diameter, diameter);
		return circle;
	}

	private Ellipse2D paintSmallcircle(double x,double y,double size,double sizeRate)
    {
        double xx = x+0.5*Coordinate(sizeRate);
        double yy = y+0.5*Coordinate(sizeRate);
        double px=xx-0.5*Coordinate(size);
        double py=yy-0.5*Coordinate(size);
        double diameter=Coordinate(size);
        Ellipse2D circle=new Ellipse2D.Double(px,py,diameter,diameter);
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


	private RoundRectangle2D paintPaddle(double x, double y, toolBoxPanel.rotation rotate) {
		double X, Y;
		double length = 0.25 * rowHeight;
		double weight = Coordinate(2);
		if (rotate == toolBoxPanel.rotation.left) {
			X = x + 0.75 * rowHeight;
			Y = y;
		} else {
			X = x;
			Y = y;
		}
		RoundRectangle2D d = new RoundRectangle2D.Double(X, Y, length, weight, 0.5 * length, 0.5 * length);
		return d;
	}

	private RoundRectangle2D paintSlider(double x,double y){
        double length=Coordinate(1);
        double weight=Coordinate(0.25);
        double px=x;
        double py=y+Coordinate(0.75);
        RoundRectangle2D slider=new RoundRectangle2D.Double(px,py,length,weight,0.25*length,0.5*length);
        return slider;
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
				updateScreen();
				try {
					Thread.sleep((long) (timeStep * 1000));
				} catch (InterruptedException e) {
					break;
				}
			}
			updateScreen();
		}
	}

	private void updateScreen() {
		if (SystemStructure.isWindows()) {
			getGraphics().clearRect(0, 0, getWidth(), getHeight());
			paintComponent(getGraphics());
		} else {
			repaint();
		}
	}

	public void newScene() {
		components.clear();
		updateScreen();
	}

	public void saveScene(){

	}

	public void loadScene(){

	}
}
