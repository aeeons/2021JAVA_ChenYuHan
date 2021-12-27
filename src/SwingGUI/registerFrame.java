package SwingGUI;

import javax.swing.*;

public class registerFrame extends JFrame {
    //设置大小
    final int width = 600, height = 305;

    //容器
    JPanel root = new JPanel();

    //标签
    JLabel welcomeLabel = new JLabel("欢迎注册商品信息管理系统");
    JLabel userNameLabel = new JLabel("账号");
    JLabel userPasswordLabel = new JLabel("密码");
    JLabel userPasswordAgainLabel = new JLabel("确认密码");
    JLabel companyNameLabel = new JLabel("公司");

    //文本框
    JTextField userName = new JTextField();
    JPasswordField userPassword = new JPasswordField();
    JPasswordField userPasswordAgain = new JPasswordField();
    JTextField companyName = new JTextField();

    //按钮
    ImageIcon icon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\注册.png");
    JButton registerButton = new JButton(icon);

    void Init() {
        setBounds(100, 100, width, height);
        setVisible(true);
        setResizable(false);
        root.setLayout(null);
        this.setContentPane(root);

        InitLabel();
        InitTextField();
        InitButton();
    }

    void InitLabel() {
        this.add(welcomeLabel);
        this.add(userNameLabel);
        this.add(userPasswordLabel);
        this.add(userPasswordAgainLabel);
        this.add(companyNameLabel);

        final int labelWidth = 100, labelHeight = 40;
        welcomeLabel.setBounds(100, 20, 400, 40);
        userNameLabel.setBounds(60, 60, labelWidth, labelHeight);
        userPasswordLabel.setBounds(60, 100, labelWidth, labelHeight);
        userPasswordAgainLabel.setBounds(60, 140, labelWidth, labelHeight);
        companyNameLabel.setBounds(60, 180, labelWidth, labelHeight);
    }

    void InitTextField() {
        this.add(userName);
        this.add(userPassword);
        this.add(userPasswordAgain);
        this.add(companyName);

        final int textFieldWidth = 200, textFieldHeight = 40;
        userName.setBounds(140, 60, textFieldWidth, textFieldHeight);
        userPassword.setBounds(140, 100, textFieldWidth, textFieldHeight);
        userPasswordAgain.setBounds(140, 140, textFieldWidth, textFieldHeight);
        companyName.setBounds(140, 180, textFieldWidth, textFieldHeight);
    }

    void InitButton() {
        registerButton.setContentAreaFilled(false);
        registerButton.setFocusPainted(false);

        this.add(registerButton);

        registerButton.setBounds(400, 70, 120, 120);
    }

}
