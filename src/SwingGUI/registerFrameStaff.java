package SwingGUI;

import Functions.Check;
import Functions.Register;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

public class registerFrameStaff extends registerFrame {
    registerFrameStaff(loginFrame origin) {
        super(origin);
        this.setTitle("商品信息管理系统-员工注册");
        registerButton.addMouseListener(new registerStaffMouseListener());
    }

    class registerStaffMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            String password = userPassword.getText();
            String passwordAgain = userPasswordAgain.getText();
            String name = userName.getText();
            String company = companyName.getText();
            int flag = 0;
            try {
                flag = new Check(origin.conn).CheckStaffRegister(password, passwordAgain, name, company);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if (flag == 1) {

                try {
                    new Register(origin.conn).RegisterStaff(name, password, company);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                InitTextField();
                setVisible(false);
                new succeedDialog(name);
            } else if (flag == 2) {
                new faultDialog("密码错误");
            } else if (flag == 3) {
                new faultDialog("账号存在");
            } else if (flag == 4) {
                new faultDialog("公司不存在");
            } else {
                new faultDialog("未填写完整");
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
