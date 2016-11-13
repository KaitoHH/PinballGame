import org.jbox2d.collision.RayCastInput;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

/**
 * Project: PinballGame
 * Author: KaitoHH
 * Create Date: 2016/11/12
 * Description:
 * All rights reserved.
 */
public class TestB2D {
	public static void main(String[] args) {
		World world = new World(new Vec2(0.0f, -10.0f));

		//Ground Body
		//Step 1
		BodyDef groundBodyDef = new BodyDef();
		groundBodyDef.position.set(0.0f, -10.0f);

		//Step 2
		Body groundBody = world.createBody(groundBodyDef);

		//Step 3
		PolygonShape groundBox = new PolygonShape();
		groundBox.setAsBox(50.0f, 10.0f);

		//Step 4
		groundBody.createFixture(groundBox, 0.0f);


		//Dynamic Body
		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(0.0f, 4.0f);
		bodyDef.type = BodyType.DYNAMIC;

		Body body = world.createBody(bodyDef);

		PolygonShape dynamicBox = new PolygonShape();
		dynamicBox.setAsBox(1.0f, 1.0f);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = dynamicBox;
		fixtureDef.friction = 0.3f;
		fixtureDef.density = 1.0f;
		body.createFixture(fixtureDef);


		float timeStep = 1.0f / 60.0f;
		int velocityIterations = 8;
		int positionIterations = 3;

		for (int i = 0; i < 120; i++) {
			world.step(timeStep, velocityIterations, positionIterations);
			Vec2 position = body.getPosition();
			float angle = body.getAngle();
			System.out.printf("%4.2f %4.2f %4.2f\n", position.x, position.y, angle);
		}

		RayCastInput input = new RayCastInput();
		
	}
}
