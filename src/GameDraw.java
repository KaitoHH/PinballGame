import javafx.scene.shape.Circle;
import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.common.*;
import org.jbox2d.pooling.arrays.IntArray;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Project: PinballGame
 * Author: KaitoHH
 * Create Date: 2016/11/18
 * Description:
 * All rights reserved.
 */
public class GameDraw extends DebugDraw {
	private final Vec2 temp = new Vec2();
	private final IntArray xIntsPool = new IntArray();
	private final IntArray yIntsPool = new IntArray();
	private int square;
	Image image;

	public GameDraw(int square) {
		super(new OBBViewportTransform());
		this.square = square;
		getViewportTranform().setYFlip(true);
		getViewportTranform().setCenter(8f, square - 9);
	}

	public void setImage(Image image) {
		this.image = image;
		getGraphics().setColor(Color.pink);
		getGraphics().fillRect(0, 0, image.getWidth(null), image.getHeight(null));
	}

	private Graphics getGraphics() {
		return image.getGraphics();
	}

	@Override
	public void drawPoint(Vec2 vec2, float v, Color3f color3f) {

	}

	@Override
	public void drawSolidPolygon(Vec2[] vertices, int vertexCount, Color3f color) {
		Graphics g = getGraphics();
		int[] xInts = xIntsPool.get(vertexCount);
		int[] yInts = yIntsPool.get(vertexCount);

		for (int i = 0; i < vertexCount; i++) {
			getWorldToScreenToOut(vertices[i], temp);
			xInts[i] = (int) Math.round(temp.x / 1) + 10;
			xInts[i] *= 10;
			yInts[i] = (int) Math.round(temp.y / 1) + 10;
			yInts[i] *= 10;
		}

		g.setColor(Color.getHSBColor(color.x, color.y, color.z));
		g.fillPolygon(xInts, yInts, vertexCount);

		// outside
		drawPolygon(vertices, vertexCount, color);
	}

	@Override
	public void drawCircle(Vec2 vec2, float v, Color3f color3f) {

	}

	@Override
	public void drawSolidCircle(Vec2 vec2, float v, Vec2 vec21, Color3f color) {
		vec2 = getWorldToScreen(vec2);
		vec2.x += 10;
		vec2.y += 10;
		vec2.x *= 10;
		vec2.y *= 10;
		Graphics2D g2d = (Graphics2D) getGraphics();
		g2d.setColor(Color.getHSBColor(color.x, color.y, color.z));
		Ellipse2D circle = new Ellipse2D.Float(vec2.x - v, vec2.y - v, v * 2, v * 2);
		g2d.fill(circle);
	}

	@Override
	public void drawSegment(Vec2 vec2, Vec2 vec21, Color3f color3f) {

	}

	@Override
	public void drawTransform(Transform transform) {

	}

	@Override
	public void drawString(float v, float v1, String s, Color3f color3f) {

	}
}
