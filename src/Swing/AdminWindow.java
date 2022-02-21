package Swing;

import DAO.StuDAOImpl;
import DAO.TeaDAOImpl;
import DAO.courInfoDAOImpl;
import DAO.courSelDAOImpl;
import JavaBean.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminWindow extends JFrame{
    StuDAOImpl sDAO = new StuDAOImpl();
    TeaDAOImpl tDAO = new TeaDAOImpl();
    courInfoDAOImpl ciDAO = new courInfoDAOImpl();
    courSelDAOImpl csDAO = new courSelDAOImpl();

    List<CourseInfo> buffer = new ArrayList<>(10);
    List<CourseSelected> buffer1 = new ArrayList<>(10);
    List<Student> buffer2 = new ArrayList<>(10);
    List<Teacher> buffer3 = new ArrayList<>(10);

    /*
        个人信息及总界面变量
     */
    Box box;
    Box infobox1;
    Box infobox2;

    JTabbedPane jtp;
    JButton exit;

    /**
     * 存放个人信息
     */
    JPanel jp;
    JLabel jname;
    JLabel jsid;
    JLabel jmajor;
    JLabel jgrade;
    JLabel jxf;
    String sid;
    String name;
    String major;
    String grade;
    long xf;


    /*
        课程详细信息管理
     */
    Box tabbox1;
    Box pagebox1;
    Box sortbox1;
    Box sb1;
    Box sb2;

    //选课表格
    JTable jtable1;
    //以下组件用作选课筛选
    JTextField scid1;
    JLabel SCID1;
    JTextField scname1;
    JLabel SCNAME1;
    JTextField stid1;
    JLabel STID1;
    JTextField stname1;
    JLabel STNAME1;
    JTextField stime1;
    JLabel STIME1;
    JTextField sxf1;
    JLabel SXF1;
    JCheckBox shr1;
    JLabel SHR1;

    //维护的两个分页指针
    long count1;
    long pages1;
    //创建表格的两个成员，用于设置表格内容
    Object[] columnNames1 = {"课程号","课程名","教师号","教师名","上课时间","选课人数","总容量","学分"};
    Object[][] content1 = new Object[10][8];
    //上下页切换按钮、修改课程信息按钮、增加按钮、删除按钮、分类按钮
    JButton Pageup1;
    JButton Pagedown1;
    JButton add1;
    JButton delete1;
    JButton change1;
    JButton sort1;
    int Flag1 = 0;//筛选标志符
    String Sql1;//语句筛选后缀




    /*
        结课管理
     */


    Box tabbox2;
    Box pagebox2;
    Box sortbox2;
    Box sb3;
    Box sb4;

    //选课表格
    JTable jtable2;
    //以下组件用作选课筛选
    JTextField scid2;
    JLabel SCID2;
    JTextField scname2;
    JLabel SCNAME2;
    JTextField stid2;
    JLabel STID2;
    JTextField stname2;
    JLabel STNAME2;
    JTextField stime2;
    JLabel STIME2;
    JTextField ssid1;
    JLabel SSID1;
    JTextField ssname1;
    JLabel SSNAME1;

    //维护的两个分页指针
    long count2;
    long pages2;
    //创建表格的两个成员，用于设置表格内容
    Object[] columnNames2 = {"课程号","课程名","教师号","教师名","学生号","学生名"};
    Object[][] content2 = new Object[10][7];
    //上下页切换按钮等
    JButton Pageup2;
    JButton Pagedown2;
    JButton delete2;
    JButton change2;
    JButton sort2;
    int Flag2 = 0;//筛选标志符
    String Sql2;//语句筛选后缀

    /*
        学生信息管理
     */

    Box tabbox3;
    Box pagebox3;
    Box sortbox3;
    Box sb5;
    Box sb6;

    //选课表格
    JTable jtable3;
    //以下组件用作选课筛选
    JTextField ssid2;
    JLabel SSID2;
    JTextField ssname2;
    JLabel SSNAME2;
    JTextField smajor;
    JLabel SMAJOR;
    JTextField sgrade;
    JLabel SGRADE;


    //维护的两个分页指针
    long count3;
    long pages3;
    //创建表格的两个成员，用于设置表格内容
    Object[] columnNames3 = {"学号","学生名","账号密码","学生专业","学生年级"};
    Object[][] content3 = new Object[10][5];
    //上下页切换按钮等
    JButton Pageup3;
    JButton Pagedown3;
    JButton add3;
    JButton delete3;
    JButton change3;
    JButton sort3;
    int Flag3 = 0;//筛选标志符
    String Sql3;//语句筛选后缀

    /*
        老师信息管理
     */
    Box tabbox4;
    Box pagebox4;
    Box sortbox4;
    Box sb7;
    Box sb8;

    //选课表格
    JTable jtable4;
    //以下组件用作老师信息筛选
    JTextField tid2;
    JLabel Tid2;
    JTextField tname2;
    JLabel TNAME2;
    JTextField tmajor;
    JLabel Tmajor;



    //维护的两个分页指针
    long count4;
    long pages4;
    //创建表格的两个成员，用于设置表格内容
    Object[] columnNames4 = {"教师号","姓名","所属学院"};
    Object[][] content4 = new Object[10][3];
    //上下页切换按钮等
    JButton Pageup4;
    JButton Pagedown4;
    JButton add4;
    JButton delete4;
    JButton change4;
    JButton sort4;
    int Flag4 = 0;//筛选标志符
    String Sql4;//语句筛选后缀


    AdminWindow(String aid) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        box = Box.createVerticalBox();
        /**
         * 个人信息初始化
         */



        sid = aid;
        name = sDAO.getAdminNameByAid(aid);
        major = "****";
        grade = "****";


        jp = new JPanel(new GridLayout(2,1));
        infobox1 = Box.createHorizontalBox();
        infobox2 = Box.createHorizontalBox();
        jp.add(infobox1);
        jp.add(infobox2);

        exit = new JButton("退出登录");
        jname = new JLabel("学生姓名：" + name);
        jsid = new JLabel("学号：" + sid);
        jmajor = new JLabel("学生专业：" + major);
        jgrade = new JLabel("年级：" + grade);

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    new LoginWindow();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        infobox1.add(jsid);
        infobox1.add(Box.createHorizontalStrut(10));
        infobox1.add(jname);
        infobox1.add(Box.createHorizontalStrut(10));
        infobox1.add(jgrade);
        infobox2.add(jmajor);
        infobox2.add(Box.createHorizontalStrut(50));
        infobox2.add(exit);




        /*------------------------------------------------------------------------------------------------*/

        /*
        选项卡初始化
         */


        jtp = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
        JPanel jp1 = new JPanel();
        jtp.addTab("课程详细信息管理", jp1);
        JPanel jp2 = new JPanel();
        jtp.addTab("结课管理", jp2);
        JPanel jp3 = new JPanel();
        jtp.addTab("学生信息管理", jp3);
        JPanel jp4 = new JPanel();
        jtp.addTab("教师信息管理", jp4);


        box.add(jp);
        box.add(jtp);
        add(box);
        /*------------------------------------------------------------------------------------------------*/

        courManageWindow(jp1);
        courFinishWindow(jp2);
        stuManageWindow(jp3);
        teaManageWindow(jp4);



        /*-------------------------------------------------------------------------------*/
        /*
            结构默认和容器调整
         */
        jp.setPreferredSize(new Dimension(0,40));
        setResizable(false);
        setTitle("管理员调试界面");
        setVisible(true);
        setSize(new Dimension(900,600));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    void courManageWindow(JPanel jp1) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        tabbox1 = Box.createVerticalBox();
        pagebox1 = Box.createHorizontalBox();
        Pageup1 = new JButton("上一页");
        Pagedown1 = new JButton("下一页");
        sort1 = new JButton("搜索");
        add1 = new JButton("增设课程");
        delete1 = new JButton("删除课程");
        change1 = new JButton("修改课程信息");

        jtable1 = new JTable(content1, columnNames1){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

//        JComboBox valueAt =(JComboBox) jtable.getModel().getValueAt(0, 8);

        // 设置表格内容颜色
        jtable1.setForeground(Color.BLACK);                   // 字体颜色
        jtable1.setFont(new Font(null, Font.PLAIN, 14));      // 字体样式
        jtable1.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        jtable1.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        jtable1.setGridColor(Color.GRAY);                     // 网格颜色
        jtable1.getTableHeader().setFont(new Font(null, Font.BOLD, 14));
        jtable1.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        jtable1.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列

        tabbox1.add(jtable1.getTableHeader());
        tabbox1.add(jtable1);
        pagebox1.add(Box.createHorizontalStrut(300));
        pagebox1.add(add1);
        pagebox1.add(Box.createHorizontalStrut(10));
        pagebox1.add(delete1);
        pagebox1.add(Box.createHorizontalStrut(10));
        pagebox1.add(change1);
        pagebox1.add(Box.createHorizontalStrut(10));
        pagebox1.add(Pageup1);
        pagebox1.add(Box.createHorizontalStrut(10));
        pagebox1.add(Pagedown1);
        tabbox1.add(Box.createVerticalStrut(20));
        tabbox1.add(pagebox1);
        jp1.add(tabbox1);


        xkTableRefresh();
        Pageup1.addActionListener(new up1ActionListener());
        Pagedown1.addActionListener(new down1ActionListener());



        jtable1.setPreferredSize(new Dimension(800,160));





        sortbox1 = Box.createVerticalBox();
        tabbox1.add(Box.createVerticalStrut(30));
        tabbox1.add(sortbox1);
        sb1 = Box.createHorizontalBox();
        sb2 = Box.createHorizontalBox();
        sortbox1.add(sb1);
        sortbox1.add(Box.createVerticalStrut(20));
        sortbox1.add(sb2);
        SCID1 = new JLabel("课程号：");
        sb1.add(SCID1);
        scid1 = new JTextField();
        sb1.add(scid1);
        SCNAME1 = new JLabel("课程名：");
        sb1.add(SCNAME1);
        scname1 = new JTextField();
        sb1.add(scname1);
        STID1 = new JLabel("教师号：");
        sb1.add(STID1);
        stid1 = new JTextField();
        sb1.add(stid1);
        STNAME1 = new JLabel("教师名：");
        sb1.add(STNAME1);
        stname1 = new JTextField();
        sb1.add(stname1);
        STIME1 = new JLabel("课程时间：");
        sb2.add(STIME1);
        stime1 = new JTextField(10);
        sb2.add(stime1);
        SXF1 = new JLabel("学分：");
        sb2.add(SXF1);
        sxf1 = new JTextField();
        sb2.add(sxf1);
        sb2.add(Box.createHorizontalStrut(20));
        SHR1 = new JLabel("人数未满：");
        sb2.add(SHR1);
        shr1 = new JCheckBox();
        sb2.add(shr1);
        sort1 = new JButton("搜索");
        sb2.add(Box.createHorizontalStrut(50));
        sb2.add(sort1);
        sb2.add(Box.createHorizontalStrut(300));
        sort1.addActionListener(new sort1ActionListener());
        add1.addActionListener(new add1ActionListener());
        delete1.addActionListener(new delete1ActionListener());
        change1.addActionListener(new change1ActionListener());


    }

    void courFinishWindow(JPanel jp2) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        tabbox2 = Box.createVerticalBox();
        pagebox2 = Box.createHorizontalBox();
        Pageup2 = new JButton("上一页");
        Pagedown2 = new JButton("下一页");
        delete2 = new JButton("移除");
        change2 = new JButton("增设成绩");

        jtable2 = new JTable(content2, columnNames2){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };



        // 设置表格内容颜色
        jtable2.setForeground(Color.BLACK);                   // 字体颜色
        jtable2.setFont(new Font(null, Font.PLAIN, 14));      // 字体样式
        jtable2.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        jtable2.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        jtable2.setGridColor(Color.GRAY);                     // 网格颜色
        jtable2.getTableHeader().setFont(new Font(null, Font.BOLD, 14));
        jtable2.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        jtable2.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列

        tabbox2.add(jtable2.getTableHeader());
        tabbox2.add(jtable2);
        pagebox2.add(Box.createHorizontalStrut(300));

        pagebox2.add(Box.createHorizontalStrut(10));
        pagebox2.add(delete2);
        pagebox2.add(Box.createHorizontalStrut(10));
        pagebox2.add(change2);
        pagebox2.add(Box.createHorizontalStrut(10));
        pagebox2.add(Pageup2);
        pagebox2.add(Box.createHorizontalStrut(10));
        pagebox2.add(Pagedown2);
        tabbox2.add(Box.createVerticalStrut(10));
        tabbox2.add(pagebox2);
        jp2.add(tabbox2);

        Pageup2.addActionListener(new up2ActionListener());
        Pagedown2.addActionListener(new down2ActionListener());
        delete2.addActionListener(new delete2ActionListener());



        jtable2.setPreferredSize(new Dimension(800,160));





        sortbox2 = Box.createVerticalBox();
        tabbox2.add(Box.createVerticalStrut(30));
        tabbox2.add(sortbox2);
        sb3 = Box.createHorizontalBox();
        sb4 = Box.createHorizontalBox();
        sortbox2.add(sb3);
        sortbox2.add(Box.createVerticalStrut(10));
        sortbox2.add(sb4);
        SCID2 = new JLabel("课程号：");
        sb3.add(SCID2);
        scid2 = new JTextField();
        sb3.add(scid2);
        SCNAME2 = new JLabel("课程名：");
        sb3.add(SCNAME2);
        scname2 = new JTextField();
        sb3.add(scname2);
        STID2 = new JLabel("教师号：");
        sb3.add(STID2);
        stid2 = new JTextField();
        sb3.add(stid2);
        STNAME2 = new JLabel("教师名：");
        sb3.add(STNAME2);
        stname2 = new JTextField();
        sb3.add(stname2);
        SSID1 = new JLabel("学生号：");
        sb4.add(SSID1);
        ssid1 = new JTextField(10);
        sb4.add(ssid1);
        SSNAME1 = new JLabel("学生名：");
        sb4.add(SSNAME1);
        ssname1 = new JTextField();
        sb4.add(ssname1);
        sb4.add(Box.createHorizontalStrut(10));

        sort2 = new JButton("搜索");
        sb4.add(Box.createHorizontalStrut(50));
        sb4.add(sort2);

        sb4.add(Box.createHorizontalStrut(300));
        wcourTableRefresh();
        change2.addActionListener(new change2ActionListener());
        sort2.addActionListener(new sort2ActionListener());



    }

    void stuManageWindow(JPanel jp3) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        tabbox3 = Box.createVerticalBox();
        pagebox3 = Box.createHorizontalBox();
        Pageup3 = new JButton("上一页");
        Pagedown3 = new JButton("下一页");
        add3 = new JButton("增加学生信息");
        delete3 = new JButton("删除学生信息");
        change3 = new JButton("修改学生信息");

        jtable3 = new JTable(content3, columnNames3){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

//        JComboBox valueAt =(JComboBox) jtable.getModel().getValueAt(0, 8);

        // 设置表格内容颜色
        jtable3.setForeground(Color.BLACK);                   // 字体颜色
        jtable3.setFont(new Font(null, Font.PLAIN, 14));      // 字体样式
        jtable3.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        jtable3.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        jtable3.setGridColor(Color.GRAY);                     // 网格颜色
        jtable3.getTableHeader().setFont(new Font(null, Font.BOLD, 14));
        jtable3.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        jtable3.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列

        tabbox3.add(jtable3.getTableHeader());
        tabbox3.add(jtable3);
        pagebox3.add(Box.createHorizontalStrut(300));
        pagebox3.add(add3);
        pagebox3.add(Box.createHorizontalStrut(10));
        pagebox3.add(delete3);
        pagebox3.add(Box.createHorizontalStrut(10));
        pagebox3.add(change3);
        pagebox3.add(Box.createHorizontalStrut(10));
        pagebox3.add(Pageup3);
        pagebox3.add(Box.createHorizontalStrut(10));
        pagebox3.add(Pagedown3);
        tabbox3.add(Box.createVerticalStrut(20));
        tabbox3.add(pagebox3);
        jp3.add(tabbox3);


        stuTableRefresh();
        Pageup3.addActionListener(new up3ActionListener());
        Pagedown3.addActionListener(new down3ActionListener());




        jtable3.setPreferredSize(new Dimension(800,160));





        sortbox3 = Box.createVerticalBox();
        tabbox3.add(Box.createVerticalStrut(30));
        tabbox3.add(sortbox3);
        sb5 = Box.createHorizontalBox();
        sb6 = Box.createHorizontalBox();
        sortbox3.add(sb5);
        sortbox3.add(Box.createVerticalStrut(20));
        sortbox3.add(sb6);

        sort3 = new JButton("搜索");

        sb6.add(sort3);
        SSID2 = new JLabel("学生号：");
        sb6.add(SSID2);
        ssid2 = new JTextField(10);
        sb6.add(ssid2);

        SSNAME2 = new JLabel("学生名：");
        sb6.add(SSNAME2);
        ssname2 = new JTextField();
        sb6.add(ssname2);

        SMAJOR = new JLabel("学生专业");
        sb6.add(SMAJOR);
        smajor = new JTextField();
        sb6.add(smajor);

        SGRADE = new JLabel("学生年级：");
        sb6.add(SGRADE);
        sgrade = new JTextField();
        sb6.add(sgrade);


        sb6.add(Box.createHorizontalStrut(100));
        sort3.addActionListener(new sort3ActionListener());
        add3.addActionListener(new add3ActionListener());
        delete3.addActionListener(new delete3ActionListener());
        change3.addActionListener(new change3ActionListener());


    }

    void teaManageWindow(JPanel jp4) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        tabbox4 = Box.createVerticalBox();
        pagebox4 = Box.createHorizontalBox();
        Pageup4 = new JButton("上一页");
        Pagedown4 = new JButton("下一页");
        add4 = new JButton("增加老师信息");
        delete4 = new JButton("删除老师信息");
        change4 = new JButton("修改老师信息");

        jtable4 = new JTable(content4, columnNames4){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

//        JComboBox valueAt =(JComboBox) jtable.getModel().getValueAt(0, 8);

        // 设置表格内容颜色
        jtable4.setForeground(Color.BLACK);                   // 字体颜色
        jtable4.setFont(new Font(null, Font.PLAIN, 14));      // 字体样式
        jtable4.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        jtable4.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        jtable4.setGridColor(Color.GRAY);                     // 网格颜色
        jtable4.getTableHeader().setFont(new Font(null, Font.BOLD, 14));
        jtable4.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        jtable4.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列

        tabbox4.add(jtable4.getTableHeader());
        tabbox4.add(jtable4);
        pagebox4.add(Box.createHorizontalStrut(300));
        pagebox4.add(add4);
        pagebox4.add(Box.createHorizontalStrut(10));
        pagebox4.add(delete4);
        pagebox4.add(Box.createHorizontalStrut(10));
        pagebox4.add(change4);
        pagebox4.add(Box.createHorizontalStrut(10));
        pagebox4.add(Pageup4);
        pagebox4.add(Box.createHorizontalStrut(10));
        pagebox4.add(Pagedown4);
        tabbox4.add(Box.createVerticalStrut(20));
        tabbox4.add(pagebox4);
        jp4.add(tabbox4);

        teaTableRefresh();
        Pageup4.addActionListener(new up4ActionListener());
        Pagedown4.addActionListener(new down4ActionListener());




        jtable4.setPreferredSize(new Dimension(800,160));





        sortbox4 = Box.createVerticalBox();
        tabbox4.add(Box.createVerticalStrut(30));
        tabbox4.add(sortbox4);
        sb7 = Box.createHorizontalBox();
        sb8 = Box.createHorizontalBox();
        sortbox4.add(sb7);
        sortbox4.add(Box.createVerticalStrut(20));
        sortbox4.add(sb8);

        sort4 = new JButton("搜索");

        sb8.add(sort4);
        Tid2 = new JLabel("教师号：");
        sb8.add(Tid2);
        tid2 = new JTextField(10);
        sb8.add(tid2);

        TNAME2 = new JLabel("教师名：");
        sb8.add(TNAME2);
        tname2 = new JTextField();
        sb8.add(tname2);

        Tmajor = new JLabel("所属院系");
        sb8.add(Tmajor);
        tmajor = new JTextField();
        sb8.add(tmajor);




        sb8.add(Box.createHorizontalStrut(100));
        sort4.addActionListener(new sort4ActionListener());
        add4.addActionListener(new add4ActionListener());
        delete4.addActionListener(new delete4ActionListener());
        change4.addActionListener(new change4ActionListener());


    }

    public void xkTableRefresh() throws SQLException, IOException, ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        pages1 = 1;
        if(Flag1 == 1){
            count1 = ciDAO.getSortedLength(Sql1) / 10 + 1;
            buffer = ciDAO.getSortedTable(pages1 - 1, Sql1);
        }else {
            count1 = ciDAO.getLength() / 10 + 1;
            buffer = ciDAO.getTable(pages1 - 1);
        }
        for(int i = 0; i < content1.length; i++)
            for(int j = 0; j < content1[i].length; j++)
                content1[i][j] = null;
        if(buffer == null||buffer.size()==0)  {
            jtable1.updateUI();
            return;
        }
        for(int i = 0; i < Math.min(content1.length, buffer.size()); i++) {
            CourseInfo temp = buffer.get(i);
            Object[] contain = temp.toObjectArray();
            for (int j = 0; j < content1[i].length; j++) {
                content1[i][j] =  contain[j];
            }
        }
        jtable1.updateUI();
    }

    public void wcourTableRefresh() throws SQLException, IOException, ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        pages2 = 1;
        if(Flag2 == 1){
            count2 = csDAO.getWSortedLength(Sql2) / 10 + 1;
            buffer1 = csDAO.getWSortedTable(pages2 - 1, Sql2);
        }else {
            count2 = csDAO.getWLength() / 10 + 1;
            buffer1 = csDAO.getWTable(pages2 - 1);
        }
        for(int i = 0; i < content2.length; i++)
            for(int j = 0; j < content2[i].length; j++)
                content2[i][j] = null;
        if(buffer1 == null|| buffer1.size() == 0)  {
            jtable2.updateUI();
            return;
        }
        for(int i = 0; i < Math.min(content2.length, buffer1.size()); i++) {
            CourseSelected temp = buffer1.get(i);
            System.out.println(temp);
            Object[] contain = temp.toObjectArray2();
            for (int j = 0; j < content2[i].length; j++) {
                content2[i][j] =  contain[j];
            }
        }
        jtable2.updateUI();
    }

    public void stuTableRefresh() throws SQLException, IOException, ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        pages3 = 1;
        if(Flag3 == 1){
            count3 = sDAO.getSortedLength(Sql3) / 10 + 1;
            buffer2 = sDAO.getSortedTable(pages3 - 1, Sql3);
        }else {
            count3 = sDAO.getLength() / 10 + 1;
            buffer2 = sDAO.getTable(pages3 - 1);
        }
        for(int i = 0; i < content3.length; i++)
            for(int j = 0; j < content3[i].length; j++)
                content3[i][j] = null;
        if(buffer2 == null|| buffer2.size() == 0)  {
            jtable3.updateUI();
            return;
        }
        for(int i = 0; i < Math.min(content3.length, buffer2.size()); i++) {
            Student temp = buffer2.get(i);
            Object[] contain = temp.toObjectArray();
            for (int j = 0; j < content3[i].length; j++) {
                content3[i][j] =  contain[j];
            }
        }
        jtable3.updateUI();
    }

    public void teaTableRefresh() throws SQLException, IOException, ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        pages4 = 1;
        if(Flag4 == 1){
            count4 = tDAO.getSortedLength(Sql4) / 10 + 1;
            buffer3 = tDAO.getSortedTable(pages4 - 1, Sql4);
        }else {
            count4 = tDAO.getLength() / 10 + 1;
            buffer3 = tDAO.getTable(pages4 - 1);
        }
        for(int i = 0; i < content4.length; i++)
            for(int j = 0; j < content4[i].length; j++)
                content4[i][j] = null;
        if(buffer3 == null|| buffer3.size() == 0)  {
            jtable4.updateUI();
            return;
        }
        for(int i = 0; i < Math.min(content4.length, buffer3.size()); i++) {
            Teacher temp = buffer3.get(i);
            Object[] contain = temp.toObjectArray();
            for (int j = 0; j < content4[i].length; j++) {
                content4[i][j] =  contain[j];
            }
        }
        jtable4.updateUI();
    }
    class up1ActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (pages1 == 1) {
                JOptionPane.showMessageDialog(new JPanel(), "已到第一页", "提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Object obj1[][] = new Object[10][8];
                for (int i = 0; i < 10; i++)
                    for (int j = 0; j < 8; j++)
                        content1[i][j] = obj1[i][j];
                jtable1.getSelectionModel().clearSelection();
                pages1--;
                buffer.clear();
                try {
                    if (Flag1 == 0)
                        buffer = ciDAO.getTable((pages1 - 1) * 10);
                    else buffer = ciDAO.getSortedTable((pages1 - 1) * 10, Sql1);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                for (int j = 0; j < Math.min(buffer.size(), 10 * pages1); j++) {
                    CourseInfo temp = buffer.get(j);
                    try {
                        Object[] obj = temp.toObjectArray();
                        for (int i = 0; i < 8; i++)
                            content1[j][i] = obj[i];
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }

            jtable1.updateUI();
        }
    }
    class down1ActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (pages1 == count1) {
                JOptionPane.showMessageDialog(new JPanel(), "已到最后一页", "提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                jtable1.getSelectionModel().clearSelection();
                pages1++;
                buffer.clear();
                try {
                    if (Flag1 == 0)
                        buffer = ciDAO.getTable((pages1 - 1) * 10);
                    else buffer = ciDAO.getSortedTable((pages1 - 1) * 10, Sql1);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                Object obj1[][] = new Object[10][8];
                for (int i = 0; i < 10; i++)
                    for (int j = 0; j < 8; j++)
                        content1[i][j] = obj1[i][j];
                for (int j = 0; j < Math.min(buffer.size(), 10 * pages1); j++) {
                    CourseInfo temp = buffer.get(j);
                    try {
                        Object[] obj = temp.toObjectArray();
                        for (int i = 0; i < 8; i++)
                            content1[j][i] = obj[i];

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
            jtable1.updateUI();
        }
    }
    class sort1ActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String cid = scid1.getText();
            String cname = scname1.getText();
            String tid = stid1.getText();
            String tname = stname1.getText();
            String time = stime1.getText();
            long xf;
            Boolean hr = shr1.isSelected();
            StringBuilder sql = new StringBuilder("Select * from ci where ");

            int FLAG = 0;
            if (!cid.equals("")) {
                sql.append("cid = " + cid + " AND ");
                FLAG = 1;
            } else if (cid.equals("") && !cname.equals("")) {
                try {
                    sql.append("cid in (select cid from c where name = '" + cname + "') AND ");
                    FLAG = 1;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            if (!tid.equals("")) {
                sql.append("tid = " + tid + " AND ");
                FLAG = 1;
            } else if (tid.equals("") && !tname.equals("")) {
                try {
                    FLAG = 1;
                    sql.append("tid in (select tid from t where name = '" + tname + "') AND ");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (!time.equals("")) {
                sql.append("time = '" + time + "' AND ");
                FLAG = 1;
            }

            if (!sxf1.getText().equals("")) {
                xf = Long.parseLong(sxf1.getText());
                sql.append("xf = " + xf + " AND ");
                FLAG = 1;
            }

            if (hr == true) {
                sql.append("rs < cap AND ");
                FLAG = 1;
            }


            buffer.clear();

            if (FLAG == 1) {
                Flag1 = 1;
                sql.delete(sql.length() - 5, sql.length());
                Sql1 = sql.substring(23);
                try {
                    xkTableRefresh();
                    jtable1.updateUI();
                    jtable1.validate();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }


            if (FLAG == 0) {
                try {
                    Flag1 = 0;
                    xkTableRefresh();
                    jtable1.updateUI();
                    jtable1.validate();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }
    }
    class add1ActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            add1Window();
        }
    }
    class change1ActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                change1Window();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    class delete1ActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int[] select = jtable1.getSelectedRows();
            if(select.length == 0) return;
            for(int i : select){
                if(i > buffer.size()) continue;
                try {
                    csDAO.deleteOne(buffer.get(i).getCid());
                    ciDAO.deleteOneCourse(buffer.get(i).getCid());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            JOptionPane.showMessageDialog(null, "删除成功", "", JOptionPane.WARNING_MESSAGE);
            try {
                xkTableRefresh();
                wcourTableRefresh();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void add1Window(){
        JButton cfbtn1 = new JButton("确认添加");
        JFrame cont = new JFrame();
        Box box1 = Box.createVerticalBox();

        cont.add(box1);

        JLabel newcid1 = new JLabel("课程号");
        JTextField newCid1 = new JTextField();
        JLabel newcname1 = new JLabel("课程名：");
        JTextField newCname1 = new JTextField();
        JLabel newtname1 = new JLabel("教师名：");
        JTextField newTname1 = new JTextField();
        JLabel newtime1 = new JLabel("上课时间：");
        JTextField newTime1 = new JTextField();
        JLabel newxf1 = new JLabel("学分：");
        JTextField newXf1 = new JTextField();
        JLabel newcap1 = new JLabel("容量：");
        JTextField newCap1 = new JTextField();
        box1.add(newcid1);
        box1.add(newCid1);
        box1.add(newcname1);
        box1.add(newCname1);
        box1.add(newtname1);
        box1.add(newTname1);
        box1.add(newtime1);
        box1.add(newTime1);
        box1.add(newxf1);
        box1.add(newXf1);
        box1.add(newcap1);
        box1.add(newCap1);
        box1.add(cfbtn1);
        newCid1.setSize(new Dimension(150,30));
        newCname1.setSize(new Dimension(150,30));
        newTname1.setSize(new Dimension(150,30));
        newTime1.setSize(new Dimension(150,30));
        newXf1.setSize(new Dimension(150,30));
        newCap1.setSize(new Dimension(150,30));

        cfbtn1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String cid1 = newCid1.getText();
                String cn1 = newCname1.getText();
                String tid1 = new String();
                String tn1 = newTname1.getText();
                String nt1 = newTime1.getText();
                long xf1 = 0,cp1 = 0;
                CourseInfo temp = new CourseInfo();
                if(!newXf1.getText().equals("")){
                    xf1 = Long.parseLong(newXf1.getText());
                }
                if(!newCap1.getText().equals("")) {
                    cp1 = Long.parseLong(newCap1.getText());
                }
                if(cid1.equals("")||cn1.equals("")||tn1.equals("")||nt1.equals("")||newXf1.getText().equals("")||newCap1.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "表单填写不规范","" , JOptionPane.WARNING_MESSAGE);
                    cont.dispose();
                    return;
                }
                try {
                    temp = ciDAO.getInstanceByCID(cid1);
                    tid1 = tDAO.getIDByName(tn1);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if(temp != null){
                    JOptionPane.showMessageDialog(null, "课程号已存在", "", JOptionPane.WARNING_MESSAGE);
                    cont.dispose();
                    return;
                }
                else if(tid1 == null){
                    JOptionPane.showMessageDialog(null, "老师不存在", "", JOptionPane.WARNING_MESSAGE);
                    cont.dispose();
                    return;
                }
                else{
                    CourseInfo temp1  = new CourseInfo(tid1, cid1, nt1, xf1,0, cp1);
                    Course temp2 = new Course(cid1,cn1,xf1);
                    try {
                        ciDAO.createCourse(temp2);
                        ciDAO.addOne(temp1);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "添加成功", "", JOptionPane.WARNING_MESSAGE);
                    cont.dispose();

                    try {
                        xkTableRefresh();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });
        cont.setTitle("新建课程信息");
        cont.setVisible(true);
        cont.setSize(new Dimension(300,400));
        cont.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void change1Window() throws SQLException, IOException, ClassNotFoundException {
        JButton cfbtn1 = new JButton("确认修改");
        if(jtable1.getSelectedRows().length==0) return;
        int select = jtable1.getSelectedRows()[0];
        CourseInfo temp = buffer.get(select);
        JFrame cont = new JFrame();
        String name = new String();
        Box box1 = Box.createVerticalBox();

        cont.add(box1);

        JLabel newcname1 = new JLabel("课程名：");
        name = ciDAO.getNameByID(temp.getCid());
        JTextField newCname1 = new JTextField(name);
        JLabel newtname1 = new JLabel("教师名：");
        String teacher = tDAO.getNameByID(temp.getTid());
        JTextField newTname1 = new JTextField(teacher);
        JLabel newxf1 = new JLabel("学分：");
        JTextField newXf1 = new JTextField(String.valueOf(temp.getXf()));
        JLabel newcap1 = new JLabel("容量：");
        JTextField newCap1 = new JTextField(String.valueOf(temp.getCap()));
        box1.add(newcname1);
        box1.add(newCname1);
        box1.add(newtname1);
        box1.add(newTname1);
        box1.add(newxf1);
        box1.add(newXf1);
        box1.add(newcap1);
        box1.add(newCap1);
        box1.add(cfbtn1);
        newCname1.setSize(new Dimension(150,30));
        newTname1.setSize(new Dimension(150,30));
        newXf1.setSize(new Dimension(150,30));
        newCap1.setSize(new Dimension(150,30));
        cfbtn1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String test = null;
                try {
                    test = tDAO.getIDByName(newTname1.getText());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if(test == null){
                    JOptionPane.showMessageDialog(null, "老师不存在", "", JOptionPane.WARNING_MESSAGE);
                    cont.dispose();
                    return;
                }
                String tempcid = buffer.get(select).getCid();
                try {
                    ciDAO.updateCourse(tempcid, newCname1.getText(), newTname1.getText(),Long.parseLong(newXf1.getText()) , Long.parseLong(newCap1.getText()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                JOptionPane.showMessageDialog(null, "修改成功", "", JOptionPane.WARNING_MESSAGE);
                cont.dispose();

                try {
                    xkTableRefresh();
                    wcourTableRefresh();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        cont.setTitle("修改课程信息");
        cont.setVisible(true);
        cont.setSize(new Dimension(300,400));
        cont.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    class up2ActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (pages2 == 1) {
                JOptionPane.showMessageDialog(new JPanel(), "已到第一页", "提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Object obj1[][] = new Object[10][6];
                for (int i = 0; i < 10; i++)
                    for (int j = 0; j < 6; j++)
                        content2[i][j] = obj1[i][j];
                jtable2.getSelectionModel().clearSelection();
                pages2--;
                buffer1.clear();
                try {
                    if (Flag2 == 0)
                        buffer1 = csDAO.getWTable((pages2 - 1) * 10);
                    else buffer1 = csDAO.getWSortedTable((pages2 - 1) * 10, Sql2);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                for (int j = 0; j < Math.min(buffer1.size(), 10 * pages2); j++) {
                    CourseSelected temp = buffer1.get(j);
                    try {
                        Object[] obj = temp.toObjectArray2();
                        for (int i = 0; i < 6; i++)
                            content2[j][i] = obj[i];
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }

            jtable2.updateUI();
        }
    }
    class down2ActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (pages2 == count2) {
                JOptionPane.showMessageDialog(new JPanel(), "已到最后一页", "提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                jtable2.getSelectionModel().clearSelection();
                pages2++;
                buffer1.clear();
                try {
                    if (Flag2 == 0)
                        buffer1 = csDAO.getWTable((pages2 - 1) * 10);
                    else buffer1 = csDAO.getWSortedTable((pages2 - 1) * 10, Sql2);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                Object obj1[][] = new Object[10][6];
                for (int i = 0; i < 10; i++)
                    for (int j = 0; j < 8; j++)
                        content2[i][j] = obj1[i][j];
                for (int j = 0; j < Math.min(buffer1.size(), 10 * pages2); j++) {
                    CourseSelected temp = buffer1.get(j);
                    try {
                        Object[] obj = temp.toObjectArray2();
                        for (int i = 0; i < 6; i++)
                            content2[j][i] = obj[i];

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
            jtable2.updateUI();
        }
    }
    class sort2ActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String cid = scid2.getText();
            String cname = scname2.getText();
            String tid = stid2.getText();
            String tname = stname2.getText();
            String sid = ssid1.getText();
            String sname = ssname1.getText();
            StringBuilder sql = new StringBuilder("Select * from ci where ");

            int FLAG = 0;
            if (!cid.equals("")) {
                sql.append("cid = " + cid + " AND ");
                FLAG = 1;
            } else if (cid.equals("") && !cname.equals("")) {
                try {
                    sql.append("cid in (select cid from c where name = '" + cname + "') AND ");
                    FLAG = 1;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            if (!tid.equals("")) {
                sql.append("tid = " + tid + " AND ");
                FLAG = 1;
            } else if (tid.equals("") && !tname.equals("")) {
                try {
                    FLAG = 1;
                    sql.append("tid in (select tid from t where name = '" + tname + "') AND ");
                    System.out.println(sql);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            if (!sid.equals("")) {
                sql.append("sid = " + sid + " AND ");
                FLAG = 1;
            } else if (sid.equals("") && !sname.equals("")) {
                try {
                    FLAG = 1;
                    sql.append("sid in (select sid from s where name = '" + sname + "') AND ");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            buffer1.clear();

            if (FLAG == 1) {
                Flag2 = 1;
                sql.delete(sql.length() - 5, sql.length());
                Sql2 = sql.substring(23);
                try {
                    wcourTableRefresh();
                    jtable2.updateUI();
                    jtable2.validate();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }


            if (FLAG == 0) {
                try {
                    Flag2 = 0;
                    wcourTableRefresh();
                    jtable2.updateUI();
                    jtable2.validate();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }
    }
    class delete2ActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int[] select = jtable2.getSelectedRows();
            if(select.length == 0) return;
            for(int i : select){
                if(i > buffer1.size()) continue;
                try {
                    csDAO.deleteWOne(buffer1.get(i).getCid());
                    ciDAO.deleteRs(buffer1.get(i).getCid());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            JOptionPane.showMessageDialog(null, "删除成功", "", JOptionPane.WARNING_MESSAGE);
            try {
                wcourTableRefresh();
                xkTableRefresh();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    class change2ActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                change2Window();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void change2Window() throws SQLException, IOException, ClassNotFoundException {
        if(jtable2.getSelectedRows().length==0) return;
        JButton cfbtn1 = new JButton("确认修改");
        int select = jtable2.getSelectedRows()[0];
        CourseSelected temp = buffer1.get(select);
        JFrame cont = new JFrame();
        String name = new String();
        Box box1 = Box.createVerticalBox();
        cont.add(box1);
        JLabel score = new JLabel("分数：");
        JTextField Score = new JTextField();

        box1.add(score);
        box1.add(Score);
        box1.add(cfbtn1);
        cfbtn1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    csDAO.setScore(buffer1.get(select).getCid(), Long.parseLong(Score.getText()));
                    ciDAO.deleteRs(buffer1.get(select).getCid());
                    JOptionPane.showMessageDialog(null, "修改成功", "", JOptionPane.WARNING_MESSAGE);
                    wcourTableRefresh();
                    cont.dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        cont.setTitle("增加分数");
        cont.setVisible(true);
        cont.setSize(300,100);
        cont.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    class up3ActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (pages3 == 1) {
                JOptionPane.showMessageDialog(new JPanel(), "已到第一页", "提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Object obj1[][] = new Object[10][5];
                for (int i = 0; i < 10; i++)
                    for (int j = 0; j < 5; j++)
                        content1[i][j] = obj1[i][j];
                jtable3.getSelectionModel().clearSelection();
                pages3--;
                buffer2.clear();
                try {
                    if (Flag3 == 0)
                        buffer2 = sDAO.getTable((pages3 - 1) * 10);
                    else buffer2 = sDAO.getSortedTable((pages3 - 1) * 10, Sql3);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                for (int j = 0; j < Math.min(buffer2.size(), 10 * pages3); j++) {
                    Student temp = buffer2.get(j);
                    try {
                        Object[] obj = temp.toObjectArray();
                        for (int i = 0; i < 5; i++)
                            content3[j][i] = obj[i];
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }

            jtable3.updateUI();
        }
    }
    class down3ActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (pages3 == count3) {
                JOptionPane.showMessageDialog(new JPanel(), "已到最后一页", "提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                jtable3.getSelectionModel().clearSelection();
                pages3++;
                buffer2.clear();
                try {
                    if (Flag3 == 0)
                        buffer2 = sDAO.getTable((pages3 - 1) * 10);
                    else buffer2 = sDAO.getSortedTable((pages3 - 1) * 10, Sql3);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                Object obj1[][] = new Object[10][5];
                for (int i = 0; i < 10; i++)
                    for (int j = 0; j < 5; j++)
                        content3[i][j] = obj1[i][j];
                for (int j = 0; j < Math.min(buffer2.size(), 10 * pages3); j++) {
                    Student temp = buffer2.get(j);
                    try {
                        Object[] obj = temp.toObjectArray();
                        for (int i = 0; i < 5; i++)
                            content3[j][i] = obj[i];

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
            jtable3.updateUI();
        }
    }
    class sort3ActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String sid = ssid2.getText();
            String sname = ssname2.getText();
            String major = smajor.getText();
            String grade = sgrade.getText();


            StringBuilder sql = new StringBuilder("Select * from s where ");

            int FLAG = 0;
            if (!sid.equals("")) {
                sql.append("sid = " + sid + " AND ");
                FLAG = 1;
            } else if (sid.equals("") && !sname.equals("")) {
                try {
                    sql.append("sid in (select sid from s where name = '" + sname + "') AND ");
                    FLAG = 1;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }


            if (!major.equals("")) {
                sql.append("major = '" + major + "' AND ");
                FLAG = 1;
            }



            if (!grade.equals("")) {
                sql.append("grade = '" + grade + "' AND ");
                FLAG = 1;
            }


            buffer2.clear();

            if (FLAG == 1) {
                Flag3 = 1;
                sql.delete(sql.length() - 5, sql.length());
                Sql3 = sql.substring(22);
                try {
                    stuTableRefresh();
                    jtable3.updateUI();
                    jtable3.validate();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }


            if (FLAG == 0) {
                try {
                    Flag3 = 0;
                    stuTableRefresh();
                    jtable3.updateUI();
                    jtable3.validate();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }
    }
    class add3ActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            add3Window();
        }
    }
    class delete3ActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int[] select = jtable3.getSelectedRows();
            if(select.length == 0) return;
            for(int i : select){
                if(i > buffer2.size()) continue;
                try {
                    ciDAO.deleteRs1(buffer2.get(i).getSid());
                    csDAO.deleteBySID(buffer2.get(i).getSid());
                    sDAO.deleteStudent(buffer2.get(i));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            JOptionPane.showMessageDialog(null, "删除成功", "", JOptionPane.WARNING_MESSAGE);
            try {
                wcourTableRefresh();
                stuTableRefresh();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    class change3ActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                change3Window();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void add3Window(){
        JButton cfbtn1 = new JButton("确认添加");
        JFrame cont = new JFrame();
        Box box1 = Box.createVerticalBox();

        cont.add(box1);

        JLabel newsid1 = new JLabel("学号");
        JTextField newSid1 = new JTextField();
        JLabel newsname1 = new JLabel("学生名：");
        JTextField newSname1 = new JTextField();
        JLabel newpwd = new JLabel("密码：");
        JTextField newPwd = new JTextField();
        JLabel newmajor = new JLabel("专业名：");
        JTextField newMajor = new JTextField();
        JLabel newgrade = new JLabel("年级：");
        JTextField newGrade = new JTextField();


        box1.add(newsid1);
        box1.add(newSid1);
        box1.add(newsname1);
        box1.add(newSname1);
        box1.add(newpwd);
        box1.add(newPwd);
        box1.add(newmajor);
        box1.add(newMajor);
        box1.add(newgrade);
        box1.add(newGrade);
        box1.add(cfbtn1);


        cfbtn1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String sid1 = newSid1.getText();
                String sn1 = newSname1.getText();
                String pwd = newPwd.getText();
                String major = newMajor.getText();
                String grade = newGrade.getText();
                Student temp = new Student();


                if(sid1.equals("")||sn1.equals("")||pwd.equals("")||major.equals("")||grade.equals("")){
                    JOptionPane.showMessageDialog(null, "表单填写不规范","" , JOptionPane.WARNING_MESSAGE);
                    cont.dispose();
                    return;
                }
                try {
                    temp = sDAO.getStuInfo(sid1);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if(temp != null){
                    JOptionPane.showMessageDialog(null, "学号已存在", "", JOptionPane.WARNING_MESSAGE);
                    cont.dispose();
                    return;
                }

                else{
                    Student temp1 = new Student(sid1,sn1,pwd,major,grade);
                    try {
                        sDAO.addStudent(temp1);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "添加成功", "", JOptionPane.WARNING_MESSAGE);
                    cont.dispose();

                    try {
                        stuTableRefresh();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });
        cont.setTitle("新建课程信息");
        cont.setVisible(true);
        cont.setSize(new Dimension(300,300));
        cont.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void change3Window(){
        if(jtable3.getSelectedRows().length==0) return;
        int select = jtable3.getSelectedRows()[0];
        Student temp = buffer2.get(select);
        JButton cfbtn1 = new JButton("确认添加");
        JFrame cont = new JFrame();
        Box box1 = Box.createVerticalBox();

        cont.add(box1);

        JLabel newsid1 = new JLabel("学号");
        JTextField newSid1 = new JTextField(temp.getSid());
        JLabel newsname1 = new JLabel("学生名：");
        JTextField newSname1 = new JTextField(temp.getName());
        JLabel newpwd = new JLabel("密码：");
        JTextField newPwd = new JTextField(temp.getPword());
        JLabel newmajor = new JLabel("专业名：");
        JTextField newMajor = new JTextField(temp.getMajor());
        JLabel newgrade = new JLabel("年级：");
        JTextField newGrade = new JTextField(temp.getGrade());


        box1.add(newsid1);
        box1.add(newSid1);
        box1.add(newsname1);
        box1.add(newSname1);
        box1.add(newpwd);
        box1.add(newPwd);
        box1.add(newmajor);
        box1.add(newMajor);
        box1.add(newgrade);
        box1.add(newGrade);
        box1.add(cfbtn1);


        cfbtn1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String sid1 = newSid1.getText();
                String sn1 = newSname1.getText();
                String pwd = newPwd.getText();
                String major = newMajor.getText();
                String grade = newGrade.getText();
                Student temp = new Student();


                if(sid1.equals("")||sn1.equals("")||pwd.equals("")||major.equals("")||grade.equals("")){
                    JOptionPane.showMessageDialog(null, "表单填写不规范","" , JOptionPane.WARNING_MESSAGE);
                    cont.dispose();
                    return;
                }
                try {
                    temp = sDAO.getStuInfo(sid1);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if(temp != null&&!temp.getSid().equals(buffer2.get(select).getSid())){
                    JOptionPane.showMessageDialog(null, "学号已存在", "", JOptionPane.WARNING_MESSAGE);
                    cont.dispose();
                    return;
                }

                else{
                    Student temp1 = new Student(sid1,sn1,pwd,major,grade);
                    try {
                        csDAO.changeSidBySid(buffer2.get(select).getSid(), temp1.getSid());
                        sDAO.changeStudentBySid(buffer2.get(select).getSid(), temp1);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "添加成功", "", JOptionPane.WARNING_MESSAGE);
                    cont.dispose();

                    try {
                        xkTableRefresh();
                        stuTableRefresh();
                        wcourTableRefresh();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });
        cont.setTitle("修改学生信息");
        cont.setVisible(true);
        cont.setSize(new Dimension(300,300));
        cont.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    class up4ActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (pages4 == 1) {
                JOptionPane.showMessageDialog(new JPanel(), "已到第一页", "提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Object obj1[][] = new Object[10][5];
                for (int i = 0; i < 10; i++)
                    for (int j = 0; j < 3; j++)
                        content4[i][j] = obj1[i][j];
                jtable4.getSelectionModel().clearSelection();
                pages4--;
                buffer3.clear();
                try {
                    if (Flag4 == 0)
                        buffer3 = tDAO.getTable((pages4 - 1) * 10);
                    else buffer3 = tDAO.getSortedTable((pages4 - 1) * 10, Sql4);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                for (int j = 0; j < Math.min(buffer3.size(), 10 * pages4); j++) {
                    Teacher temp = buffer3.get(j);
                    try {
                        Object[] obj = temp.toObjectArray();
                        for (int i = 0; i < 3; i++)
                            content4[j][i] = obj[i];
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }

            jtable4.updateUI();
        }
    }
    class down4ActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (pages4 == count4) {
                JOptionPane.showMessageDialog(new JPanel(), "已到最后一页", "提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                jtable4.getSelectionModel().clearSelection();
                pages4++;
                buffer3.clear();
                try {
                    if (Flag4 == 0)
                        buffer3 = tDAO.getTable((pages4 - 1) * 10);
                    else buffer3 = tDAO.getSortedTable((pages4 - 1) * 10, Sql4);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                Object obj1[][] = new Object[10][5];
                for (int i = 0; i < 10; i++)
                    for (int j = 0; j < 3; j++)
                        content4[i][j] = obj1[i][j];
                for (int j = 0; j < Math.min(buffer3.size(), 10 * pages4); j++) {
                    Teacher temp = buffer3.get(j);
                    try {
                        Object[] obj = temp.toObjectArray();
                        for (int i = 0; i < 5; i++)
                            content4[j][i] = obj[i];

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
            jtable4.updateUI();
        }
    }
    class sort4ActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String tid = tid2.getText();
            String tname = tname2.getText();
            String major = tmajor.getText();

            StringBuilder sql = new StringBuilder("Select * from t where ");

            int FLAG = 0;
            if (!tid.equals("")) {
                sql.append("tid = " + tid + " AND ");
                FLAG = 1;
            } else if (tid.equals("") && !tname.equals("")) {
                try {
                    sql.append("tid in (select tid from t where name = '" + tname + "') AND ");
                    FLAG = 1;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }


            if (!major.equals("")) {
                sql.append("major = '" + major + "' AND ");
                FLAG = 1;
            }




            buffer3.clear();

            if (FLAG == 1) {
                Flag4 = 1;
                sql.delete(sql.length() - 5, sql.length());
                Sql4 = sql.substring(22);
                try {
                    teaTableRefresh();
                    jtable4.updateUI();
                    jtable4.validate();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }


            if (FLAG == 0) {
                try {
                    Flag4 = 0;
                    teaTableRefresh();
                    jtable4.updateUI();
                    jtable4.validate();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }
    }
    class add4ActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            add4Window();
        }
    }
    class delete4ActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int[] select = jtable4.getSelectedRows();
            if(select.length == 0) return;
            for(int i : select){
                if(i > buffer3.size()) continue;
                try {
                    ciDAO.deleteOneByTid(buffer3.get(i).getTid());
                    csDAO.deleteOneByTid(buffer3.get(i).getTid());
                    tDAO.deleteTeacher(buffer3.get(i));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            JOptionPane.showMessageDialog(null, "删除成功", "", JOptionPane.WARNING_MESSAGE);
            try {
                teaTableRefresh();
                wcourTableRefresh();
                xkTableRefresh();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    class change4ActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                change4Window();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void add4Window(){
        JButton cfbtn1 = new JButton("确认添加");
        JFrame cont = new JFrame();
        Box box1 = Box.createVerticalBox();

        cont.add(box1);

        JLabel newtid1 = new JLabel("教师号");
        JTextField newTid1 = new JTextField();
        JLabel newtname1 = new JLabel("教师名：");
        JTextField newTname1 = new JTextField();
        JLabel newmajor = new JLabel("所属院系：");
        JTextField newMajor = new JTextField();

        box1.add(newtid1);
        box1.add(newTid1);
        box1.add(newtname1);
        box1.add(newTname1);
        box1.add(newmajor);
        box1.add(newMajor);
        box1.add(cfbtn1);


        cfbtn1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String tid1 = newTid1.getText();
                String tn1 = newTname1.getText();
                String major = newMajor.getText();

                Teacher temp = new Teacher();


                if(tid1.equals("")||tn1.equals("")||major.equals("")){
                    JOptionPane.showMessageDialog(null, "表单填写不规范","" , JOptionPane.WARNING_MESSAGE);
                    cont.dispose();
                    return;
                }
                try {
                    temp = tDAO.getTeaInfo(tid1);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if(temp != null){
                    JOptionPane.showMessageDialog(null, "教师号已存在", "", JOptionPane.WARNING_MESSAGE);
                    cont.dispose();
                    return;
                }

                else{
                    Teacher temp1 = new Teacher(tid1,tn1,major);
                    try {
                        tDAO.addTeacher(temp1);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "添加成功", "", JOptionPane.WARNING_MESSAGE);
                    cont.dispose();

                    try {
                        teaTableRefresh();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });
        cont.setTitle("新建老师信息");
        cont.setVisible(true);
        cont.setSize(new Dimension(300,300));
        cont.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void change4Window(){
        if(jtable4.getSelectedRows().length==0) return;
        int select = jtable4.getSelectedRows()[0];
        Teacher temp = buffer3.get(select);
        JButton cfbtn1 = new JButton("确认添加");
        JFrame cont = new JFrame();
        Box box1 = Box.createVerticalBox();

        cont.add(box1);

        JLabel newtid1 = new JLabel("教师号");
        JTextField newTid1 = new JTextField(temp.getTid());
        JLabel newtname1 = new JLabel("教师名：");
        JTextField newTname1 = new JTextField(temp.getName());
        JLabel newmajor = new JLabel("专业名：");
        JTextField newMajor = new JTextField(temp.getMajor());

        box1.add(newtid1);
        box1.add(newTid1);
        box1.add(newtname1);
        box1.add(newTname1);
        box1.add(newmajor);
        box1.add(newMajor);
        box1.add(cfbtn1);


        cfbtn1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String tid1 = newTid1.getText();
                String tn1 = newTname1.getText();
                String major = newMajor.getText();

                Teacher temp = new Teacher();


                if(tid1.equals("")||tn1.equals("")||major.equals("")){
                    JOptionPane.showMessageDialog(null, "表单填写不规范","" , JOptionPane.WARNING_MESSAGE);
                    cont.dispose();
                    return;
                }
                try {
                    temp = tDAO.getTeaInfo(tid1);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if(temp != null&&!temp.getTid().equals(buffer3.get(select).getTid())){
                    JOptionPane.showMessageDialog(null, "教师已存在", "", JOptionPane.WARNING_MESSAGE);
                    cont.dispose();
                    return;
                }

                else{
                    Teacher temp1 = new Teacher(tid1,tn1,major);
                    try {
                          csDAO.changeTidByTid(buffer3.get(select).getTid(),tid1);
                          ciDAO.changeTidByTid(buffer3.get(select).getTid(),tid1);
                          tDAO.changeTeacherByTid(buffer3.get(select).getTid(), temp1);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "修改成功", "", JOptionPane.WARNING_MESSAGE);
                    cont.dispose();

                    try {
                        teaTableRefresh();
                        wcourTableRefresh();
                        xkTableRefresh();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });
        cont.setTitle("修改学生信息");
        cont.setVisible(true);
        cont.setSize(new Dimension(300,300));
        cont.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}
