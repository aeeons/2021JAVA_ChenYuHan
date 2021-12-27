package SwingGUI;

import javax.swing.*;

public class loginFrame extends JFrame {
    final int width = 600, height = 305;

    //容器
    JPanel root = new JPanel();

    //文本框
    JTextField userName = new JTextField();                 //账号输入框
    JPasswordField userPassword = new JPasswordField();     //密码输入框

    //标签
    JLabel welcomeLabel = new JLabel("欢迎使用 商品信息管理系统 ，祝您工作愉快");
    JLabel userNameLabel = new JLabel("登录");
    JLabel userPasswordLabel = new JLabel("密码");

    //按钮

    JButton loginButton = new JButton(new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\登录.png"));
    JButton registerBossButton = new JButton("公司注册");
    JButton registerStaffButton = new JButton("员工注册");

    void Init() {
        this.setTitle("商品信息管理系统-登录");
        setBounds(100, 100, width, height);
        setVisible(true);
        setResizable(false);
        root.setLayout(null);
        this.setContentPane(root);

        //加载文本框z
        InitTextField();
        InitLabel();
        InitButton();
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

        registerBossButton.setFocusPainted(false);
        registerBossButton.setContentAreaFilled(false);

        registerStaffButton.setFocusPainted(false);
        registerStaffButton.setContentAreaFilled(false);


        //加载按钮
        this.add(loginButton);
        this.add(registerBossButton);
        this.add(registerStaffButton);

        //设置按钮位置
        loginButton.setBounds(400, 70, 100, 100);
        registerBossButton.setBounds(120, 180, 100, 40);
        registerStaffButton.setBounds(250, 180, 100, 40);
        //
    }
}