import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;
import org.jbox2d.pooling.arrays.IntArray;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.TimerTask;
import java.util.Timer;

/**
 * Project: PinballGame
 * Author: KaitoHH
 * Create Date: 2016/11/13
 * Description:
 * All rights reserved.
 */
public class GamePanel extends JPanel {
	private int x;
	private int y;
	boolean flag;

	float timeStep = 1.0f / 60.0f;
	int velocityIterations = 6;
	int positionIterations = 2;

	Image image;

	public GamePanel() {
		//setBackground(Color.PINK);
		x = y = 0;
		flag = true;
	}

	public void setUp() {
		Draw debugDraw = new Draw();
		Vec2 gravity = new Vec2(0, -50);
		boolean doSleep = true;
		World world = new World(gravity);
		BodyDef groundBodyDef = new BodyDef();
		groundBodyDef.position.set(0, -10);
		Body groundBody = world.createBody(groundBodyDef);
		PolygonShape groundBox = new PolygonShape();
		groundBox.setAsBox(50, 10);
		groundBody.createFixture(groundBox, 0);

// Dynamic Body
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DYNAMIC;
		bodyDef.position.set(0, 60);
		Body body = world.createBody(bodyDef);
		PolygonShape dynamicBox = new PolygonShape();
		dynamicBox.setAsBox(1, 1);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = dynamicBox;
		fixtureDef.restitution = 0.5f;
		fixtureDef.density = 1;
		fixtureDef.friction = 0.3f;
		body.createFixture(fixtureDef);

// Setup world
		debugDraw.setFlags(debugDraw.e_shapeBit);
		world.setDebugDraw(debugDraw);

		for (int i = 0; i < 600; ++i) {
			world.step(timeStep, velocityIterations, positionIterations);
			Vec2 position = body.getPosition();
			float angle = body.getAngle();
			image = createImage(getWidth(), getHeight());
			world.drawDebugData();
			repaint();
			System.out.println(i + ": X: " + position.x + " Y: " + position.y + " ANGLE: " + angle);
			try {
				Thread.sleep(1000 / 60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.clearRect(0, 0, getWidth(), getHeight());
		g.drawImage(image, 0, 0, null);
	}


	public Graphics get_Graphics() {
		return image.getGraphics();
	}

	class Draw extends DebugDraw {
		private final Vec2 temp = new Vec2();
		private final IntArray xIntsPool = new IntArray();
		private final IntArray yIntsPool = new IntArray();

		public Draw() {
			super(new OBBViewportTransform());
		}

		@Override
		public void drawPoint(Vec2 vec2, float v, Color3f color3f) {
			Graphics g = getGraphics();

		}

		@Override
		public void drawSolidPolygon(Vec2[] vertices, int vertexCount, Color3f color) {
			Graphics g = get_Graphics();
			int[] xInts = xIntsPool.get(vertexCount);
			int[] yInts = yIntsPool.get(vertexCount);

			for (int i = 0; i < vertexCount; i++) {
				getWorldToScreenToOut(vertices[i], temp);
				xInts[i] = (int) (temp.x / 1) + 10;
				xInts[i] *= 10;
				yInts[i] = (int) (temp.y / 1) + 10;
				yInts[i] *= 10;
			}

			g.setColor(Color.GREEN);
			g.fillPolygon(xInts, yInts, vertexCount);

			// outside
			drawPolygon(vertices, vertexCount, color);
		}

		@Override
		public void drawCircle(Vec2 vec2, float v, Color3f color3f) {

		}

		@Override
		public void drawSolidCircle(Vec2 vec2, float v, Vec2 vec21, Color3f color3f) {

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
}
