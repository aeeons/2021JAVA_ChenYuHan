package SwingGUI;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class faultDialog extends JDialog {
    //基本信息----
    final int width = 275;          //设置弹窗宽度
    final int height = 150;         //设置弹窗高度
    String path = "src\\SwingGUI\\img\\";

    //标签
    JLabel welcome = new JLabel();

    //按钮----
    ImageIcon returnIcon = new ImageIcon(path + "密码错误.png");
    JButton returnButton = new JButton(returnIcon);

    faultDialog(String message) {
        //信息初始化
        welcome.setText(message);

        //窗口设置
        setTitle("商品信息管理系统");                       //设置窗口标题
        setVisible(true);                                //窗口默认可见
        setResizable(false);                             //窗口大小固定
        setLayout(null);                                 //使用绝对布局
        setBounds(250, 150, width, height);         //设置窗口大小

        //按钮设置
        returnButton.setFocusable(false);               //按钮不能获得焦点，下同
        returnButton.setFocusPainted(false);            //取消焦点状态，下同
        returnButton.setBorderPainted(false);           //取消边框，下同
        returnButton.setContentAreaFilled(false);       //关闭背景填充，下同

        //添加按钮和标签到弹窗中
        add(welcome);
        add(returnButton);

        //设置按钮和标签位置
        welcome.setBounds(20, 10, 400, 40);
        returnButton.setBounds(108, 50, 40, 40);

        //添加按钮事件
        returnButton.addMouseListener(new returnMouseListener());
    }

    //释放当前窗口
    class returnMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            dispose();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            ImageIcon pressedIcon = new ImageIcon(path + "返回2.png");
            returnButton.setIcon(pressedIcon);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            returnButton.setIcon(returnIcon);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ImageIcon enteredIcon = new ImageIcon(path + "返回.png");
            returnButton.setIcon(enteredIcon);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            returnButton.setIcon(returnIcon);
        }
    }
}