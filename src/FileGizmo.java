import java.awt.*;
import java.io.Serializable;

/**
 * Project: PinballGame
 * Author: KaitoHH
 * Create Date: 2016/11/20
 * Description:
 * All rights reserved.
 */
public class FileGizmo implements Serializable {
	private int x;
	private int y;
	private int sizeRate = 1;
	private toolBoxPanel.rotation rotate;
	private GraphPanel.Shape shape;
	private Color color;
	private double angle = 0 * Math.PI / 180;

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

	public toolBoxPanel.rotation getRotate() {
		return rotate;
	}

	public void setRotate(toolBoxPanel.rotation rotate) {
		this.rotate = rotate;
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
}