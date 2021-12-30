package SwingGUI;

import Functions.Check;
import Functions.Register;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

public class registerFrameBoss extends registerFrame {
    registerFrameBoss(loginFrame origin) {
        //信息初始化
        super(origin);                              //调用父类构造方法
        this.setTitle("商品信息管理系统-公司注册");      //设置窗口标题

        //添加按钮事件
        registerButton.addMouseListener(new registerBossMouseListener());
    }

    class registerBossMouseListener implements MouseListener {
        //基本信息
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
                //查询结果
                flag = new Check(origin.conn).CheckBossRegister(password, passwordAgain, name, company);
                switch (flag) {
                    case 1:         //为1说明注册成功
                        new Register(origin.conn).RegisterBoss(name, password, company);
                        setVisible(false);
                        InitTextField();
                        new succeedDialog(origin, name);
                        break;
                    case 2:
                        new faultDialog("密码错误");
                        break;
                    case 3:
                        new faultDialog("账号存在");
                        break;
                    case 4:
                        new faultDialog("公司存在");
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
            ImageIcon pressedIcon = new ImageIcon("src\\SwingGUI\\img\\注册3.png");
            registerButton.setIcon(pressedIcon);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            registerButton.setIcon(registerIcon);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ImageIcon enterIcon = new ImageIcon("src\\SwingGUI\\img\\注册2.png");
            registerButton.setIcon(enterIcon);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            registerButton.setIcon(registerIcon);
        }
    }
}
