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
    //基本信息----
    final int width = 1500;         //设置窗口宽度
    final int height = 800;         //设置窗口高度
    String name;                    //当前用户账号
    String company;                 //当前用户公司
    String position;                //当前用户权限
    loginFrame origin;              //父级窗口
    indexFrame root = this;         //获得本身的调用
    String path = "src\\SwingGUI\\img\\";

    //日志----
    FileReader fr;                      //用于读取日志
    FileWriter fw;                      //用于记录日志
    JDialog logDialog = new JDialog();  //用于显示日志
    JTextArea logArea = new JTextArea();//用于显示日志

    //标签----
    JLabel numberLabel = new JLabel("编号");
    JLabel nameLabel = new JLabel("品名");
    JLabel priceLabel = new JLabel("单价");
    JLabel dateLabel = new JLabel("上市日期");
    JLabel typeLabel = new JLabel("类别");
    JLabel placeLabel = new JLabel("产地");
    JLabel markLabel = new JLabel("备注");
    JLabel welcomeLabel = new JLabel("");

    //文本框----
    JTextField numberTextField = new JTextField();           //编号输入框
    JTextField nameTextField = new JTextField();             //品名输入框
    JTextField priceTextField = new JTextField();            //价格输入框
    JTextField dateTextField = new JTextField();             //日期输入框
    JTextField typeTextField = new JTextField();             //类型输入框
    JTextField placeTextField = new JTextField();            //产地输入框
    JTextField markTextField = new JTextField();             //备注输入框
    JTextField findTextField = new JTextField();             //搜索输入框

    //按钮----
    ImageIcon logIcon = new ImageIcon(path + "日志.png");     //日志按钮
    JButton logButton = new JButton(logIcon);

    ImageIcon deleteIcon = new ImageIcon(path + "删除.png");  //删除按钮
    JButton deleteButton = new JButton(deleteIcon);

    ImageIcon addIcon = new ImageIcon(path + "添加.png");     //添加按钮
    JButton addButton = new JButton(addIcon);

    ImageIcon clearIcon = new ImageIcon(path + "清空.png");   //清空按钮
    JButton clearButton = new JButton(clearIcon);

    ImageIcon returnIcon = new ImageIcon(path + "密码错误.png");//返回按钮
    JButton returnButton = new JButton(returnIcon);

    ImageIcon findIcon = new ImageIcon(path + "查找.png");     //查找按钮
    JButton findButton = new JButton(findIcon);

    //列表----
    public DefaultTableModel defaultTableModel = null;              //表格样式
    JTable table = new JTable() {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }; //不可编辑表格
    LinkedList<goods> tableList = new LinkedList<>();          //商品数组

    //构造函数，需要的参数有父级窗口、账号
    indexFrame(loginFrame origin, String name) throws IOException, SQLException {
        //信息初始化
        this.name = name;
        this.origin = origin;
        getInfo();

        //窗口初始化
        setTitle("商品信息管理系统");                           //设置窗口标题
        setVisible(true);                                    //设置窗口可见
        setResizable(false);                                 //窗口大小固定
        setLayout(null);                                     //使用绝对布局
        setBounds(100, 100, width, height);             //设置窗口大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      //关闭窗口后：结束程序

        //组件初始化
        InitLabel();        //初始化标签
        InitButton();       //初始化按钮
        InitTextField();    //初始化文本框
        InitTable();        //初始化表格
        InitTextArea();     //初始化文本域
    }

    //标签初始化
    void InitLabel() {
        welcomeLabel.setText(name + " ，您已成功登录 " + company + " 商品信息管理系统");

        //添加标签到窗口
        add(numberLabel);
        add(nameLabel);
        add(priceLabel);
        add(dateLabel);
        add(typeLabel);
        add(placeLabel);
        add(markLabel);
        add(welcomeLabel);

        //设置标签位置
        final int labelWidth = 54;  //设置标签宽度
        final int labelHeight = 20; //设置标签高度

        numberLabel.setBounds(100, 70, labelWidth, labelHeight);
        nameLabel.setBounds(100 * 3, 70, labelWidth, labelHeight);
        priceLabel.setBounds(100 * 5, 70, labelWidth, labelHeight);
        dateLabel.setBounds(100 * 7, 70, labelWidth, labelHeight);
        typeLabel.setBounds(100 * 9, 70, labelWidth, labelHeight);
        placeLabel.setBounds(100 * 11, 70, labelWidth, labelHeight);
        markLabel.setBounds(100 * 13, 70, labelWidth, labelHeight);
        welcomeLabel.setBounds(100 * 7, 20, labelWidth * 10, labelHeight);
    }

    //按钮初始化
    void InitButton() {
        //按钮设置
        logButton.setFocusable(false);               //按钮不能获得焦点，下同
        logButton.setFocusPainted(false);            //取消焦点状态，下同
        logButton.setBorderPainted(false);           //取消边框，下同
        logButton.setContentAreaFilled(false);       //关闭背景填充，下同

        deleteButton.setFocusable(false);
        deleteButton.setFocusPainted(false);
        deleteButton.setBorderPainted(false);
        deleteButton.setContentAreaFilled(false);

        addButton.setFocusable(false);
        addButton.setFocusPainted(false);
        addButton.setBorderPainted(false);
        addButton.setContentAreaFilled(false);

        clearButton.setFocusable(false);
        clearButton.setFocusPainted(false);
        clearButton.setBorderPainted(false);
        clearButton.setContentAreaFilled(false);

        returnButton.setFocusable(false);
        returnButton.setFocusPainted(false);
        returnButton.setBorderPainted(false);
        returnButton.setContentAreaFilled(false);

        findButton.setFocusable(false);
        findButton.setFocusPainted(false);
        findButton.setBorderPainted(false);
        findButton.setContentAreaFilled(false);

        //按钮添加到容器中
        add(deleteButton);
        add(logButton);
        add(addButton);
        add(clearButton);
        add(findButton);
        add(returnButton);

        //设置按钮位置
        logButton.setBounds(130, 20, 40, 40);
        deleteButton.setBounds(210, 20, 40, 40);
        addButton.setBounds(290, 20, 40, 40);
        clearButton.setBounds(370, 20, 40, 40);
        findButton.setBounds(450, 20, 40, 40);
        returnButton.setBounds(10, 10, 40, 40);

        //添加按钮事件
        logButton.addMouseListener(new logMouseListener());
        deleteButton.addMouseListener(new deleteMouseListener());
        addButton.addMouseListener(new addMouseListener());
        clearButton.addMouseListener(new clearMouseListener());
        findButton.addMouseListener(new findMouseListener());
        returnButton.addMouseListener(new returnMouseListener());
    }

    //文本框初始化
    void InitTextField() {
        //文本框添加到容器中
        add(numberTextField);
        add(nameTextField);
        add(priceTextField);
        add(dateTextField);
        add(typeTextField);
        add(placeTextField);
        add(markTextField);
        add(findTextField);
        //设置文本框位置
        final int textFieldWidth = 100;     //设置文本框宽度
        final int textFieldHeight = 30;     //设置文本框高度

        numberTextField.setBounds(100, 110, textFieldWidth, textFieldHeight);
        nameTextField.setBounds(100 * 3, 110, textFieldWidth, textFieldHeight);
        priceTextField.setBounds(100 * 5, 110, textFieldWidth, textFieldHeight);
        dateTextField.setBounds(100 * 7, 110, textFieldWidth, textFieldHeight);
        typeTextField.setBounds(100 * 9, 110, textFieldWidth, textFieldHeight);
        placeTextField.setBounds(100 * 11, 110, textFieldWidth, textFieldHeight);
        markTextField.setBounds(100 * 13, 110, textFieldWidth, textFieldHeight);
        findTextField.setBounds(500, 23, textFieldWidth, textFieldHeight);
    }

    //表格初始化
    public void InitTable() {
        //表格设置
        table.setModel(getDefaultTableModel());             //加载表格样式
        table.setRowHeight(35);                             //设置行高

        //加载表格数据
        tableLoad();

        //将表格添加到滚动条，将滚动条添加到窗口
        JScrollPane tableScroll = new JScrollPane(table);
        root.add(tableScroll);

        //设置表格位置
        tableScroll.setBounds(80, 170, 1330, 550);

        //添加表格事件
        table.addMouseListener(new tableMouseListener());
    }

    //文本域初始化
    void InitTextArea() throws IOException {
        //加载日志数据
        LoadLog();
        loadLogArea();

        //弹窗和文本域设置
        logDialog.setVisible(false);
        logDialog.setResizable(false);
        logDialog.setLayout(null);
        logArea.setEditable(false);

        //将文本域添加到滚动条，将滚动条添加到弹窗
        JScrollPane scroll = new JScrollPane(logArea);
        logDialog.add(scroll);

        //设置弹窗和文本域位置
        logDialog.setBounds(100, 100, 705, 480);
        scroll.setBounds(20, 20, 650, 400);
    }

    //加载表格数据
    public void tableLoad() {
        table.setFocusable(false);
        defaultTableModel = null;
        table.setModel(getDefaultTableModel());

        //从数据库中加载货物信息，并填入表格中
        tableList = new Load(origin.conn).getTableList(company);
        for (goods o : tableList) {
            System.out.println(Arrays.toString(o.getInfo()));
            defaultTableModel.addRow(o.getInfo());
        }
        System.out.println(tableList.size());
    }

    //返回表格样式
    DefaultTableModel getDefaultTableModel() {
        String[] Head = {"编号", "品名", "单价", "上市日期", "类别", "产地", "备注"};               //表头
        defaultTableModel = new DefaultTableModel(null, Head);//加载数据
        return defaultTableModel;
    }

    //从日志文件中读取数据
    void loadLogArea() throws IOException {
        //从日志文件中读取数据
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            logArea.append(line + "\n");
        }
    }

    //将查找到的货物信息填入文本框中
    void findUpdate(goods o) {
        numberTextField.setText(o.id);
        nameTextField.setText(o.name);
        priceTextField.setText(o.price);
        dateTextField.setText(o.date);
        typeTextField.setText(o.type);
        placeTextField.setText(o.place);
        markTextField.setText(o.mark);
    }

    //记录登录事件，如果没有日志文件则新建
    void LoadLog() throws IOException {
        File logFile = new File("src\\log\\" + company + ".txt");

        //如果没有日志文件，则新建文件
        if (!logFile.exists()) {
            Boolean f = logFile.createNewFile();
            fw = new FileWriter(logFile, true);
            fw.write("-------------------------------\n");
            fw.flush();
        }

        //记录登录事件
        fr = new FileReader(logFile);
        fw = new FileWriter(logFile, true);
        fw.write("\n-------- " + getDate() + " --- " + name + " 登录了系统--------\n");
        fw.flush();
    }

    //根据当前账号查询公司、权限信息
    void getInfo() throws SQLException {
        String SQL = "select * from account where name='" + name + "'";
        Statement stmt = origin.conn.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        rs.next();
        company = rs.getString("company");
        position = rs.getString("position");
    }

    //获取当前的时间
    String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return formatter.format(new Date());
    }

    //日志按钮事件，如果权限为 Boss 则打开日志弹窗
    class logMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (position.equals("Staff")) {
                new faultDialog("没有权限");
                return;
            }

            logDialog.setVisible(true);
            try {
                loadLogArea();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            ImageIcon pressedIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\日志3.png");
            logButton.setIcon(pressedIcon);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            logButton.setIcon(logIcon);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ImageIcon enteredIcon = new ImageIcon("E:\\课设\\java课设\\商品信息管理系统\\src\\SwingGUI\\img\\日志2.png");
            logButton.setIcon(enteredIcon);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            logButton.setIcon(logIcon);
        }
    }

    //删除按钮事件
    class deleteMouseListener implements MouseListener {
        //选中行信息
        String id;
        String name;
        String price;
        String date;
        String type;
        String place;
        String mark;

        void updateInfo(int row) {
            id = (String) table.getValueAt(row, 0);
            name = (String) table.getValueAt(row, 1);
            price = (String) table.getValueAt(row, 2);
            date = (String) table.getValueAt(row, 3);
            type = (String) table.getValueAt(row, 4);
            place = (String) table.getValueAt(row, 5);
            mark = (String) table.getValueAt(row, 6);
        }

        void writeLog() throws IOException {
            fw.write("id: " + id + " ;");
            fw.write("name: " + name + " ;");
            fw.write("price: " + price + " ;");
            fw.write("date: " + date + " ;");
            fw.write("type: " + type + " ;");
            fw.write("place: " + place + " ;");
            fw.write("mark: " + mark + "\n");
            fw.flush();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            //获取选中的表格
            int[] row = table.getSelectedRows();
            if (row.length <= 0) {//如果没有选中表格，则退出
                return;
            }

            try {
                //日志写入删除事件
                fw.write(root.name + " 在 " + getDate() + " 成功删除了数据: \n");
                fw.flush();

                //遍历选中的表格，依次删除数据
                for (int i = 0; i < row.length; i++) {
                    //更新选中行信息
                    updateInfo(row[0]);
                    writeLog();

                    int modelIndex = table.convertColumnIndexToModel(row[0]);
                    defaultTableModel.removeRow(modelIndex);
                    //在数据库中删除信息
                    new Delete(origin.conn, root, company).Delete(id);
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            ImageIcon pressedIcon = new ImageIcon(path + "删除3.png");
            deleteButton.setIcon(pressedIcon);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            deleteButton.setIcon(deleteIcon);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ImageIcon enteredIcon = new ImageIcon(path + "删除2.png");
            deleteButton.setIcon(enteredIcon);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            deleteButton.setIcon(deleteIcon);
        }
    }

    //增加/修改按钮事件
    class addMouseListener implements MouseListener {
        //要增加/修改的信息
        String id;
        String name;
        String price;
        String date;
        String type;
        String place;
        String mark;

        void updateInfo() {
            id = numberTextField.getText();
            name = nameTextField.getText();
            price = priceTextField.getText();
            date = dateTextField.getText();
            type = typeTextField.getText();
            place = placeTextField.getText();
            mark = markTextField.getText();
        }

        void writeLog() throws IOException {
            fw.write("id: " + id + " ;");
            fw.write("name: " + name + " ;");
            fw.write("price: " + price + " ;");
            fw.write("date: " + date + " ;");
            fw.write("type: " + type + " ;");
            fw.write("place: " + place + " ;");
            fw.write("mark: " + mark + "\n");
            fw.flush();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            updateInfo();
            //向数据库中添加信息
            if (new Add(origin.conn, root, company).Add(id, name, price, date, type, place, mark)) {
                tableLoad();
                try {
                    //日志写入写入/修改事件
                    fw.write(root.name + " 在 " + getDate() + " 成功写入/修改了新数据: \n");
                    writeLog();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            ImageIcon pressedIcon = new ImageIcon(path + "添加3.png");
            addButton.setIcon(pressedIcon);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            addButton.setIcon(addIcon);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ImageIcon enteredIcon = new ImageIcon(path + "添加2.png");
            addButton.setIcon(enteredIcon);

        }

        @Override
        public void mouseExited(MouseEvent e) {
            addButton.setIcon(addIcon);
        }

    }

    //查找按钮事件
    class findMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            //读取搜索框的值并清空
            String target = findTextField.getText();
            findTextField.setText("");

            //在表格中寻找目标
            for (Functions.goods goods : tableList) {
                if (goods.id.equals(target)) {
                    findUpdate(goods);
                    return;
                }
            }
            //如果找不到则报错
            new faultDialog("编号不存在");
        }

        @Override
        public void mousePressed(MouseEvent e) {
            ImageIcon pressedIcon = new ImageIcon(path + "查找3.png");
            findButton.setIcon(pressedIcon);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            findButton.setIcon(findIcon);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ImageIcon enteredIcon = new ImageIcon(path + "查找2.png");
            findButton.setIcon(enteredIcon);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            findButton.setIcon(findIcon);
        }
    }

    //清空按钮事件
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
            ImageIcon pressedIcon = new ImageIcon(path + "清空3.png");
            clearButton.setIcon(pressedIcon);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            clearButton.setIcon(clearIcon);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ImageIcon enteredIcon = new ImageIcon(path + "清空2.png");
            clearButton.setIcon(enteredIcon);

        }

        @Override
        public void mouseExited(MouseEvent e) {
            clearButton.setIcon(clearIcon);
        }
    }

    //返回按钮事件
    class returnMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            origin.setVisible(true);    //将父级窗口设为可见
            dispose();                  //释放当前窗口
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

    //表格选中事件
    class tableMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = table.getSelectedRow();   //获取当前选择的行数
            findUpdate(tableList.get(row));     //将当前行信息加载到文本框中
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
    }
}