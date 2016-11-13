import org.jbox2d.callbacks.*;
import org.jbox2d.collision.shapes.*;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;

import javax.swing.*;

public class Main {
	private static DebugDraw debugDraw;

	public static DebugDraw getDebugDraw() {
		return debugDraw;
	}

	public static void main(String[] args) {
		/*JPanel panel = new JPanel();
		debugDraw = new GameDebugDraw(panel);
		JFrame frame = new JFrame();
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/


		debugDraw = new MyDebugDraw();
		Vec2 gravity = new Vec2(0, -10);
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
		bodyDef.position.set(0, 4);
		Body body = world.createBody(bodyDef);
		PolygonShape dynamicBox = new PolygonShape();
		dynamicBox.setAsBox(1, 1);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = dynamicBox;
		fixtureDef.density = 1;
		fixtureDef.friction = 0.3f;
		body.createFixture(fixtureDef);

// Setup world
		float timeStep = 1.0f / 60.0f;
		int velocityIterations = 6;
		int positionIterations = 2;

		debugDraw.setFlags(debugDraw.e_shapeBit);
		world.setDebugDraw(debugDraw);
// Run loop
		for (int i = 0; i < 60; ++i) {
			world.step(timeStep, velocityIterations, positionIterations);
			Vec2 position = body.getPosition();
			float angle = body.getAngle();
			world.drawDebugData();
			System.out.println(i + ": X: " + position.x + " Y: " + position.y + " ANGLE: " + angle);
		}

	}
}