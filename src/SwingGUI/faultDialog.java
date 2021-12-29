package SwingGUI;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class faultDialog extends JDialog {
    final int width = 275, height = 150;

    //按钮
    ImageIcon returnIcon = new ImageIcon("src\\SwingGUI\\img\\密码错误.png");
    JButton returnButton = new JButton(returnIcon);

    //标签
    JLabel welcome = new JLabel();


    faultDialog(String message) {
        setTitle("商品信息管理系统-注册失败");
        setVisible(true);
        setBounds(250, 150, width, height);
        setResizable(false);
        setLayout(null);
        welcome.setText(message);
        add(welcome);
        welcome.setBounds(105, 10, 100, 40);

        add(returnButton);
        returnButton.setBounds(108, 50, 40, 40);
        returnButton.setContentAreaFilled(false);
        returnButton.setFocusPainted(false);
        returnButton.setBorderPainted(false);
        returnButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                ImageIcon pressedIcon = new ImageIcon("src\\SwingGUI\\img\\返回2.png");
                returnButton.setIcon(pressedIcon);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                returnButton.setIcon(returnIcon);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ImageIcon enteredIcon = new ImageIcon("src\\SwingGUI\\img\\返回.png");
                returnButton.setIcon(enteredIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                returnButton.setIcon(returnIcon);
            }
        });
    }
}