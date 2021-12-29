package SwingGUI;

import Functions.Check;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class loginFrame extends JFrame {
    final int width = 600, height = 305;
    loginFrame root = this;
    Connection conn;
    //文本框
    JTextField userName = new JTextField();                 //账号输入框
    JPasswordField userPassword = new JPasswordField();     //密码输入框
    //标签
    JLabel welcomeLabel = new JLabel("欢迎使用 商品信息管理系统 ，祝您工作愉快");
    JLabel userNameLabel = new JLabel("登录");
    JLabel userPasswordLabel = new JLabel("密码");

    //按钮
    ImageIcon loginIcon = new ImageIcon("src\\SwingGUI\\img\\登录.png");
    JButton loginButton = new JButton(loginIcon);

    ImageIcon registerBossIcon = new ImageIcon("src\\SwingGUI\\img\\公司注册.png");
    JButton registerBossButton = new JButton(registerBossIcon);

    ImageIcon registerStaffIcon = new ImageIcon("src\\SwingGUI\\img\\员工注册.png");
    JButton registerStaffButton = new JButton(registerStaffIcon);

    //弹窗
    public static class faultDialog extends JDialog {
        final int width = 275, height = 100;

        //标签
        JLabel welcome = new JLabel("账号或密码错误，请检查后再试");

        //按钮
        ImageIcon faultIcon = new ImageIcon("src\\SwingGUI\\img\\密码错误.png");
        JButton returnButton = new JButton(faultIcon);

        faultDialog() {
            setTitle("商品信息管理系统-登陆失败");
            setVisible(false);
            setBounds(150, 150, width, height);
            setResizable(false);
            setLayout(null);

            add(welcome);
            welcome.setBounds(10, 10, 300, 40);

            add(returnButton);
            returnButton.setBounds(200, 10, 40, 40);
            returnButton.setContentAreaFilled(false);
            returnButton.setFocusPainted(false);
            returnButton.setBorderPainted(false);
            returnButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    ImageIcon pressedIcon = new ImageIcon("src\\SwingGUI\\img\\返回2.png");
                    returnButton.setIcon(pressedIcon);
                    setVisible(false);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    ImageIcon pressedIcon = new ImageIcon("src\\SwingGUI\\img\\返回2.png");
                    returnButton.setIcon(pressedIcon);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    returnButton.setIcon(faultIcon);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    ImageIcon enteredIcon = new ImageIcon("src\\SwingGUI\\img\\返回.png");
                    returnButton.setIcon(enteredIcon);

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    returnButton.setIcon(faultIcon);
                }
            });
        }
    }

    faultDialog dialog = new faultDialog();

    //注册界面
    registerFrameBoss registerBoss = new registerFrameBoss(this);
    registerFrameStaff registerStaff = new registerFrameStaff(this);

    public loginFrame(Connection conn) {
        this.conn = conn;
        this.setTitle("商品信息管理系统-登录");
        setBounds(100, 100, width, height);
        setVisible(true);
        setResizable(false);
        setLayout(null);

        //加载文本框z
        InitTextField();
        InitLabel();
        InitButton();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void InitTextField() {
        //添加到容器
        this.add(userName);
        this.add(userPassword);

        final int textWidth = 230, textHeight = 30, textMargenTop = 30;
        final int startX = 120, startY = 70;
        //设置标签位置
        userName.setBounds(startX, startY, textWidth, textHeight);
        userPassword.setBounds(startX, startY + textHeight + textMargenTop, textWidth, textHeight);
    }

    void InitLabel() {
        this.add(userNameLabel);
        this.add(userPasswordLabel);
        this.add(welcomeLabel);
        final int labelWidth = 40, labelHeight = 40;
        userNameLabel.setBounds(60, 60, labelWidth, labelHeight);
        userPasswordLabel.setBounds(60, 120, labelWidth, labelHeight);
        welcomeLabel.setBounds(100, 20, 400, 40);
    }

    void InitButton() {
        //按钮设置
        loginButton.setContentAreaFilled(false);
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setFocusable(false);

        registerBossButton.setFocusPainted(false);
        registerBossButton.setContentAreaFilled(false);
        registerBossButton.setBorderPainted(false);
        registerBossButton.setFocusable(false);

        registerStaffButton.setFocusPainted(false);
        registerStaffButton.setContentAreaFilled(false);
        registerStaffButton.setBorderPainted(false);
        registerStaffButton.setFocusable(false);

        //加载按钮
        this.add(loginButton);
        this.add(registerBossButton);
        this.add(registerStaffButton);

        //设置按钮位置
        loginButton.setBounds(400, 70, 140, 140);
        registerBossButton.setBounds(130, 165, 70, 70);
        registerStaffButton.setBounds(240, 165, 70, 70);

        loginButton.addMouseListener(new loginMouseListener());
        registerStaffButton.addMouseListener(new registerStaffMouseListener());
        registerBossButton.addMouseListener(new registerBossMouseListener());
    }

    class loginMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                String name = userName.getText();
                String password = userPassword.getText();
                if (new Check(conn).CheckLogin(name, password)) {
                    setVisible(false);
                    try {
                        new indexFrame(root, name);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } else {
                    dialog.setVisible(true);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            ImageIcon pressedIcon = new ImageIcon("src\\SwingGUI\\img\\登录3.png");
            loginButton.setIcon(pressedIcon);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            loginButton.setIcon(loginIcon);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ImageIcon enterIcon = new ImageIcon("src\\SwingGUI\\img\\登录2.png");
            loginButton.setIcon(enterIcon);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            loginButton.setIcon(loginIcon);
        }
    }

    class registerStaffMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            setVisible(false);
            registerStaff.setVisible(true);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            ImageIcon pressedIcon = new ImageIcon("src\\SwingGUI\\img\\员工注册3.png");
            registerStaffButton.setIcon(pressedIcon);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            registerStaffButton.setIcon(registerStaffIcon);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ImageIcon enterIcon = new ImageIcon("src\\SwingGUI\\img\\员工注册2.png");
            registerStaffButton.setIcon(enterIcon);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            registerStaffButton.setIcon(registerStaffIcon);
        }
    }

    class registerBossMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            setVisible(false);
            registerBoss.setVisible(true);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            ImageIcon pressedIcon = new ImageIcon("src\\SwingGUI\\img\\公司注册3.png");
            registerBossButton.setIcon(pressedIcon);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            registerBossButton.setIcon(registerBossIcon);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ImageIcon enterIcon = new ImageIcon("src\\SwingGUI\\img\\公司注册2.png");
            registerBossButton.setIcon(enterIcon);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            registerBossButton.setIcon(registerBossIcon);
        }
    }
}