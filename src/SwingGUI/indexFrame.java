package SwingGUI;

import Functions.Add;
import Functions.Delete;
import Functions.Load;
import Functions.goods;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;

public class indexFrame extends JFrame {
    final int width = 1500, height = 800;
    //登录信息
    String name;
    String company;
    String position;
    //日志
    FileReader fr;
    FileWriter fw;

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
    JLabel welcomeLabel = new JLabel("");

    //八个文本框
    JTextField numberTextField = new JTextField();
    JTextField nameTextField = new JTextField();             //品名输入框
    JTextField priceTextField = new JTextField();            //价格输入框
    JTextField dateTextField = new JTextField();
    JTextField typeTextField = new JTextField();             //类型输入框
    JTextField placeTextField = new JTextField();
    JTextField markTextField = new JTextField();             //备注输入框
    JTextField findTextField = new JTextField();
    //按钮
    ImageIcon exportIcon = new ImageIcon("src\\SwingGUI\\img\\导出.png");
    JButton exportButton = new JButton(exportIcon);

    ImageIcon deleteIcon = new ImageIcon("src\\SwingGUI\\img\\删除.png");
    JButton deleteButton = new JButton(deleteIcon);

    ImageIcon addIcon = new ImageIcon("src\\SwingGUI\\img\\添加.png");
    JButton addButton = new JButton(addIcon);

    ImageIcon clearIcon = new ImageIcon("src\\SwingGUI\\img\\清空.png");
    JButton clearButton = new JButton(clearIcon);

    ImageIcon returnIcon = new ImageIcon("src\\SwingGUI\\img\\密码错误.png");
    JButton returnButton = new JButton(returnIcon);

    ImageIcon findIcon = new ImageIcon("src\\SwingGUI\\img\\查找.png");
    JButton findButton = new JButton(findIcon);

    //日志文本域
    JDialog logDialog = new JDialog();
    JTextArea logArea = new JTextArea();

