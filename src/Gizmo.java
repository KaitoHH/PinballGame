import javafx.scene.shape.Circle;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

import java.awt.*;


/**
 * Project: PinballGame
 * Author: KaitoHH
 * Create Date: 2016/11/18
 * Description:
 * All rights reserved.
 */
public class Gizmo {
	private static World world;
	private static int size = 5;
	private static int rowNum;

	private Body body;
	private int x;
	private int y;
	private int sizeRate = 1;
	private GraphPanel.Shape shape;
	private Color color;
	private double angle = 0 * Math.PI / 180;

	public static void setWorld(World world) {
		Gizmo.world = world;
	}

	public static int getLength() {
		return size * rowNum;
	}

	public static void setSize(int size) {
		Gizmo.size = size;
	}

	public static void setRowNum(int rowNum) {
		Gizmo.rowNum = rowNum;
	}

	public Gizmo(int x, int y, int sizeRate, GraphPanel.Shape shape, Color color) {
		this.x = x;
		this.y = y;
		this.sizeRate = sizeRate;
		this.shape = shape;
		this.color = color;
		createBody(shape, x, y, sizeRate);
	}

	public Body getBody() {
		return body;
	}

	private void createBody(GraphPanel.Shape shape, int x, int y, int sizeRate) {
		switch (shape) {
			case Triangle:
				addSquare(x, y, sizeRate);
				break;
			case Rectangle:
				addSquare(x, y, sizeRate);
				break;
			case Circle:
				addCircle(x, y, sizeRate);
				break;
			case Ball:
				addBall(x, y);
				break;
		}
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

	public GraphPanel.Shape getShape() {
		return shape;
	}

	public void setShape(GraphPanel.Shape shape) {
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

	public static void addSingleBoarder(int x, int y) {
		BodyDef def = new BodyDef();
		def.type = BodyType.STATIC;
		def.position.set(x * size * rowNum, y * size * rowNum);
		Body body = world.createBody(def);
		PolygonShape box = new PolygonShape();
		if (y == x) {
			box.setAsBox(size * rowNum, 0);
		} else {
			box.setAsBox(0, size * rowNum);
		}
		body.createFixture(box, 0);
	}


	public void addSquare(int x, int y, int sizeRate) {
		y = 20 - y;
		BodyDef def = new BodyDef();
		def.type = BodyType.DYNAMIC;
		def.gravityScale = 0;
		int a = sizeRate * size;
		def.position.set(x * size + a / 2.0f, y * size - a / 2.0f);
		body = world.createBody(def);
		PolygonShape box = new PolygonShape();
		box.setAsBox(a / 2.0f, a / 2.0f);
		body.createFixture(box, 2);
	}


	private void addCircle(int x, int y, int sizeRate) {
		y = 20 - y;
		BodyDef def = new BodyDef();
		def.type = BodyType.DYNAMIC;
		def.gravityScale = 0;
		int r = sizeRate * size;
		def.position.set(x * size + r / 2.0f, y * size - r / 2.0f);
		body = world.createBody(def);
		CircleShape circle = new CircleShape();
		circle.setRadius(r / 2.0f);
		body.createFixture(circle, 2);
	}

	private void addBall(int x, int y) {
		y = 20 - y;
		BodyDef def = new BodyDef();
		def.type = BodyType.DYNAMIC;
		float r = size / 2.0f;
		def.position.set(x * size + r / 2.0f, y * size - r / 2.0f);
		body = world.createBody(def);
		CircleShape circle = new CircleShape();
		circle.setRadius(r / 2.0f);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.restitution = 0.8f;
		body.createFixture(fixtureDef);
	}
}
