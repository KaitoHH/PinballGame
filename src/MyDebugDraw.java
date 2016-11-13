import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.common.*;

/**
 * Project: PinballGame
 * Author: KaitoHH
 * Create Date: 2016/11/13
 * Description:
 * All rights reserved.
 */
public class MyDebugDraw extends DebugDraw{
	public MyDebugDraw() {
		super(new OBBViewportTransform());
	}

	@Override
	public void drawPoint(Vec2 vec2, float v, Color3f color3f) {
		System.out.println(1);
		System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName());
	}

	@Override
	public void drawSolidPolygon(Vec2[] vec2s, int i, Color3f color3f) {
		System.out.println(2);
		System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName());
	}

	@Override
	public void drawCircle(Vec2 vec2, float v, Color3f color3f) {
		System.out.println(3);
		System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName());
	}

	@Override
	public void drawSolidCircle(Vec2 vec2, float v, Vec2 vec21, Color3f color3f) {
		System.out.println(4);
		System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName());
	}

	@Override
	public void drawSegment(Vec2 vec2, Vec2 vec21, Color3f color3f) {
		System.out.println(5);
		System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName());
	}

	@Override
	public void drawTransform(Transform transform) {
		System.out.println(6);
		System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName());
	}

	@Override
	public void drawString(float v, float v1, String s, Color3f color3f) {
		System.out.println(7);
		System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName());
	}
}