    //列表
    public DefaultTableModel defaultTableModel = null;         //表格样式
    JTable table = new JTable() {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    LinkedList<goods> tableList = new LinkedList<goods>();

    indexFrame(loginFrame origin, String name) throws IOException {
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
        welcomeLabel.setText(name + " ，您已成功登录 " + company + " 商品信息管理系统");
        LabelInit();    //设置标签
        TextInit();     //设置文本框

        //表格信息
        TableInit();    //设置表格

        //加载日志
        File logFile = new File("src\\log\\" + company + ".txt");
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
                fw = new FileWriter(logFile, true);
                fw.write("-------------------------------\n");
                fw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        fr = new FileReader(logFile);
        fw = new FileWriter(logFile, true);
        fw.write("\n-------- " + getDate() + " --- " + name + " 登录了系统--------\n");
        fw.flush();

        //文本域
        AreaInit();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    }

    public void TableInit() {
        table.setModel(getDefaultTableModel());             //加载表格样式
        table.setRowHeight(35);                             //设置行高
        JScrollPane tableScroll = new JScrollPane(table);   //滚动条
        root.add(tableScroll);
        tableScroll.setBounds(80, 170, width - 170, height - 250);//设置大小

        tableLoad();
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                findUpdate(tableList.get(row));
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void tableLoad() {
        table.setFocusable(false);
        defaultTableModel = null;
        table.setModel(getDefaultTableModel());
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
        this.add(welcomeLabel);
        //设置标签位置
        final int labelWidth = 54, labelHeight = 20;
        numberLabel.setBounds(100, 70, labelWidth, labelHeight);
        nameLabel.setBounds(100 * 3, 70, labelWidth, labelHeight);
        priceLabel.setBounds(100 * 5, 70, labelWidth, labelHeight);
        dateLabel.setBounds(100 * 7, 70, labelWidth, labelHeight);
        typeLabel.setBounds(100 * 9, 70, labelWidth, labelHeight);
        placeLabel.setBounds(100 * 11, 70, labelWidth, labelHeight);
        markLabel.setBounds(100 * 13, 70, labelWidth, labelHeight);
        welcomeLabel.setBounds(100 * 7, 20, labelWidth * 10, labelHeight);
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
        this.add(findTextField);
        //设置文本框位置
        final int textFieldWidth = 100, textFieldHeight = 30;

        numberTextField.setBounds(100, 110, textFieldWidth, textFieldHeight);
        nameTextField.setBounds(100 * 3, 110, textFieldWidth, textFieldHeight);
        priceTextField.setBounds(100 * 5, 110, textFieldWidth, textFieldHeight);
        dateTextField.setBounds(100 * 7, 110, textFieldWidth, textFieldHeight);
        typeTextField.setBounds(100 * 9, 110, textFieldWidth, textFieldHeight);
        placeTextField.setBounds(100 * 11, 110, textFieldWidth, textFieldHeight);
        markTextField.setBounds(100 * 13, 110, textFieldWidth, textFieldHeight);
        findTextField.setBounds(500, 23, textFieldWidth, textFieldHeight);
    }

    void ButtonInit() {
        //按钮添加到容器中
        this.add(deleteButton);
        this.add(exportButton);
        this.add(addButton);
        this.add(clearButton);
        this.add(findButton);
        this.add(returnButton);

        exportButton.setFocusPainted(false);
        exportButton.setContentAreaFilled(false);
        exportButton.setBorderPainted(false);
        exportButton.setFocusable(false);

        deleteButton.setFocusPainted(false);
        deleteButton.setContentAreaFilled(false);
        deleteButton.setBorderPainted(false);
        deleteButton.setFocusable(false);

        addButton.setFocusPainted(false);
        addButton.setContentAreaFilled(false);
        addButton.setBorderPainted(false);
        addButton.setFocusable(false);

        clearButton.setFocusPainted(false);
        clearButton.setContentAreaFilled(false);
        clearButton.setBorderPainted(false);
        clearButton.setFocusable(false);

        returnButton.setContentAreaFilled(false);
        returnButton.setFocusPainted(false);
        returnButton.setBorderPainted(false);
        returnButton.setFocusable(false);

        findButton.setContentAreaFilled(false);
        findButton.setFocusPainted(false);
        findButton.setBorderPainted(false);
        findButton.setFocusable(false);

        //设置按钮位置
        exportButton.setBounds(130, 20, 40, 40);
        deleteButton.setBounds(210, 20, 40, 40);
        addButton.setBounds(290, 20, 40, 40);
        clearButton.setBounds(370, 20, 40, 40);
        findButton.setBounds(450, 20, 40, 40);
        returnButton.setBounds(10, 10, 40, 40);

        //clearButton.addActionListener(new clearAction());
        exportButton.addMouseListener(new exportMouseListener());
        deleteButton.addMouseListener(new deleteMouseListener());
        addButton.addMouseListener(new addMouseListener());
        clearButton.addMouseListener(new clearMouseListener());
        findButton.addMouseListener(new findMouseListener());
        returnButton.addMouseListener(new returnButtonMouseListener());


    }

    void AreaInit() throws IOException {
        logDialog.setVisible(false);
        logDialog.setResizable(false);
        logDialog.setLayout(null);
        JScrollPane scroll = new JScrollPane(logArea);
        loadLogArea();

        logArea.setEditable(false);
        logDialog.add(scroll);
        //scroll.setBounds(10, 10, 10, 10);
        logDialog.setBounds(100, 100, 705, 480);
        scroll.setBounds(20, 20, 650, 400);
    }

    void loadLogArea() throws IOException {
        BufferedReader br = new BufferedReader(fr);

        String line;
        while ((line = br.readLine()) != null) {
            logArea.append(line + "\n");
        }
    }

    void findUpdate(goods o) {
        numberTextField.setText(o.id);
        nameTextField.setText(o.name);
        priceTextField.setText(o.price);
        dateTextField.setText(o.date);
        typeTextField.setText(o.type);
        placeTextField.setText(o.place);
        markTextField.setText(o.mark);
    }

    class exportMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            logDialog.setVisible(true);
            try {
                loadLogArea();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
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

            try {
                fw.write(root.name + " 在 " + getDate() + " 成功删除了数据: \n");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            for (int i = 0; i < row.length; i++) {
                String id = (String) table.getValueAt(row[0], 0);
                String name = (String) table.getValueAt(row[0], 1);
                String price = (String) table.getValueAt(row[0], 2);
                String date = (String) table.getValueAt(row[0], 3);
                String type = (String) table.getValueAt(row[0], 4);
                String place = (String) table.getValueAt(row[0], 5);
                String mark = (String) table.getValueAt(row[0], 6);

                try {
                    fw.write("id: " + row[0] + " ;name: " + name + " ;price: " + price + " ;date: " + date + " ;type: " + type + " ;place: " + place + " ;mark: " + mark + "\n");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }


                int modelIndex = table.convertColumnIndexToModel(row[0]);
                defaultTableModel.removeRow(modelIndex);
                new Delete(origin.conn, root, company).Delete(id);

            }
            try {
                fw.flush();
            } catch (IOException ioException) {
                ioException.printStackTrace();
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

            if (new Add(origin.conn, root, company).Add(id, name, price, date, type, place, mark)) {
                try {
                    fw.write(root.name + " 在 " + getDate() + " 成功写入/修改了新数据: \n");
                    fw.write("id: " + id + " ;name: " + name + " ;price: " + price + " ;date: " + date + " ;type: " + type + " ;place: " + place + " ;mark: " + mark + "\n");
                    fw.flush();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
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

    class findMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            String target = findTextField.getText();
            findTextField.setText("");
            for (Functions.goods goods : tableList) {
                if (goods.id.equals(target)) {
                    findUpdate(goods);
                    return;
                }
            }
            new faultDialog("编号不存在");
        }

        @Override
        public void mousePressed(MouseEvent e) {
            ImageIcon pressedIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\查找3.png");
            findButton.setIcon(pressedIcon);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            findButton.setIcon(findIcon);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ImageIcon enteredIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\查找2.png");
            findButton.setIcon(enteredIcon);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            findButton.setIcon(findIcon);
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

    String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return formatter.format(new Date());
    }
}