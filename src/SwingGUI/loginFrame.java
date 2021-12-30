package SwingGUI;

import Functions.Check;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class loginFrame extends JFrame {
    //基本信息----
    final int width = 600;              //窗口宽度
    final int height = 305;             //窗口高度
    loginFrame root = this;
    Connection conn;
    String path = "src\\SwingGUI\\img\\";

    //文本框----
    JTextField userName = new JTextField();                 //账号输入框
    JPasswordField userPassword = new JPasswordField();     //密码输入框

    //标签----
    JLabel welcomeLabel = new JLabel("欢迎使用 商品信息管理系统 ，祝您工作愉快");
    JLabel userNameLabel = new JLabel("账号");
    JLabel userPasswordLabel = new JLabel("密码");

    //按钮----
    ImageIcon loginIcon = new ImageIcon(path + "登录.png");          //登录
    JButton loginButton = new JButton(loginIcon);

    ImageIcon regBossIcon = new ImageIcon(path + "公司注册.png");//公司注册
    JButton regBossButton = new JButton(regBossIcon);

    ImageIcon regStaffIcon = new ImageIcon(path + "员工注册.png");//个人注册
    JButton regStaffButton = new JButton(regStaffIcon);

    //注册界面----
    registerFrameBoss registerBoss = new registerFrameBoss(this);
    registerFrameStaff registerStaff = new registerFrameStaff(this);

    public loginFrame(Connection conn) {
        //信息初始化
        this.conn = conn;

        //窗口初始化
        setTitle("商品信息管理系统-登录");                      //设置窗口标题
        setVisible(true);                                   //窗口默认可见
        setResizable(false);                                //窗口大小固定
        setLayout(null);                                    //使用绝对布局
        setBounds(100, 100, width, height);            //设置窗口大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     //关闭窗口后：结束程序

        //组件初始化
        InitLabel();        //初始化标签
        InitButton();       //初始化按钮
        InitTextField();    //初始化文本框
    }

    void InitLabel() {
        //添加标签到窗口
        add(userNameLabel);
        add(userPasswordLabel);
        add(welcomeLabel);

        //设置标签位置
        final int labelWidth = 40;  //设置标签宽度,这里的标签是32*32大小的图片
        final int labelHeight = 40; //设置标签高度

        userNameLabel.setBounds(60, 60, labelWidth, labelHeight);
        userPasswordLabel.setBounds(60, 120, labelWidth, labelHeight);
        welcomeLabel.setBounds(100, 20, 400, 40);
    }

    void InitButton() {
        //按钮设置
        loginButton.setFocusable(false);               //按钮不能获得焦点，下同
        loginButton.setFocusPainted(false);            //取消焦点状态，下同
        loginButton.setBorderPainted(false);           //取消边框，下同
        loginButton.setContentAreaFilled(false);       //关闭背景填充，下同

        regBossButton.setFocusable(false);
        regBossButton.setFocusPainted(false);
        regBossButton.setBorderPainted(false);
        regBossButton.setContentAreaFilled(false);

        regStaffButton.setFocusable(false);
        regStaffButton.setFocusPainted(false);
        regStaffButton.setBorderPainted(false);
        regStaffButton.setContentAreaFilled(false);

        //添加按钮到窗口
        add(loginButton);
        add(regBossButton);
        add(regStaffButton);

        //设置按钮位置
        loginButton.setBounds(400, 70, 140, 140);
        regBossButton.setBounds(130, 165, 70, 70);
        regStaffButton.setBounds(240, 165, 70, 70);

        //添加按钮事件
        loginButton.addMouseListener(new loginMouseListener());
        regStaffButton.addMouseListener(new registerStaffMouseListener());
        regBossButton.addMouseListener(new registerBossMouseListener());
    }

    void InitTextField() {
        //添加文本框到窗口
        add(userName);
        add(userPassword);

        final int textWidth = 230;  //设置文本框宽度
        final int textHeight = 30;  //设置文本框高度
        //设置标签位置
        userName.setBounds(120, 70, textWidth, textHeight);
        userPassword.setBounds(120, 130, textWidth, textHeight);
    }

    //登录按钮事件
    class loginMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                String name = userName.getText();
                String password = userPassword.getText();
                //检查当前账号和密码是否正确
                if (new Check(conn).CheckLogin(name, password)) {
                    setVisible(false);
                    new indexFrame(root, name);
                } else {//如果不正确则报错
                    new faultDialog("账号或密码错误，请检查后再试");
                }
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            ImageIcon pressedIcon = new ImageIcon(path + "登录3.png");
            loginButton.setIcon(pressedIcon);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            loginButton.setIcon(loginIcon);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ImageIcon enterIcon = new ImageIcon(path + "登录2.png");
            loginButton.setIcon(enterIcon);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            loginButton.setIcon(loginIcon);
        }
    }

    //打开个人注册窗口
    class registerStaffMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            setVisible(false);
            registerStaff.setVisible(true);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            ImageIcon pressedIcon = new ImageIcon(path + "员工注册3.png");
            regStaffButton.setIcon(pressedIcon);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            regStaffButton.setIcon(regStaffIcon);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ImageIcon enterIcon = new ImageIcon(path + "员工注册2.png");
            regStaffButton.setIcon(enterIcon);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            regStaffButton.setIcon(regStaffIcon);
        }
    }

    //打开公司注册窗口
    class registerBossMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            setVisible(false);
            registerBoss.setVisible(true);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            ImageIcon pressedIcon = new ImageIcon(path + "公司注册3.png");
            regBossButton.setIcon(pressedIcon);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            regBossButton.setIcon(regBossIcon);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ImageIcon enterIcon = new ImageIcon(path + "公司注册2.png");
            regBossButton.setIcon(enterIcon);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            regBossButton.setIcon(regBossIcon);
        }
    }
}