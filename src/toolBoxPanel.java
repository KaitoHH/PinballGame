import com.sun.corba.se.impl.orbutil.graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by Cach on 2016/11/14.
 */
public class toolBoxPanel extends JPanel {
    enum mode {
        gizmo, rotate,delete
    }

    enum rotation {
        left, right
    }

    private JButton playButton;
    mode curmode;
    private int sizeRate = 1;
    private boolean buildMode;
    rotation rotate;
    GraphPanel.Shape shape;
    Color gizmoColor;


    public rotation getRotate() {
        return rotate;
    }

    public GraphPanel.Shape getShape() {
        return shape;
    }

    public Color getGizmoColor() {
        return gizmoColor;
    }

    public int getSizeRate() {
        return sizeRate;
    }

    java.util.List<ToolBoxButton> compoments;

    public toolBoxPanel(GraphPanel panel) {
        setLayout(null);
        //更改游戏模式，开始游戏，暂停游戏切换回Build Model
        playButton = new JButton("Play!");
        Font font = new Font("等线light", Font.ITALIC, 12);
        playButton.setFont(font);
        playButton.setBounds(0, 400, 70, 30);
        add(playButton);
        buildMode = true;
        //playButton的鼠标事件
        playButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                buildMode = !buildMode;
                if (buildMode == false) {
                    playButton.setText("Pause");
                } else {
                    playButton.setText("Play!");
                }
                panel.build();
            }
        });

        ToolBoxButton circleButton = new ToolBoxButton(new ImageIcon("PinballRes/Circle_Green.png"));
        circleButton.setBounds(0, 0, 30, 30);
        circleButton.setShape(GraphPanel.Shape.Ball);
        circleButton.setGizmoColor(Color.green);
        add(circleButton);

        ToolBoxButton boxButton = new ToolBoxButton(new ImageIcon("PinballRes/square.png"));
        boxButton.setBounds(40, 0, 30, 30);
        add(boxButton);
        boxButton.setShape(GraphPanel.Shape.Rectangle);
        boxButton.setGizmoColor(Color.BLUE);

        ToolBoxButton purpleCircleButton = new ToolBoxButton(new ImageIcon("PinballRes/Purple_Circle.png"));
        purpleCircleButton.setBounds(0, 40, 30, 30);
        add(purpleCircleButton);
        purpleCircleButton.setShape(GraphPanel.Shape.Circle);
        purpleCircleButton.setGizmoColor(Color.PINK);

        ToolBoxButton blankButton = new ToolBoxButton(new ImageIcon("PinballRes/White_Blank.png"));
        blankButton.setBounds(40, 40, 30, 30);
        add(blankButton);
        blankButton.setShape(GraphPanel.Shape.Rectangle);
        blankButton.setGizmoColor(Color.WHITE);

        ToolBoxButton rctangleButton = new ToolBoxButton(new ImageIcon("PinballRes/Ruler_Triangle_E.png"));
        rctangleButton.setBounds(0, 80, 30, 30);
        add(rctangleButton);
        rctangleButton.setGizmoColor(Color.BLACK);
        rctangleButton.setShape(GraphPanel.Shape.Triangle);

        ToolBoxButton redCircleButton = new ToolBoxButton(new ImageIcon("PinballRes/Red_Circle.png"));
        redCircleButton.setBounds(40, 80, 30, 30);
        add(redCircleButton);
        redCircleButton.setShape(GraphPanel.Shape.Circle);
        redCircleButton.setGizmoColor(Color.RED);

        ToolBoxButton grayButton = new ToolBoxButton(new ImageIcon("PinballRes/Gray_Circle.png"));
        grayButton.setBounds(0, 120, 30, 30);
        add(grayButton);
        grayButton.setGizmoColor(Color.GRAY);
        grayButton.setShape(GraphPanel.Shape.Circle);

        ToolBoxButton planePaddleButton = new ToolBoxButton(new ImageIcon("PinballRes/stick.png"));
        planePaddleButton.setBounds(0, 160, 70, 30);
        add(planePaddleButton);
        planePaddleButton.setShape(GraphPanel.Shape.Track);
        planePaddleButton.setGizmoColor(Color.ORANGE);

        ToolBoxButton upPaddleButton = new ToolBoxButton(new ImageIcon("PinballRes/stick2.png"));
        upPaddleButton.setBounds(0, 210, 70, 30);
        add(upPaddleButton);
        upPaddleButton.setGizmoColor(Color.yellow);
        upPaddleButton.setShape(GraphPanel.Shape.Paddle);
        upPaddleButton.setRotate(rotation.left);

        ToolBoxButton downPaddleButton = new ToolBoxButton(new ImageIcon("PinballRes/stick_1.png"));
        downPaddleButton.setBounds(0, 260, 70, 30);
        add(downPaddleButton);
        downPaddleButton.setGizmoColor(Color.YELLOW);
        downPaddleButton.setShape(GraphPanel.Shape.Paddle);
        downPaddleButton.setRotate(rotation.right);

        ToolBoxButton rotateButton = new ToolBoxButton(new ImageIcon("PinballRes/rotateButtonIcon.png"));
        rotateButton.setBounds(0, 440, 70, 30);
        add(rotateButton);
        rotateButton.addActionListener(e -> {
            curmode = mode.rotate;
        });

        ToolBoxButton deleteButton = new ToolBoxButton(new ImageIcon("PinballRes/Delete_Button.png"));
        deleteButton.setBounds(0, 480, 70, 30);
        add(deleteButton);
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                curmode = mode.delete;
            }
        });

        SpinnerModel model = new SpinnerNumberModel(1, 1, 5, 1);
        JSpinner spinner = new JSpinner(model);
        JTextField textField = new JTextField(2);
        textField.setEditable(false);
        textField.setText("1");
        spinner.setEditor(textField);
        spinner.addChangeListener(e -> {
            sizeRate = (int) spinner.getValue();
            textField.setText(spinner.getValue().toString());
        });
        spinner.setBounds(0, 300, 70, 30);
        add(spinner);

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
                public void mouseReleased(MouseEvent e) {
                    curmode = mode.gizmo;
                    shape = button.getShape();
                    gizmoColor = button.getGizmoColor();
                    rotate = button.getRotate();
                }
            });
        }
    }
}

class ToolBoxButton extends JButton {
    private Color gizmoColor;
    private GraphPanel.Shape shape;
    private toolBoxPanel.rotation rotate;

    enum mode {gizmo, rotate, enlarge, shrink}

    private mode curMode;

    public ToolBoxButton(Icon icon) {
        super(icon);
    }

    public ToolBoxButton(String text) {
        super(text);
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

    public void setRotate(toolBoxPanel.rotation rotate) {
        this.rotate = rotate;
    }

    public toolBoxPanel.rotation getRotate() {
        return rotate;
    }

}