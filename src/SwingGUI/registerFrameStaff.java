package SwingGUI;

import Functions.Check;
import Functions.Register;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

public class registerFrameStaff extends registerFrame {
    registerFrameStaff(loginFrame origin) {
        //信息初始化
        super(origin);                              //调用父类构造方法
        this.setTitle("商品信息管理系统-员工注册");      //设置窗口标题

        //添加按钮事件
        registerButton.addMouseListener(new registerStaffMouseListener());
    }

    class registerStaffMouseListener implements MouseListener {
        int flag;               //保存查询结果
        String name;            //姓名
        String company;         //公司
        String password;        //密码
        String passwordAgain;   //确认密码

        //更新信息
        void updateInfo() {
            password = userPassword.getText();
            passwordAgain = userPasswordAgain.getText();
            name = userName.getText();
            company = companyName.getText();
            flag = 0;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            updateInfo();
            try {
                flag = new Check(origin.conn).CheckStaffRegister(password, passwordAgain, name, company);
                switch (flag) {
                    case 1:         //为1说明注册成功
                        new Register(origin.conn).RegisterStaff(name, password, company);
                        InitTextField();
                        setVisible(false);
                        new succeedDialog(origin, name);
                        break;
                    case 2:
                        new faultDialog("密码错误");
                        break;
                    case 3:
                        new faultDialog("账号存在");
                        break;
                    case 4:
                        new faultDialog("公司不存在");
                        break;
                    default:
                        new faultDialog("未填写完整");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            ImageIcon pressedIcon = new ImageIcon(path + "注册3.png");
            registerButton.setIcon(pressedIcon);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            registerButton.setIcon(registerIcon);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ImageIcon enterIcon = new ImageIcon(path + "注册2.png");
            registerButton.setIcon(enterIcon);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            registerButton.setIcon(registerIcon);
        }
    }
}
