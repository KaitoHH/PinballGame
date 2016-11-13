import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.collision.AABB;
import org.jbox2d.common.Color3f;
import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.common.OBBViewportTransform;
import org.jbox2d.pooling.arrays.IntArray;
import org.jbox2d.pooling.arrays.Vec2Array;

public class GameDebugDraw extends DebugDraw {

	private JPanel panel;
	public static int circlePoints = 13;


	public GameDebugDraw(JPanel contentPane) {
		super(new OBBViewportTransform());
		panel = contentPane;
		// TODO Auto-generated constructor stub
	}

	private final Vec2Array vec2Array = new Vec2Array();

	@Override
	public void drawCircle(Vec2 center, float radius, Color3f color) {
		System.out.println(1);
		Vec2[] vecs = vec2Array.get(circlePoints);
		generateCirle(center, radius, vecs, circlePoints);
		drawPolygon(vecs, circlePoints, color);
	}

	@Override
	public void drawPoint(Vec2 argPoint, float argRadiusOnScreen, Color3f argColor) {
		System.out.println(1);
		getWorldToScreenToOut(argPoint, sp1);
		Graphics g = getGraphics();
		g.setColor(Color.GREEN);
		sp1.x -= argRadiusOnScreen;
		sp1.y -= argRadiusOnScreen;
		g.fillOval((int) (sp1.x / 1), (int) (sp1.y / 1), (int) argRadiusOnScreen * 2, (int) argRadiusOnScreen * 2);
	}


	private final Vec2 sp1 = new Vec2();
	private final Vec2 sp2 = new Vec2();

	@Override
	public void drawSegment(Vec2 p1, Vec2 p2, Color3f color) {
		System.out.println(1);
		getWorldToScreenToOut(p1, sp1);
		getWorldToScreenToOut(p2, sp2);
		Graphics g = getGraphics();
		g.setColor(Color.BLUE);
		g.drawLine((int) (sp1.x / 1), (int) (sp1.y / 1), (int) (sp2.x / 1), (int) (sp2.y / 1));
	}

	@Override
	public void drawString(float arg0, float arg1, String arg2, Color3f arg3) {
		// TODO Auto-generated method stub
		System.out.println(1);

	}

	private Graphics getGraphics() {
		return panel.getGraphics();
	}

	public void drawAABB(AABB argAABB, Color3f color) {
		Vec2 vecs[] = vec2Array.get(4);
		argAABB.getVertices(vecs);
		drawPolygon(vecs, 4, color);
	}

	private final Vec2 saxis = new Vec2();

	@Override
	public void drawSolidCircle(Vec2 center, float radius, Vec2 axis, Color3f color) {
		System.out.println(1);
		Vec2[] vecs = vec2Array.get(circlePoints);
		generateCirle(center, radius, vecs, circlePoints);
		drawSolidPolygon(vecs, circlePoints, color);
		if (axis != null) {
			saxis.set(axis).mulLocal(radius).addLocal(center);
			drawSegment(center, saxis, color);
		}
	}

	// TODO change IntegerArray to a specific class for int[] arrays
	private final Vec2 temp = new Vec2();
	private final static IntArray xIntsPool = new IntArray();
	private final static IntArray yIntsPool = new IntArray();

	@Override
	public void drawSolidPolygon(Vec2[] vertices, int vertexCount, Color3f color) {
		System.out.println(1);
		// inside
		Graphics g = getGraphics();
		int[] xInts = xIntsPool.get(vertexCount);
		int[] yInts = yIntsPool.get(vertexCount);

		for (int i = 0; i < vertexCount; i++) {
			getWorldToScreenToOut(vertices[i], temp);
			xInts[i] = (int) (temp.x / 1);
			yInts[i] = (int) (temp.y / 1);
		}

		g.setColor(Color.GREEN);
		g.fillPolygon(xInts, yInts, vertexCount);

		// outside
		drawPolygon(vertices, vertexCount, color);
	}

	@Override
	public void drawTransform(Transform xf) {
	}

	// CIRCLE GENERATOR
	private void generateCirle(Vec2 argCenter, float argRadius, Vec2[] argPoints, int argNumPoints) {
		System.out.println(1);
		float inc = MathUtils.TWOPI / argNumPoints;
		for (int i = 0; i < argNumPoints; i++) {
			argPoints[i].x = (argCenter.x + MathUtils.cos(i * inc) * argRadius);
			argPoints[i].y = (argCenter.y + MathUtils.sin(i * inc) * argRadius);
		}
	}


}