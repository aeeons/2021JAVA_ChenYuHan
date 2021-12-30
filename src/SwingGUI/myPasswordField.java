package SwingGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class myPasswordField extends JPasswordField {
    //密码框中信息
    String text;

    void Init(String text) {
        setText(text);
        setForeground(new Color(153, 153, 153));
        setEchoChar((char) 0);
    }

    myPasswordField(String text) {
        Init(text);
        this.text = text;
        this.addFocusListener(new myFocusListener());
    }

    class myFocusListener implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {
            //当点击输入框时，里面的内容为提示信息时，清空内容，将其字体颜色设置为正常黑色。
            if (getText().equals(text)) {
                setEchoChar('*');
                setText("");
                setForeground(Color.BLACK);
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (getText().length() == 0) {
                Init(text);
            }
        }
    }
}