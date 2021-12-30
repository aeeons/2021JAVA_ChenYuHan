package SwingGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class registerFrame extends JFrame {
    //基本信息----
    final int width = 600;          //设置窗口宽度
    final int height = 305;         //设置窗口高度
    loginFrame origin;              //父级窗口
    String path = "src\\SwingGUI\\img\\";

    //文本框----
    JTextField userName = new JTextField("账号");                                 //账号
    myPasswordField userPassword = new myPasswordField("密码");              //密码
    myPasswordField userPasswordAgain = new myPasswordField("确认密码");      //确认密码
    JTextField companyName = new JTextField("公司");                              //公司

    //标签----
    JLabel welcomeLabel = new JLabel("欢迎注册商品信息管理系统");

    ImageIcon nameIcon = new ImageIcon(path + "账号.png");                 //账号标签
    JLabel nameLabel = new JLabel(nameIcon);

    ImageIcon passwordIcon = new ImageIcon(path + "密码.png");             //密码标签
    JLabel passwordLabel = new JLabel(passwordIcon);

    ImageIcon passwordAgainIcon = new ImageIcon(path + "确认密码.png");     //确认密码标签
    JLabel passwordAgainLabel = new JLabel(passwordAgainIcon);

    ImageIcon companyIcon = new ImageIcon(path + "公司.png");               //公司标签
    JLabel companyLabel = new JLabel(companyIcon);

    //按钮----
    ImageIcon registerIcon = new ImageIcon(path + "注册.png");              //注册按钮
    JButton registerButton = new JButton(registerIcon);

    ImageIcon returnIcon = new ImageIcon(path + "密码错误.png");             //返回按钮
    JButton returnButton = new JButton(returnIcon);

    //构造方法----
    registerFrame(loginFrame origin) {
        //信息初始化
        this.origin = origin;

        //窗口初始化
        setBounds(100, 100, width, height);
        setVisible(false);                                  //窗口默认隐藏
        setResizable(false);                                //窗口大小固定
        setLayout(null);                                    //使用绝对布局

        //组件初始化
        InitLabel();        //初始化标签
        InitButton();       //初始化按钮
        InitTextField();    //初始化文本框
    }

    void InitLabel() {
        //添加标签到窗口
        add(welcomeLabel);
        add(nameLabel);
        add(passwordLabel);
        add(passwordAgainLabel);
        add(companyLabel);

        //设置标签位置
        final int labelWidth = 100;     //设置标签宽度
        final int labelHeight = 40;     //设置标签高度
        welcomeLabel.setBounds(100, 20, 400, 40);
        nameLabel.setBounds(65, 57, labelWidth, labelHeight);
        passwordLabel.setBounds(65, 105, labelWidth, labelHeight);
        passwordAgainLabel.setBounds(65, 150, labelWidth, labelHeight);
        companyLabel.setBounds(65, 200, labelWidth, labelHeight);
    }

    void InitButton() {
        //按钮设置
        registerButton.setFocusable(false);               //按钮不能获得焦点，下同
        registerButton.setFocusPainted(false);            //取消焦点状态，下同
        registerButton.setBorderPainted(false);           //取消边框，下同
        registerButton.setContentAreaFilled(false);       //关闭背景填充，下同

        returnButton.setFocusable(false);
        returnButton.setFocusPainted(false);
        returnButton.setBorderPainted(false);
        returnButton.setContentAreaFilled(false);

        //添加按钮到窗口
        add(registerButton);
        add(returnButton);

        //设置按钮位置
        registerButton.setBounds(400, 70, 140, 140);
        returnButton.setBounds(10, 10, 40, 40);

        //添加按钮事件
        returnButton.addMouseListener(new returnButtonMouseListener());
    }

    void InitTextField() {
        //添加文本框到窗口
        add(userName);
        add(userPassword);
        add(userPasswordAgain);
        add(companyName);

        userPassword.Init("密码");
        userPasswordAgain.Init("确认密码");

        //设置文本框大小
        final int textWidth = 200;          //设置文本框宽度
        final int textHeight = 30;          //设置文本框高度
        userName.setBounds(140, 60, textWidth, textHeight);
        userPassword.setBounds(140, 110, textWidth, textHeight);
        userPasswordAgain.setBounds(140, 160, textWidth, textHeight);
        companyName.setBounds(140, 210, textWidth, textHeight);


        RemindTextField(userName, "账号");
        RemindTextField(companyName, "公司");
    }

    //将文本框设置提示信息
    void RemindTextField(JTextField textField, String text) {
        textField.setText(text);
        textField.setForeground(new Color(153, 153, 153));
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                //当点击输入框时，里面的内容为提示信息时，清空内容，将其字体颜色设置为正常黑色。
                if (textField.getText().equals(text)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().length() == 0) {
                    textField.setText(text);
                    textField.setForeground(new Color(153, 153, 153));
                }
            }
        });
    }

    //返回按钮事件
    class returnButtonMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            origin.setVisible(true);
            setVisible(false);
            InitTextField();
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
