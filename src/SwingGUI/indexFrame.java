package SwingGUI;

import Functions.Add;
import Functions.Load;
import Functions.goods;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.LinkedList;

public class indexFrame extends JFrame {
    final int width = 1500, height = 800;
    //登录信息
    String name;
    String company;
    String position;
    //起源
    loginFrame origin;
    //容器
    indexFrame root = this;

    //七个标签
    JLabel numberLabel = new JLabel("编号");
    JLabel nameLabel = new JLabel("品名");        //品名
    JLabel priceLabel = new JLabel("单价");       //价格
    JLabel dateLabel = new JLabel("上市日期");
    JLabel typeLabel = new JLabel("类别");        //类别
    JLabel placeLabel = new JLabel("产地");
    JLabel markLabel = new JLabel("备注");        //备注

    //七个文本框
    JTextField numberTextField = new JTextField();
    JTextField nameTextField = new JTextField();             //品名输入框
    JTextField priceTextField = new JTextField();            //价格输入框
    JTextField dateTextField = new JTextField();
    JTextField typeTextField = new JTextField();             //类型输入框
    JTextField placeTextField = new JTextField();
    JTextField markTextField = new JTextField();             //备注输入框

    //按钮
    ImageIcon openIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\打开.png");
    JButton openButton = new JButton(openIcon);

    ImageIcon exportIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\导出.png");
    JButton exportButton = new JButton(exportIcon);

    ImageIcon deleteIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\删除.png");
    JButton deleteButton = new JButton(deleteIcon);

    ImageIcon addIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\添加.png");
    JButton addButton = new JButton(addIcon);

    ImageIcon clearIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\清空.png");
    JButton clearButton = new JButton(clearIcon);

    ImageIcon returnIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\密码错误.png");
    JButton returnButton = new JButton(returnIcon);

    //列表
    public DefaultTableModel defaultTableModel = null;         //表格样式
    JTable table = new JTable(0, 4);
    LinkedList<goods> tableList = new LinkedList<goods>();

    indexFrame(loginFrame origin, String name) {
        this.name = name;
        this.origin = origin;
        //登录信息
        getInfo();
        //基本设置
        this.setTitle("商品信息管理系统");              //设置标题
        setBounds(100, 100, width, height);     //设置窗口大小
        setVisible(true);                            //设置窗口可见
        setResizable(false);                         //禁止修改窗口大小
        setLayout(null);                        //绝对布局

        //顶部信息
        ButtonInit();   //设置按钮
        LabelInit();    //设置标签
        TextInit();     //设置文本框

        //表格信息
        TableInit();    //设置表格

    }

    void getInfo() {
        try {
            String SQL = "select * from account where name='" + name + "'";
            Statement stmt = origin.conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            rs.next();
            company = rs.getString("company");
            position = rs.getString("position");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("登陆成功" + name + " " + company + " " + position);
    }

    void TableInit() {
        table.setModel(getDefaultTableModel());             //加载表格样式
        table.setRowHeight(35);                             //设置行高
        JScrollPane tableScroll = new JScrollPane(table);   //滚动条
        root.add(tableScroll);
        tableScroll.setBounds(80, 170, width - 170, height - 250);//设置大小

        tableList = new Load(origin.conn).getTableList(company);

        for (goods o : tableList) {
            System.out.println(Arrays.toString(o.getInfo()));
            defaultTableModel.addRow(o.getInfo());
        }
    }

    //返回表格样式
    DefaultTableModel getDefaultTableModel() {
        String[] Head = {"编号", "品名", "单价", "上市日期", "类别", "产地", "备注"};               //表头
        defaultTableModel = new DefaultTableModel(null, Head);//加载数据
        return defaultTableModel;
    }

    //标签初始化
    void LabelInit() {
        //标签添加到容器中
        this.add(numberLabel);
        this.add(nameLabel);
        this.add(priceLabel);
        this.add(dateLabel);
        this.add(typeLabel);
        this.add(placeLabel);
        this.add(markLabel);
        //设置标签位置
        final int labelWidth = 54, labelHeight = 20;
        numberLabel.setBounds(100, 70, labelWidth, labelHeight);
        nameLabel.setBounds(100 * 3, 70, labelWidth, labelHeight);
        priceLabel.setBounds(100 * 5, 70, labelWidth, labelHeight);
        dateLabel.setBounds(100 * 7, 70, labelWidth, labelHeight);
        typeLabel.setBounds(100 * 9, 70, labelWidth, labelHeight);
        placeLabel.setBounds(100 * 11, 70, labelWidth, labelHeight);
        markLabel.setBounds(100 * 13, 70, labelWidth, labelHeight);
    }

    void TextInit() {
        //文本框添加到容器中
        this.add(numberTextField);
        this.add(nameTextField);
        this.add(priceTextField);
        this.add(dateTextField);
        this.add(typeTextField);
        this.add(placeTextField);
        this.add(markTextField);
        //设置文本框位置
        final int textFieldWidth = 100, textFieldHeight = 30;

        numberTextField.setBounds(100, 110, textFieldWidth, textFieldHeight);
        nameTextField.setBounds(100 * 3, 110, textFieldWidth, textFieldHeight);
        priceTextField.setBounds(100 * 5, 110, textFieldWidth, textFieldHeight);
        dateTextField.setBounds(100 * 7, 110, textFieldWidth, textFieldHeight);
        typeTextField.setBounds(100 * 9, 110, textFieldWidth, textFieldHeight);
        placeTextField.setBounds(100 * 11, 110, textFieldWidth, textFieldHeight);
        markTextField.setBounds(100 * 13, 110, textFieldWidth, textFieldHeight);
    }

    void ButtonInit() {
        //按钮添加到容器中
        this.add(openButton);
        this.add(deleteButton);
        this.add(exportButton);
        this.add(addButton);
        this.add(clearButton);
        this.add(returnButton);
        //addButton.setContentAreaFilled(false);
        openButton.setFocusPainted(false);
        openButton.setContentAreaFilled(false);
        openButton.setBorderPainted(false);

        exportButton.setFocusPainted(false);
        exportButton.setContentAreaFilled(false);
        exportButton.setBorderPainted(false);

        deleteButton.setFocusPainted(false);
        deleteButton.setContentAreaFilled(false);
        deleteButton.setBorderPainted(false);

        addButton.setFocusPainted(false);
        addButton.setContentAreaFilled(false);
        addButton.setBorderPainted(false);

        clearButton.setFocusPainted(false);
        clearButton.setContentAreaFilled(false);
        clearButton.setBorderPainted(false);

        returnButton.setContentAreaFilled(false);
        returnButton.setFocusPainted(false);
        returnButton.setBorderPainted(false);

        //设置按钮位置
        openButton.setBounds(100, 20, 40, 40);
        exportButton.setBounds(180, 20, 40, 40);
        deleteButton.setBounds(260, 20, 40, 40);
        addButton.setBounds(340, 20, 40, 40);
        clearButton.setBounds(420, 20, 40, 40);
        returnButton.setBounds(10, 10, 40, 40);

        //clearButton.addActionListener(new clearAction());
        openButton.addMouseListener(new openMouseListener());
        exportButton.addMouseListener(new exportMouseListener());
        deleteButton.addMouseListener(new deleteMouseListener());
        addButton.addMouseListener(new addMouseListener());
        clearButton.addMouseListener(new clearMouseListener());
        returnButton.addMouseListener(new returnButtonMouseListener());


    }

    class openMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            ImageIcon pressedIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\打开3.png");
            openButton.setIcon(pressedIcon);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            openButton.setIcon(openIcon);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ImageIcon enteredIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\打开2.png");
            openButton.setIcon(enteredIcon);

        }

