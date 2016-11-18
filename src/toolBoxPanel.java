import com.sun.corba.se.impl.orbutil.graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by Cach on 2016/11/14.
 */
public class toolBoxPanel extends JPanel {
	private JButton playButton;
	private ToolBoxButton circleButton;
	private ToolBoxButton boxButton;
	private ToolBoxButton rctangleButton;
	private ToolBoxButton redCircleButton;
	private ToolBoxButton purpleCircleButton;
	private ToolBoxButton blankButton;
	private ToolBoxButton grayButton;
	private ToolBoxButton planePaddleButton;
	private ToolBoxButton upPaddleButton;
	private ToolBoxButton downPaddleButton;
	private ToolBoxButton rotateButton;
	private GraphPanel panel;

	private boolean buildMode;
	GraphPanel.Shape shape;
	Color gizmoColor;
	int rotation;

	public GraphPanel.Shape getShape() {
		return shape;
	}

	public Color getGizmoColor() {
		return gizmoColor;
	}

	public int getRotation() {
		return rotation;
	}

	java.util.List<ToolBoxButton> compoments;

	public toolBoxPanel(GraphPanel panel) {
		this.panel = panel;
		setLayout(null);
		//更改游戏模式，开始游戏，暂停游戏切换回Build Model
		playButton = new JButton("Play!");
		Font font = new Font("等线light", Font.ITALIC, 12);
		playButton.setFont(font);
		playButton.setBounds(0, 200, 70, 30);
		add(playButton);
		buildMode = true;
		//playButton的鼠标事件
		playButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buildMode = !buildMode;
				if (buildMode == false) {
					playButton.setText("Pause");
					panel.build();
				} else {
					playButton.setText("Play!");
				}
			}
		});

		circleButton = new ToolBoxButton(new ImageIcon("PinballRes/Circle_Green.png"));
		circleButton.setBounds(0, 0, 30, 30);
		circleButton.setShape(GraphPanel.Shape.Circle);
		circleButton.setGizmoColor(Color.green);
		add(circleButton);

		boxButton = new ToolBoxButton(new ImageIcon("PinballRes/square.png"));
		boxButton.setBounds(40, 0, 30, 30);
		add(boxButton);
		boxButton.setShape(GraphPanel.Shape.Rectangle);
		boxButton.setGizmoColor(Color.BLUE);

		purpleCircleButton = new ToolBoxButton(new ImageIcon("PinballRes/Purple_Circle.png"));
		purpleCircleButton.setBounds(0, 40, 30, 30);
		add(purpleCircleButton);
		purpleCircleButton.setShape(GraphPanel.Shape.Circle);
		purpleCircleButton.setGizmoColor(Color.PINK);

		blankButton = new ToolBoxButton(new ImageIcon("PinballRes/White_Blank.png"));
		blankButton.setBounds(40, 40, 30, 30);
		add(blankButton);
		blankButton.setShape(GraphPanel.Shape.Rectangle);
		blankButton.setGizmoColor(Color.WHITE);

		rctangleButton = new ToolBoxButton(new ImageIcon("PinballRes/Ruler_Triangle.png"));
		rctangleButton.setBounds(0, 80, 30, 30);
		add(rctangleButton);
		rctangleButton.setGizmoColor(Color.BLACK);
		rctangleButton.setShape(GraphPanel.Shape.Rectangle);

		redCircleButton = new ToolBoxButton(new ImageIcon("PinballRes/Red_Circle.png"));
		redCircleButton.setBounds(40, 80, 30, 30);
		add(redCircleButton);
		redCircleButton.setShape(GraphPanel.Shape.Circle);
		redCircleButton.setGizmoColor(Color.RED);

		grayButton = new ToolBoxButton(new ImageIcon("PinballRes/Gray_Circle.png"));
		grayButton.setBounds(0, 120, 30, 30);
		add(grayButton);
		grayButton.setGizmoColor(Color.GRAY);
		grayButton.setShape(GraphPanel.Shape.Circle);

		planePaddleButton = new ToolBoxButton(new ImageIcon("PinballRes/stick.png"));
		planePaddleButton.setBounds(40, 120, 30, 30);
		add(planePaddleButton);
		planePaddleButton.setShape(GraphPanel.Shape.Rectangle);
		planePaddleButton.setGizmoColor(Color.YELLOW);
		planePaddleButton.setRotation(0);

		upPaddleButton = new ToolBoxButton(new ImageIcon("PinballRes/stick2.png"));
		upPaddleButton.setBounds(0, 160, 30, 30);
		add(upPaddleButton);
		upPaddleButton.setGizmoColor(Color.yellow);
		upPaddleButton.setShape(GraphPanel.Shape.Paddle);
		upPaddleButton.setRotation(3);

		downPaddleButton = new ToolBoxButton(new ImageIcon("PinballRes/stick_1.png"));
		downPaddleButton.setBounds(40, 160, 30, 30);
		add(downPaddleButton);
		downPaddleButton.setGizmoColor(Color.YELLOW);
		downPaddleButton.setShape(GraphPanel.Shape.Paddle);
		downPaddleButton.setRotation(1);

		rotateButton = new ToolBoxButton(new ImageIcon("PinballRes/rotateButtonIcon.png"));
		rotateButton.setBounds(0, 240, 70, 30);
		add(rotateButton);


//将button组成一个队列，方便重载button类的属性
		compoments = new ArrayList();
		compoments.add(circleButton);
		compoments.add(redCircleButton);
		compoments.add(boxButton);
		compoments.add(purpleCircleButton);
		compoments.add(blankButton);
		compoments.add(rctangleButton);
		compoments.add(grayButton);
		compoments.add(planePaddleButton);
		compoments.add(upPaddleButton);
		compoments.add(downPaddleButton);
		compoments.add(rotateButton);

		addButtonActionListener();

		this.setPreferredSize(new Dimension(100, 100));
	}

	public boolean isBuildMode() {
		return buildMode;
	}

	private void addButtonActionListener() {
		for (ToolBoxButton button : compoments) {
			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					shape = button.getShape();
					gizmoColor = button.getGizmoColor();
					rotation = button.getRotation();
				}
			});
		}
	}
}

class ToolBoxButton extends JButton {
	private Color gizmoColor;
	private GraphPanel.Shape shape;
	private int rotation;

	public ToolBoxButton(Icon icon) {
		super(icon);
	}

	public Color getGizmoColor() {
		return gizmoColor;
	}

	public void setGizmoColor(Color gizmoColor) {
		this.gizmoColor = gizmoColor;
	}

	public GraphPanel.Shape getShape() {
		return shape;
	}

	public void setShape(GraphPanel.Shape shape) {
		this.shape = shape;
	}

	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}
}