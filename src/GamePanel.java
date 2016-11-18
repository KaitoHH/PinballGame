import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;
import org.jbox2d.pooling.arrays.FloatArray;
import org.jbox2d.pooling.arrays.IntArray;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
	float timeStep = 1.0f / 60.0f;
	int velocityIterations = 6;
	int positionIterations = 2;

	Image image;

	World world;
	int size;
	GameDraw debugDraw;

	public GamePanel() {
		size = 72;
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addBall(e.getX()/10, e.getY()/10);
			}
		});
	}

	public void setUpBroder() {
		addSingleBroder(0, 0);
		addSingleBroder(0, 1);
		addSingleBroder(1, 0);
		addSingleBroder(1, 1);
	}

	private World getWorld() {
		return world;
	}

	private void addSingleBroder(int x, int y) {
		BodyDef def = new BodyDef();
		def.type = BodyType.STATIC;
		def.position.set(x * size, x * size);
		Body body = getWorld().createBody(def);
		PolygonShape box = new PolygonShape();
		if (y == 0) {
			box.setAsBox(size, 0.1f);
		} else {
			box.setAsBox(0.1f, size);
		}
		body.createFixture(box, 0);
	}

	public void addBall(int x, int y) {
		BodyDef bodydef = new BodyDef();
		bodydef.type = BodyType.DYNAMIC;
		bodydef.position.set(x, y);
		Body body = world.createBody(bodydef);
		CircleShape shape = new CircleShape();
		shape.setRadius(10);
		body.createFixture(shape, 1);
	}

	public void setUp() {
		debugDraw = new GameDraw(size);
		Vec2 gravity = new Vec2(0, -50);
		world = new World(gravity);

		// Dynamic Body
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DYNAMIC;
		bodyDef.position.set(size / 2, size / 2);
		Body body = world.createBody(bodyDef);
		PolygonShape dynamicBox = new PolygonShape();
		dynamicBox.setAsBox(1, 1);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = dynamicBox;
		fixtureDef.restitution = 0.9f;
		fixtureDef.density = 1;
		fixtureDef.friction = 0.3f;
		body.createFixture(fixtureDef);

		// Dynamic Body
		BodyDef bodyDef2 = new BodyDef();
		bodyDef2.type = BodyType.DYNAMIC;
		bodyDef2.position.set(0, 10);
		bodyDef2.gravityScale = 0;
		Body body2 = world.createBody(bodyDef2);
		PolygonShape dynamicBox2 = new PolygonShape();
		dynamicBox2.set(new Vec2[]{new Vec2(-5, 10), new Vec2(-5, 12), new Vec2(10, 12)}, 3);
		FixtureDef fixtureDef2 = new FixtureDef();
		fixtureDef2.shape = dynamicBox2;
		fixtureDef2.density = 3;
		fixtureDef2.friction = 0.3f;
		body2.createFixture(fixtureDef2);

		setUpBroder();

		debugDraw.setFlags(debugDraw.e_shapeBit);
		world.setDebugDraw(debugDraw);
		while (true) {
			world.step(timeStep, velocityIterations, positionIterations);
			image = createImage(getWidth(), getHeight());
			debugDraw.setImage(image);
			world.drawDebugData();
			repaint();
			try {
				Thread.sleep((long) (timeStep * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.clearRect(0, 0, getWidth(), getHeight());
		g.drawImage(image, 0, 0, this);
	}

}
