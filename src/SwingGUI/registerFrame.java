package SwingGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class registerFrame extends JFrame {
    //设置大小
    final int width = 600, height = 305;
    loginFrame origin;
    JFrame root = this;


    //标签
    JLabel welcomeLabel = new JLabel("欢迎注册商品信息管理系统");
    ImageIcon userNameIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\账号.png");
    JLabel userNameLabel = new JLabel(userNameIcon);

    ImageIcon userPasswordIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\密码.png");
    JLabel userPasswordLabel = new JLabel(userPasswordIcon);

    ImageIcon userPasswordAgainIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\确认密码.png");
    JLabel userPasswordAgainLabel = new JLabel(userPasswordAgainIcon);

    ImageIcon companyNameIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\公司.png");
    JLabel companyNameLabel = new JLabel(companyNameIcon);

    //文本框
    JTextField userName = new JTextField("账号");
    myPasswordField userPassword = new myPasswordField("密码");
    myPasswordField userPasswordAgain = new myPasswordField("确认密码");
    JTextField companyName = new JTextField("公司");


    //按钮
    ImageIcon icon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\注册.png");
    JButton registerButton = new JButton(icon);

    ImageIcon returnIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\密码错误.png");
    JButton returnButton = new JButton(returnIcon);

    //弹窗
    public class succeedDialog extends JDialog {
        final int width = 275, height = 150;
        //登录信息
        String name;
        //按钮
        ImageIcon nextIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\继续.png");
        JButton nextButton = new JButton(nextIcon);

        //标签
        JLabel welcome = new JLabel("注册成功!");

        ImageIcon succeedIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\注册成功.png");
        JLabel succeed = new JLabel(succeedIcon);

        succeedDialog(String name) {
            this.name = name;
            setTitle("商品信息管理系统-注册成功");
            setVisible(true);
            setBounds(250, 150, width, height);
            setResizable(false);
            setLayout(null);

            add(succeed);
            add(welcome);
            succeed.setBounds(35, 40, 40, 40);
            welcome.setBounds(85, 40, 100, 40);

            add(nextButton);
            nextButton.setBounds(175, 40, 40, 40);
            nextButton.setContentAreaFilled(false);
            nextButton.setFocusPainted(false);
            nextButton.setBorderPainted(false);
            nextButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    setVisible(false);
                    new indexFrame(origin, name);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    ImageIcon pressedIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\继续3.png");
                    nextButton.setIcon(pressedIcon);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    nextButton.setIcon(nextIcon);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    ImageIcon enteredIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\继续2.png");
                    nextButton.setIcon(enteredIcon);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    nextButton.setIcon(nextIcon);
                }
            });
        }
    }

    public static class faultDialog extends JDialog {
        final int width = 275, height = 150;

        //按钮
        ImageIcon returnIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\密码错误.png");
        JButton returnButton = new JButton(returnIcon);

        //标签
        JLabel welcome = new JLabel();


        faultDialog(String message) {
            setTitle("商品信息管理系统-注册失败");
            setVisible(true);
            setBounds(250, 150, width, height);
            setResizable(false);
            setLayout(null);
            welcome.setText(message);
            add(welcome);
            welcome.setBounds(105, 10, 100, 40);

            add(returnButton);
            returnButton.setBounds(108, 50, 40, 40);
            returnButton.setContentAreaFilled(false);
            returnButton.setFocusPainted(false);
            returnButton.setBorderPainted(false);
            returnButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    setVisible(false);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    ImageIcon pressedIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\返回2.png");
                    returnButton.setIcon(pressedIcon);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    returnButton.setIcon(returnIcon);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    ImageIcon enteredIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\返回.png");
                    returnButton.setIcon(enteredIcon);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    returnButton.setIcon(returnIcon);
                }
            });
        }
    }

    registerFrame(loginFrame origin) {
        this.origin = origin;
        setBounds(100, 100, width, height);
        setVisible(false);
        setResizable(false);
        setLayout(null);

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
        userNameLabel.setBounds(65, 57, labelWidth, labelHeight);
        userPasswordLabel.setBounds(65, 105, labelWidth, labelHeight);
        userPasswordAgainLabel.setBounds(65, 150, labelWidth, labelHeight);
        companyNameLabel.setBounds(65, 200, labelWidth, labelHeight);
    }

    void InitTextField() {
        this.add(userName);
        this.add(userPassword);
        this.add(userPasswordAgain);
        this.add(companyName);

        userPassword.Init("密码");
        userPasswordAgain.Init("确认密码");

        final int textFieldWidth = 200, textFieldHeight = 30;
        userName.setBounds(140, 60, textFieldWidth, textFieldHeight);
        userPassword.setBounds(140, 110, textFieldWidth, textFieldHeight);
        userPasswordAgain.setBounds(140, 160, textFieldWidth, textFieldHeight);
        companyName.setBounds(140, 210, textFieldWidth, textFieldHeight);

        RemindTextField(userName, "账号");
        RemindTextField(companyName, "公司");
    }

    void InitButton() {
        registerButton.setContentAreaFilled(false);
        registerButton.setFocusPainted(false);
        registerButton.setBorderPainted(false);

        returnButton.setContentAreaFilled(false);
        returnButton.setFocusPainted(false);
        returnButton.setBorderPainted(false);

        this.add(registerButton);
        registerButton.setBounds(400, 70, 140, 140);

        this.add(returnButton);
        returnButton.setBounds(10, 10, 40, 40);
        returnButton.addMouseListener(new returnButtonMouseListener());
    }

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

    class returnButtonMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            ImageIcon pressedIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\返回2.png");
            returnButton.setIcon(pressedIcon);
            origin.setVisible(true);
            setVisible(false);
            InitTextField();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            ImageIcon pressedIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\返回2.png");
            returnButton.setIcon(pressedIcon);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            returnButton.setIcon(returnIcon);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ImageIcon enteredIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\返回.png");
            returnButton.setIcon(enteredIcon);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            returnButton.setIcon(returnIcon);
        }
    }

}