        @Override
        public void mouseExited(MouseEvent e) {
            openButton.setIcon(openIcon);
        }
    }

    class exportMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            ImageIcon pressedIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\导出3.png");
            exportButton.setIcon(pressedIcon);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            exportButton.setIcon(exportIcon);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ImageIcon enteredIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\导出2.png");
            exportButton.setIcon(enteredIcon);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            exportButton.setIcon(exportIcon);
        }
    }

    class deleteMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            int[] row = table.getSelectedRows();
            if (row.length <= 0) {
                return;
            }
            for (int i = 0; i < row.length; i++) {
                System.out.println(row[0]);
                int modelIndex = table.convertColumnIndexToModel(row[0]);
                defaultTableModel.removeRow(modelIndex);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            ImageIcon pressedIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\删除3.png");
            deleteButton.setIcon(pressedIcon);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            deleteButton.setIcon(deleteIcon);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ImageIcon enteredIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\删除2.png");
            deleteButton.setIcon(enteredIcon);

        }

        @Override
        public void mouseExited(MouseEvent e) {
            deleteButton.setIcon(deleteIcon);
        }
    }

    class addMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            String id = numberTextField.getText();
            String name = nameTextField.getText();
            String price = priceTextField.getText();
            String date = dateTextField.getText();
            String type = typeTextField.getText();
            String place = placeTextField.getText();
            String mark = markTextField.getText();

            new Add(origin.conn, root, company).Add(id, name, price, date, type, place, mark);

        }

        @Override
        public void mousePressed(MouseEvent e) {
            ImageIcon pressedIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\添加3.png");
            addButton.setIcon(pressedIcon);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            addButton.setIcon(addIcon);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ImageIcon enteredIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\添加2.png");
            addButton.setIcon(enteredIcon);

        }

        @Override
        public void mouseExited(MouseEvent e) {
            addButton.setIcon(addIcon);
        }

    }

    class clearMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            numberTextField.setText("");
            nameTextField.setText("");
            priceTextField.setText("");
            dateTextField.setText("");
            typeTextField.setText("");
            placeTextField.setText("");
            markTextField.setText("");
        }

        @Override
        public void mousePressed(MouseEvent e) {
            ImageIcon pressedIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\清空3.png");
            clearButton.setIcon(pressedIcon);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            clearButton.setIcon(clearIcon);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ImageIcon enteredIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\清空2.png");
            clearButton.setIcon(enteredIcon);

        }

        @Override
        public void mouseExited(MouseEvent e) {
            clearButton.setIcon(clearIcon);
        }
    }

    class returnButtonMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            ImageIcon pressedIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\返回2.png");
            returnButton.setIcon(pressedIcon);
            origin.setVisible(true);
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
    }


}
