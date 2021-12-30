package SwingGUI;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;

public class succeedDialog extends JDialog {
    final int width = 275, height = 150;
    //登录信息
    String name;
    String path = "src\\SwingGUI\\img\\";
    loginFrame origin;
    //按钮
    ImageIcon nextIcon = new ImageIcon(path + "继续.png");
    JButton nextButton = new JButton(nextIcon);

    //标签
    JLabel welcome = new JLabel("注册成功!");

    ImageIcon succeedIcon = new ImageIcon(path + "注册成功.png");
    JLabel succeed = new JLabel(succeedIcon);

    succeedDialog(loginFrame origin, String name) {
        this.name = name;
        setTitle("商品信息管理系统-注册成功");
        setVisible(true);
        setBounds(250, 150, width, height);
        setResizable(false);
        setLayout(null);

        add(succeed);
        add(welcome);
        succeed.setBounds(35, 40, 40, 40);
        welcome.setBounds(85, 40, 100, 40);

        add(nextButton);
        nextButton.setBounds(175, 40, 40, 40);
        nextButton.setContentAreaFilled(false);
        nextButton.setFocusPainted(false);
        nextButton.setBorderPainted(false);
        nextButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                try {
                    new indexFrame(origin, name);
                } catch (IOException | SQLException ioException) {
                    ioException.printStackTrace();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                ImageIcon pressedIcon = new ImageIcon(path + "继续3.png");
                nextButton.setIcon(pressedIcon);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                nextButton.setIcon(nextIcon);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ImageIcon enteredIcon = new ImageIcon(path + "继续2.png");
                nextButton.setIcon(enteredIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                nextButton.setIcon(nextIcon);
            }
        });
    }
}
