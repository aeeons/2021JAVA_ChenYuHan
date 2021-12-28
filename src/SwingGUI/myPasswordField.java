package SwingGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class myPasswordField extends JPasswordField {

    void Init(String text) {
        this.setText(text);
        this.setForeground(new Color(153, 153, 153));
        this.setEchoChar((char) 0);
    }

    myPasswordField(String text) {
        Init(text);
        this.addFocusListener(new FocusListener() {
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
        });
    }
}