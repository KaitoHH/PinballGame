import javax.swing.*;
import java.awt.*;

/**
 * Created by Cach on 2016/11/14.
 */
public class toolBoxPanel extends JPanel {
    private JButton circleButton;
    private JButton boxButton;
    private JButton rctangleButton;
    private JButton redCircleButton;
    private JButton purpleCircleButton;
    private JButton blankButton;
    private JButton grayButton;
    private JButton planePaddleButton;
    private JButton upPaddleButton;
    private JButton downPaddleButton;

    public toolBoxPanel(){
        setLayout(null);
        circleButton = new JButton(new ImageIcon("PinballRes/Circle_Green.png"));
        circleButton.setBounds(0,0,30,30);
        add(circleButton);

        boxButton=new JButton(new ImageIcon("PinballRes/square.png"));
        boxButton.setBounds(40,0,30,30);
        add(boxButton);

        purpleCircleButton=new JButton(new ImageIcon("PinballRes/Purple_Circle.png"));
        purpleCircleButton.setBounds(0,40,30,30);
        add(purpleCircleButton);

        blankButton=new JButton(new ImageIcon("PinballRes/White_Blank.png"));
        blankButton.setBounds(40,40,30,30);
        add(blankButton);

        rctangleButton=new JButton(new ImageIcon("PinballRes/Right_Tranigle.png"));
        rctangleButton.setBounds(0,80,30,30);
        add(rctangleButton);

        redCircleButton=new JButton(new ImageIcon("PinballRes/Red_Circle.png"));
        redCircleButton.setBounds(40,80,30,30);
        add(redCircleButton);

        grayButton=new JButton(new ImageIcon("PinballRes/Gray_Circle.png"));
        grayButton.setBounds(0,120,30,30);
        add(grayButton);

        planePaddleButton=new JButton(new ImageIcon("PinballRes/stick.png"));
        planePaddleButton.setBounds(40,120,30,30);
        add(planePaddleButton);

        upPaddleButton=new JButton(new ImageIcon("PinballRes/stick2.png"));
        upPaddleButton.setBounds(0,160,30,30);
        add(upPaddleButton);

        downPaddleButton=new JButton(new ImageIcon("PinballRes/stick_1"));
        downPaddleButton.setBounds(40,160,30,30);
        add(downPaddleButton);


        /*boxButton.setBounds(40,0,30,30);
        rctangleButton.setBounds(0,40,30,30);
        redCircleButton.setBounds(40,40,30,30);
        purpleCircleButton.setBounds(0,80,30,30);
        blankButton.setBounds(40,80,30,30);
        grayButton.setBounds(0,120,30,30);
        planePaddleButton.setBounds(40,120,30,30);
        upPaddleButton.setBounds(0,160,30,30);
        downPaddleButton.setBounds(40,160,30,30);*/
    }
}
