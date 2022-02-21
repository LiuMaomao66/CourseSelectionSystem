package Swing;

import DAO.StuDAOImpl;
import DAO.TeaDAOImpl;
import DAO.courInfoDAOImpl;
import DAO.courSelDAOImpl;
import JavaBean.CourseInfo;
import JavaBean.CourseSelected;
import JavaBean.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentWindow extends JFrame {


    StuDAOImpl sDAO = new StuDAOImpl();
    TeaDAOImpl tDAO = new TeaDAOImpl();
    courInfoDAOImpl ciDAO = new courInfoDAOImpl();
    courSelDAOImpl csDAO = new courSelDAOImpl();

    List<CourseInfo> buffer = new ArrayList<>(10);
    List<CourseSelected> buffer1 = new ArrayList<>(10);
    List<CourseSelected> buffer2 = new ArrayList<>(10);



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
        选课界面变量
     */

    Box tabbox1;
    Box pagebox;
    Box sortbox;
    Box sb1;
    Box sb2;

    //选课表格
    JTable jtable;
    //以下组件用作选课筛选
    JTextField scid;
    JLabel SCID;
    JTextField scname;
    JLabel SCNAME;
    JTextField stid;
    JLabel STID;
    JTextField stname;
    JLabel STNAME;
    JTextField stime;
    JLabel STIME;
    JTextField sxf;
    JLabel SXF;
    JCheckBox shr;
    JLabel SHR;

    //维护的两个分页指针
    long count;
    long pages;
    //创建表格的两个成员，用于设置表格内容
    Object[] columnNames = {"课程号","课程名","教师号","教师名","上课时间","选课人数","总容量","学分"};
    Object[][] content = new Object[10][8];
    //上下页切换按钮、选课按钮、筛选按钮
    JButton Pageup;
    JButton Pagedown;
    JButton xk;
    JButton sort;
    int Flag = 0;//筛选标志符
    String Sql;//语句筛选后缀



    /*
        退课界面变量
     */

    //已选择的课表格
    Box tkbox;
    Box tkbox1;
    Box tkbox2;
    Box btnbox;

    JTable tkTab;
    JTable courseTab;
    JLabel selectxf;

    JButton tkButton;

    //退课表中的元素
    Object[] name2 = {"课程号","课程名","教师号","教师名","上课时间","选课人数","总容量","学分"};
    Object[][] content2 = new Object[10][8];

    long selxf;
    //课程安排表元素
    Object[] kname = {"","","","","","",""};
    Object[][] content3 = new Object[13][7];



    /*
        已选课程界面变量
     */
    Box tabbox2;
    Box pagebox1;
    Box sortbox1;
    Box sb3;
    Box sb4;

    long count1;
    long pages1;

    JTable seltable;

    Object[] name3 = {"课程号","课程名","教师号","教师名","上课时间","总评成绩","学分"};
    Object[][] content4 = new Object[10][7];

    JLabel allXf;
    JButton Pageup1;
    JButton Pagedown1;
    JButton Sort1;

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

    int Flag1 = 0;//筛选标志符
    String Sql1;//语句筛选后缀


    /**
     * 选项卡面板
     */

    StudentWindow(String Sid) throws SQLException, IOException, ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        box = Box.createVerticalBox();
        /**
         * 个人信息初始化
         */

        Student stu = sDAO.getStuInfo(Sid);

        sid = stu.getSid();
        name = stu.getName();
        major = stu.getMajor();
        grade = stu.getGrade();


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
        jtp.addTab("选课", jp1);
        JPanel jp2 = new JPanel();
        jtp.addTab("退课", jp2);
        JPanel jp3 = new JPanel();
        jtp.addTab("已修课程", jp3);


        box.add(jp);
        box.add(jtp);
        add(box);
        /*------------------------------------------------------------------------------------------------*/

        xkWindow(jp1);
        tkWindow(jp2);
        selWindow(jp3);



/*-------------------------------------------------------------------------------*/
        /*
            结构默认和容器调整
         */
        jp.setPreferredSize(new Dimension(0,40));
        setResizable(false);
        setTitle("学生选课界面");
        setVisible(true);
        setSize(new Dimension(900,600));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /*
    功能实现方法
     */
    //选课界面
    public void xkWindow(JPanel jp1) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        tabbox1 = Box.createVerticalBox();
        pagebox = Box.createHorizontalBox();
        Pageup = new JButton("上一页");
        Pagedown = new JButton("下一页");
        xk = new JButton("选课");

        jtable = new JTable(content, columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

//        JComboBox valueAt =(JComboBox) jtable.getModel().getValueAt(0, 8);

        // 设置表格内容颜色
        jtable.setForeground(Color.BLACK);                   // 字体颜色
        jtable.setFont(new Font(null, Font.PLAIN, 14));      // 字体样式
        jtable.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        jtable.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        jtable.setGridColor(Color.GRAY);                     // 网格颜色
        jtable.getTableHeader().setFont(new Font(null, Font.BOLD, 14));
        jtable.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        jtable.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列

        tabbox1.add(jtable.getTableHeader());
        tabbox1.add(jtable);
        pagebox.add(Box.createHorizontalStrut(600));
        pagebox.add(xk);
        pagebox.add(Box.createHorizontalStrut(10));
        pagebox.add(Pageup);
        pagebox.add(Box.createHorizontalStrut(10));
        pagebox.add(Pagedown);
        tabbox1.add(Box.createVerticalStrut(20));
        tabbox1.add(pagebox);
        jp1.add(tabbox1);


        xkTableRefresh();
        Pageup.addActionListener(new UpActionListener());
        Pagedown.addActionListener(new DownActionListener());
        xk.addActionListener(new xkActionListener());



        jtable.setPreferredSize(new Dimension(800,160));





        sortbox = Box.createVerticalBox();
        tabbox1.add(Box.createVerticalStrut(30));
        tabbox1.add(sortbox);
        sb1 = Box.createHorizontalBox();
        sb2 = Box.createHorizontalBox();
        sortbox.add(sb1);
        sortbox.add(Box.createVerticalStrut(20));
        sortbox.add(sb2);
        SCID = new JLabel("课程号：");
        sb1.add(SCID);
        scid = new JTextField();
        sb1.add(scid);
        SCNAME = new JLabel("课程名：");
        sb1.add(SCNAME);
        scname = new JTextField();
        sb1.add(scname);
        STID = new JLabel("教师号：");
        sb1.add(STID);
        stid = new JTextField();
        sb1.add(stid);
        STNAME = new JLabel("教师名：");
        sb1.add(STNAME);
        stname = new JTextField();
        sb1.add(stname);
        STIME = new JLabel("课程时间：");
        sb2.add(STIME);
        stime = new JTextField(10);
        sb2.add(stime);
        SXF = new JLabel("学分：");
        sb2.add(SXF);
        sxf = new JTextField();
        sb2.add(sxf);
        sb2.add(Box.createHorizontalStrut(20));
        SHR = new JLabel("人数未满：");
        sb2.add(SHR);
        shr = new JCheckBox();
        sb2.add(shr);
        sort = new JButton("搜索");
        sb2.add(Box.createHorizontalStrut(50));
        sb2.add(sort);

        sb2.add(Box.createHorizontalStrut(300));
        sort.addActionListener(new sortActionListener());


    }

    //退课界面
    public void tkWindow(JPanel jp2) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException{
        tkbox = Box.createVerticalBox();
        tkbox1 = Box.createVerticalBox();
        tkbox2 = Box.createHorizontalBox();
        btnbox = Box.createHorizontalBox();
        selxf = csDAO.getSelXf(sid);
        tkButton = new JButton("退课");
        selectxf = new JLabel("当前已选择课程总学分： "+selxf);
        tkTab = new JTable(content2,name2){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        courseTab = new JTable(content3,kname){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        courseTabInit(content3);

        tkTab.setForeground(Color.BLACK);                   // 字体颜色
        tkTab.setFont(new Font(null, Font.PLAIN, 14));      // 字体样式
        tkTab.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        tkTab.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        tkTab.setGridColor(Color.GRAY);                     // 网格颜色
        tkTab.getTableHeader().setFont(new Font(null, Font.BOLD, 14));
        tkTab.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        tkTab.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列

        courseTab.setForeground(Color.BLACK);                   // 字体颜色
        courseTab.setFont(new Font(null, Font.PLAIN, 14));      // 字体样式
        courseTab.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        courseTab.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        courseTab.setGridColor(Color.GRAY);                     // 网格颜色
        courseTab.getTableHeader().setFont(new Font(null, Font.BOLD, 14));
        courseTab.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        courseTab.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列
        courseTab.getColumnModel().getColumn(0).setPreferredWidth(5);


        jp2.add(tkbox);
        tkbox.add(tkbox1);
        tkbox.add(Box.createVerticalStrut(50));
        tkbox.add(tkbox2);

        tkbox1.add(tkTab.getTableHeader());
        tkbox1.add(tkTab);
        tkbox1.add(btnbox);
        btnbox.add(Box.createHorizontalStrut(500));
        btnbox.add(selectxf);
        btnbox.add(Box.createHorizontalStrut(30));
        btnbox.add(tkButton);
        tkButton.addActionListener(new tkActionListener());

        tkbox2.add(courseTab);
        DefaultTableCellRenderer r=new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        courseTab.setDefaultRenderer(Object.class,r);
        
        tkTableRefresh();
        refreshCourTab();


    }

    //已修课程
    public void selWindow(JPanel jp3) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        tabbox2 = Box.createVerticalBox();
        pagebox1 = Box.createHorizontalBox();
        Pageup1 = new JButton("上一页");
        Pagedown1 = new JButton("下一页");
        allXf = new JLabel();

        seltable = new JTable(content4, name3){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

//        JComboBox valueAt =(JComboBox) jtable.getModel().getValueAt(0, 8);

        // 设置表格内容颜色
        seltable.setForeground(Color.BLACK);                   // 字体颜色
        seltable.setFont(new Font(null, Font.PLAIN, 14));      // 字体样式
        seltable.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        seltable.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        seltable.setGridColor(Color.GRAY);                     // 网格颜色
        seltable.getTableHeader().setFont(new Font(null, Font.BOLD, 14));
        seltable.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        seltable.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列

        tabbox2.add(seltable.getTableHeader());
        tabbox2.add(seltable);
        pagebox1.add(allXf);
        pagebox1.add(Box.createHorizontalStrut(500));
        pagebox1.add(Box.createHorizontalStrut(10));
        pagebox1.add(Pageup1);
        pagebox1.add(Box.createHorizontalStrut(10));
        pagebox1.add(Pagedown1);
        tabbox2.add(Box.createVerticalStrut(20));
        tabbox2.add(pagebox1);
        jp3.add(tabbox2);


        selTableRefresh();
        Pageup1.addActionListener(new Up1ActionListener());
        Pagedown1.addActionListener(new Down1ActionListener());

        seltable.setPreferredSize(new Dimension(800,160));


        sortbox1 = Box.createVerticalBox();
        tabbox2.add(Box.createVerticalStrut(30));
        tabbox2.add(sortbox1);
        sb3 = Box.createHorizontalBox();
        sb4 = Box.createHorizontalBox();
        sortbox1.add(sb3);
        sortbox1.add(Box.createVerticalStrut(20));
        sortbox1.add(sb4);
        SCID1 = new JLabel("课程号：");
        sb3.add(SCID1);
        scid1 = new JTextField();
        sb3.add(scid1);
        SCNAME1 = new JLabel("课程名：");
        sb3.add(SCNAME1);
        scname1 = new JTextField();
        sb3.add(scname1);
        STID1 = new JLabel("教师号：");
        sb3.add(STID1);
        stid1 = new JTextField();
        sb3.add(stid1);
        STNAME1 = new JLabel("教师名：");
        sb3.add(STNAME1);
        stname1 = new JTextField();
        sb3.add(stname1);
        STIME1 = new JLabel("课程时间：");
        sb4.add(STIME1);
        stime1 = new JTextField(10);
        sb4.add(stime1);
        SXF1 = new JLabel("学分：");
        sb4.add(SXF1);
        sxf1 = new JTextField();
        sb4.add(sxf1);
        sb4.add(Box.createHorizontalStrut(20));

        Sort1 = new JButton("搜索");
        sb4.add(Box.createHorizontalStrut(50));
        sb4.add(Sort1);

        sb4.add(Box.createHorizontalStrut(300));
        Sort1.addActionListener(new sort1ActionListener());


    }

    //刷新选课表
    public void xkTableRefresh() throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        pages = 1;
        if(Flag == 1){
            count = ciDAO.getSortedLength(Sql) / 10 + 1;
            buffer = ciDAO.getSortedTable(pages - 1, Sql);
        }else {
            count = ciDAO.getLength() / 10 + 1;
            buffer = ciDAO.getTable(pages - 1);
        }
        for(int i = 0; i < content.length; i++)
            for(int j = 0; j < content[i].length; j++)
                content[i][j] = null;
        if(buffer == null||buffer.size()==0)  {
            jtable.updateUI();
            return;
        }
        for(int i = 0; i < Math.min(content.length, buffer.size()); i++) {
            CourseInfo temp = buffer.get(i);
            Object[] contain = temp.toObjectArray();
            for (int j = 0; j < content[i].length; j++) {
                content[i][j] =  contain[j];
            }
        }
        jtable.updateUI();

    }

    //刷新退课表
    public void tkTableRefresh() throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        buffer1 = csDAO.getList(sid);
        for(int i = 0; i < content2.length; i++)
            for(int j = 0; j < content2[i].length; j++)
                content2[i][j] = null;
        if(buffer1 == null)  {
            tkTab.updateUI();
            return;
        }
        for(int i = 0; i < Math.min(10, buffer1.size()); i++) {
            CourseSelected temp = buffer1.get(i);
            Object[] contain = temp.toObjectArray();
            for (int j = 0; j < content2[i].length; j++) {
                content2[i][j] =  contain[j];
            }
        }
        tkTab.updateUI();
    }

    //刷新课程安排表
    public void refreshCourTab() throws SQLException, IOException, ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        buffer1 = csDAO.getList(sid);
        for(int i = 1; i < content3.length; i++)
            for(int j = 2; j < content3[i].length; j++)
                content3[i][j] = null;
        for(CourseSelected i : buffer1){
            CourseInfo temp = ciDAO.getInstanceByCID(i.getCid());
            String str = temp.getTime();
            String[] arr = str.split(",");
            for(String j : arr){
                String Day = j.substring(0,1);
                int mid = j.indexOf("-");
                int start = Integer.parseInt(j.substring(1, mid));
                int end = Integer.parseInt(j.substring(mid+1));
                int dayColumn = 100;
                for(int p = 2; p < content3[0].length; p++)
                    if(Day.equals(content3[0][p])) {
                        dayColumn = p;
                    }
                for(int k = start; k <= end; k++)
                    content3[k][dayColumn] = ciDAO.getNameByID(temp.getCid());
            }
        }
        courseTab.updateUI();
    }

    //课程安排表初始化
    public void courseTabInit(Object[][] content3){
        content3[0][0] = "#";
        for(int i = 1; i < content3.length; i++)
            content3[i][0] = i;
        content3[0][1] = "上课时间";
        content3[1][1] = "8:00-8:45";
        content3[2][1] = "8:55-9:40";
        content3[3][1] = "10:00-10:45";
        content3[4][1] = "10:55-11:40";
        content3[5][1] = "13:00-13:45";
        content3[6][1] = "13:55-14:40";
        content3[7][1] = "15:00-15:45";
        content3[8][1] = "15:55-16:40";
        content3[9][1] = "18:00-18:45";
        content3[10][1] = "18:55-19:40";
        content3[11][1] = "20:00-20:45";
        content3[12][1] = "20:55-21:40";
        content3[0][2] ="一";
        content3[0][3] ="二";
        content3[0][4] ="三";
        content3[0][5] ="四";
        content3[0][6] ="五";

    }

    //刷新已选课程安排表
    public void selTableRefresh() throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        pages1 = 1;
        if(Flag1 == 1){
            count1 = csDAO.getSortedLength(Sql1,sid) / 10 + 1;
            buffer2 = csDAO.getSortedTable(pages1 - 1, Sql1, sid);
        }else{
            count1 = csDAO.getLength(sid) / 10 + 1;
            buffer2 = csDAO.getTable(pages1 - 1, sid);
        }
        for (int i = 0; i < content4.length; i++)
            for (int j = 0; j < content4[i].length; j++)
                content4[i][j] = null;
        if (buffer2 == null) {
            seltable.updateUI();
            return;
        }
        for (int i = 0; i < Math.min(10, buffer2.size()); i++) {
            CourseSelected temp = buffer2.get(i);
            Object[] contain = temp.toObjectArray1();
            for (int j = 0; j < content4[i].length; j++) {
                content4[i][j] =  contain[j];
            }
        }
        allXf.setText("已修课程的总学分为："+csDAO.getAllXf(sid));
        seltable.updateUI();
    }


    /*
    事件监听器
     */
        class UpActionListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pages == 1) {
                    JOptionPane.showMessageDialog(new JPanel(), "已到第一页", "提示", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    Object obj1[][] = new Object[10][8];
                    for (int i = 0; i < 10; i++)
                        for (int j = 0; j < 8; j++)
                            content[i][j] = obj1[i][j];
                    jtable.getSelectionModel().clearSelection();
                    pages--;
                    buffer.clear();
                    try {
                        if (Flag == 0)
                            buffer = ciDAO.getTable((pages - 1) * 10);
                        else buffer = ciDAO.getSortedTable((pages - 1) * 10, Sql);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    for (int j = 0; j < Math.min(buffer.size(), 10 * pages); j++) {
                        CourseInfo temp = buffer.get(j);
                        try {
                            Object[] obj = temp.toObjectArray();
                            for (int i = 0; i < 8; i++)
                                content[j][i] = obj[i];
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }

                jtable.updateUI();
            }
        }

        class DownActionListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pages == count) {
                    JOptionPane.showMessageDialog(new JPanel(), "已到最后一页", "提示", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    jtable.getSelectionModel().clearSelection();
                    pages++;
                    buffer.clear();
                    try {
                        if (Flag == 0)
                            buffer = ciDAO.getTable((pages - 1) * 10);
                        else buffer = ciDAO.getSortedTable((pages - 1) * 10, Sql);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    Object obj1[][] = new Object[10][8];
                    for (int i = 0; i < 10; i++)
                        for (int j = 0; j < 8; j++)
                            content[i][j] = obj1[i][j];
                    for (int j = 0; j < Math.min(buffer.size(), 10 * pages); j++) {
                        CourseInfo temp = buffer.get(j);
                        try {
                            Object[] obj = temp.toObjectArray();
                            for (int i = 0; i < 8; i++)
                                content[j][i] = obj[i];

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                jtable.updateUI();
            }
        }


        class xkActionListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selected = jtable.getSelectedRows();
                CourseInfo temp;
                List<CourseInfo> fail = new ArrayList<>();
                for (int i : selected) {
                    if (i > buffer.size()) {
                        JOptionPane.showMessageDialog(null, "选课失败", "Error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                for (int i : selected) {
                    temp = buffer.get(i);
                    for (CourseSelected j : buffer1) {
                        String[] str1 = temp.getTime().split(",");
                        String[] str2 = new String[4];
                        try {
                            str2 = ciDAO.getTimeByCTID(j.getCid(), j.getTid()).split(",");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        if (str2 == null) break;
                        for (String s1 : str1) {
                            for (String s2 : str2) {
                                if (s1.substring(0, 1).equals(s2.substring(0, 1))) {
                                    int m = s1.indexOf("-");
                                    int n = s2.indexOf("-");
                                    int m1 = Integer.parseInt(s1.substring(1, m));
                                    int n1 = Integer.parseInt(s2.substring(1, n));

                                    int m2 = Integer.parseInt(s1.substring(m + 1));
                                    int n2 = Integer.parseInt(s2.substring(n + 1));
                                    if ((m1 - n2 <= 0) && (n1 - m2 <= 0)) {
                                        fail.add(temp);
                                        break;
                                    }
                                }
                            }
                            if (fail.contains(temp)) break;
                        }
                        if (fail.contains(temp)) break;
                    }
                    if (fail.contains(temp)) {
                        continue;
                    } else if (selected.length != 0) {
                        CourseSelected cs = new CourseSelected(temp, sid);
                        try {
                            csDAO.addOne(cs);
                            ciDAO.addRs(cs.getCid());
                            selxf += temp.getXf();
                            buffer1 = csDAO.getList(sid);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                if (fail.size() != 0) {
                    StringBuffer opStr = new StringBuffer();
                    for (CourseInfo i : fail) {
                        try {
                            opStr.append("" + ciDAO.getNameByID(i.getCid()) + "\n");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    JOptionPane.showMessageDialog(null, "以下课程时间冲突:\n" + opStr, "选课提示", JOptionPane.WARNING_MESSAGE);
                } else if (selected.length != 0) {
                    JOptionPane.showMessageDialog(new JPanel(), "选课成功", "选课提示", JOptionPane.INFORMATION_MESSAGE);
                }
                try {
                    selectxf.setText("当前已选择课程总学分： " + selxf);
                    xkTableRefresh();
                    tkTableRefresh();
                    refreshCourTab();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        class sortActionListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cid = scid.getText();
                String cname = scname.getText();
                String tid = stid.getText();
                String tname = stname.getText();
                String time = stime.getText();
                long xf;
                Boolean hr = shr.isSelected();

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
                    sql.append("tid in (select tid from t where name = '" + tname + "') AND ");
                    FLAG = 1;
                } else if (tid.equals("") && !tname.equals("")) {
                    try {
                        FLAG = 1;
                        sql.append("tid = " + tDAO.getIDByName(tname) + " AND ");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                if (!time.equals("")) {
                    sql.append("time = '" + time + "' AND ");
                    FLAG = 1;
                }

                if (!sxf.getText().equals("")) {
                    xf = Long.parseLong(sxf.getText());
                    sql.append("xf = " + xf + " AND ");
                    FLAG = 1;
                }

                if (hr == true) {
                    sql.append("rs < cap AND ");
                    FLAG = 1;
                }


                buffer.clear();

                if (FLAG == 1) {
                    Flag = 1;
                    sql.delete(sql.length() - 5, sql.length());
                    Sql = sql.substring(23);
                    try {
                        xkTableRefresh();
                        jtable.updateUI();
                        jtable.validate();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }


                if (FLAG == 0) {
                    try {
                        Flag = 0;
                        xkTableRefresh();
                        jtable.updateUI();
                        jtable.validate();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }

        class tkActionListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<CourseSelected> Buf = new ArrayList<>();
                int[] selected = tkTab.getSelectedRows();
                CourseSelected temp;
                if (selected.length == 0) return;

                for (int i : selected) {
                    if (i >= buffer1.size()) {
                        JOptionPane.showMessageDialog(null, "退课失败", "Error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                for (int i : selected) {
                    temp = buffer1.get(i);
                    try {
                        csDAO.deleteOne(temp);
                        ciDAO.deleteRs(temp.getCid());
                        selxf -= ciDAO.getXfByID(temp.getCid());
                        Buf.add(temp);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                StringBuffer opStr = new StringBuffer();
                for (CourseSelected i : Buf) {
                    try {
                        opStr.append("" + ciDAO.getNameByID(i.getCid()) + "\n");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                JOptionPane.showMessageDialog(null, "成功退选以下课程:\n" + opStr.toString(), "退课提示", JOptionPane.WARNING_MESSAGE);
                try {
                    selectxf.setText("当前已选择课程总学分： " + selxf);
                    xkTableRefresh();
                    tkTableRefresh();
                    refreshCourTab();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        class Up1ActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (pages1 == 1) {
                JOptionPane.showMessageDialog(new JPanel(), "已到第一页", "提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Object obj1[][] = new Object[10][7];
                for (int i = 0; i < 10; i++)
                    for (int j = 0; j < 7; j++)
                        content4[i][j] = obj1[i][j];
                seltable.getSelectionModel().clearSelection();
                pages1--;
                buffer2.clear();
                try {
                    if (Flag1 == 0)
                        buffer2 = csDAO.getTable((pages1 - 1) * 10,sid);
                    else buffer2 = csDAO.getSortedTable((pages1 - 1) * 10, Sql1,sid);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                for (int j = 0; j < Math.min(buffer2.size(), 10 * pages1); j++) {
                    CourseSelected temp = buffer2.get(j);
                    try {
                        Object[] obj = temp.toObjectArray1();
                        for (int i = 0; i < 7; i++)
                            content4[j][i] = obj[i];
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }

            seltable.updateUI();
        }
    }

        class Down1ActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (pages1 == count1) {
                System.out.println(1);
                JOptionPane.showMessageDialog(new JPanel(), "已到最后一页", "提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println(2);
                seltable.getSelectionModel().clearSelection();
                pages1++;
                buffer2.clear();
                try {
                    if (Flag1 == 0)
                        buffer2 = csDAO.getTable((pages1 - 1) * 10,sid);
                    else buffer2 = csDAO.getSortedTable((pages1 - 1) * 10, Sql1,sid);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                Object obj1[][] = new Object[10][7];
                for (int i = 0; i < 10; i++)
                    for (int j = 0; j < 7; j++)
                        content4[i][j] = obj1[i][j];
                for (int j = 0; j < Math.min(buffer2.size(), 10 * pages1); j++) {
                    CourseSelected temp = buffer2.get(j);
                    try {
                        Object[] obj = temp.toObjectArray1();
                        for (int i = 0; i < 7; i++)
                            content4[j][i] = obj[i];

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
            seltable.updateUI();
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


            StringBuilder sql = new StringBuilder("Select * from cs where ");

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


            buffer2.clear();

            if (FLAG == 1) {

                try {
                    Flag1 = 1;
                    sql.delete(sql.length() - 5, sql.length());
                    Sql1 = sql.substring(23);
                    selTableRefresh();
                    seltable.updateUI();
                    seltable.validate();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }


            if (FLAG == 0) {
                try {
                    Flag1 = 0;
                    selTableRefresh();
                    seltable.updateUI();
                    seltable.validate();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}





