import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.testbed.framework.TestbedModel;
import org.jbox2d.testbed.framework.TestbedSettings;
import org.jbox2d.testbed.framework.TestbedTest;

public class MJWTest2 extends TestbedTest {

	@Override
	public void initTest(boolean argDeserialized) {
		setTitle("Super Couple of Things Test");
		getWorld().setGravity(new Vec2(0f, 0f));
		for (int i = 0; i < 1; i++) {
			PolygonShape polygonShape = new PolygonShape();
			polygonShape.setAsBox(1, 2);
			BodyDef bodyDef = new BodyDef();
			bodyDef.type = i == 0 ? BodyType.DYNAMIC : BodyType.STATIC;
			bodyDef.position.set(5 * i, 0);
			bodyDef.angle = (float) (Math.PI / 4 * i);
			bodyDef.allowSleep = false;
			Body body = getWorld().createBody(bodyDef);
			body.createFixture(polygonShape, 5.0f);
			//body.applyForce(new Vec2(-10000 * (i - 1), 0), new Vec2());
			//body.applyForce(new Vec2(-10000,0),new Vec2(0,-3));
		}
	}

	@Override
	public String getTestName() {
		return "Couple of Things";
	}


}