package SwingGUI;

import Functions.Check;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class registerFrameBoss extends registerFrame {
    registerFrameBoss(JFrame origin) {
        super(origin);
        this.setTitle("商品信息管理系统-公司注册");
        registerButton.addMouseListener(new registerBossMouseListener());
    }

    class registerBossMouseListener implements MouseListener {
        String password = userPassword.getText();
        String passwordAgain = userPasswordAgain.getText();
        String name = userName.getText();
        String company = companyName.getText();

        @Override
        public void mouseClicked(MouseEvent e) {
            int flag = new Check().CheckBossRegister(password, passwordAgain, name, company);
            if (flag == 1) {
                setVisible(false);
                new succeedDialog();
            } else if (flag == 2) {
                new faultDialog("密码错误");
            } else if (flag == 3) {
                new faultDialog("账号存在");
            } else {
                new faultDialog("公司存在");
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            ImageIcon pressedIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\注册3.png");
            registerButton.setIcon(pressedIcon);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            registerButton.setIcon(icon);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ImageIcon enterIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\注册2.png");
            registerButton.setIcon(enterIcon);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            registerButton.setIcon(icon);
        }
    }
}
