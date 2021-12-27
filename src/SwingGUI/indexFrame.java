package SwingGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class indexFrame extends JFrame {
    final int width = 1500, height = 800;
    //容器
    JPanel root = new JPanel();

    //四个标签
    JLabel labelName = new JLabel("品名");        //品名
    JLabel labelPrice = new JLabel("价格");       //价格
    JLabel labelType = new JLabel("类别");        //类别
    JLabel labelMark = new JLabel("备注");        //备注

    //四个文本框
    JTextField textName = new JTextField();             //品名输入框
    JTextField textPrice = new JTextField();            //价格输入框
    JTextField textType = new JTextField();             //类型输入框
    JTextField textMark = new JTextField();             //备注输入框

    //两个按钮
    JButton buttonSave = new JButton("保存");
    JButton buttonOpen = new JButton("打开");

    //列表
    DefaultTableModel defaultTableModel = null;         //表格样式
    JTable table = new JTable(0, 4);

    void Init() {
        //基本设置
        this.setTitle("商品信息管理系统");              //设置标题
        setBounds(100, 100, width, height);     //设置窗口大小
        setVisible(true);                            //设置窗口可见
        setResizable(false);                         //禁止修改窗口大小
        root.setLayout(null);                        //绝对布局
        this.setContentPane(root);                   //加载容器

        //顶部信息
        ButtonInit();   //设置按钮
        LableInit();    //设置标签
        TextInit();     //设置文本框

        //表格信息
        TableInit();    //设置表格
    }

    void TableInit() {
        table.setEnabled(false);                            //禁止修改表格
        table.setModel(getDefaultTableModel());             //加载表格样式
        table.setRowHeight(35);                             //设置行高
        JScrollPane tableScroll = new JScrollPane(table);   //滚动条
        root.add(tableScroll);
        tableScroll.setBounds(80, 170, width - 170, height - 250);//设置大小
    }

    //返回表格样式
    DefaultTableModel getDefaultTableModel() {
        String[] Head = {"编号", "品名", "单价", "上市日期", "类别", "产地", "备注"};               //表头
        defaultTableModel = new DefaultTableModel(null, Head);//加载数据
        return defaultTableModel;
    }

    //标签初始化
    void LableInit() {
        //标签添加到容器中
        root.add(labelName);
        root.add(labelPrice);
        root.add(labelType);
        root.add(labelMark);
        //设置标签位置
        labelName.setBounds(100, 70, 54, 20);
        labelPrice.setBounds(100 * 3, 70, 54, 20);
        labelType.setBounds(100 * 5, 70, 54, 20);
        labelMark.setBounds(100 * 7, 70, 54, 20);
    }

    void TextInit() {
        //文本框添加到容器中
        root.add(textName);
        root.add(textPrice);
        root.add(textType);
        root.add(textMark);
        //设置文本框位置
        textName.setBounds(100, 110, 100, 30);
        textPrice.setBounds(100 * 3, 110, 100, 30);
        textType.setBounds(100 * 5, 110, 100, 30);
        textMark.setBounds(100 * 7, 110, 200, 30);
    }

    void ButtonInit() {
        //按钮添加到容器中
        root.add(buttonOpen);
        root.add(buttonSave);
        //设置按钮位置
        buttonOpen.setBounds(100, 20, 100, 40);
        buttonSave.setBounds(250, 20, 100, 40);
    }

}
