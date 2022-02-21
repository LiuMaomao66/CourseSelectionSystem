package Swing;

import DAO.LoginDAOImpl;
import JDBCUtils.ScreenData;
import Swing.Utils.BackGroundPanel;
import Swing.Utils.RoundBorder;
import Swing.Utils.TextBorderUtlis;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class LoginWindow extends JFrame {
    /**
     * 登陆窗口的宽
     */
    final int LOGIN_WIDTH = 500;
    /**
     * 登陆窗口的高
     */
    final int LOGIN_HEIGHT = 300;
    /**
     * 背景图片
     */
    Image backImg;
    /**
     * 窗口容器
     */
    BackGroundPanel bgPanel;

    /**
     * 标题容器
     */
    JPanel panel = new JPanel();


    /**
     * status：登录身份
     */
    private JComboBox status;
    /**
     * Title: 标题
     */
    private  JLabel Title;
    /**
     * Label：用户名
     */
    private JLabel userLabel;
    /**
     * Label：密码
     */
    private JLabel psdLabel;

    /**
     * 单行文本域：输入用户名
     */
    private JTextField userTextField;

    /**
     * 单行文本域：输入密码
     */
    private JPasswordField psdTextField;

    /**
     * Button：登陆按钮
     */
    private JButton loginbtn;

    LoginDAOImpl DAO = new LoginDAOImpl();


    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        new LoginWindow();
    }

    LoginWindow() throws IOException, SQLException, ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        init();
    }
    public void init() throws IOException, SQLException, ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException, NoSuchMethodException {


        TextBorderUtlis utlis = new TextBorderUtlis(Color.WHITE,1,true);
        this.setLayout(null);

        // 设置窗口的内容
        bgPanel = new BackGroundPanel(ImageIO.read(new File("images\\img.png")));
        // 组装登陆相关的元素

        Box VBox = Box.createVerticalBox();
        Box tBox = Box.createHorizontalBox();
        Title = new JLabel("上海大学选课模拟系统");
        Title.setFont(new Font("宋体",Font.BOLD,20));
        tBox.add(Title);

        // 组装用户名
        Box uBox = Box.createHorizontalBox();
        userLabel = new JLabel("学号:");
        userTextField = new JTextField(15);

        uBox.add(Box.createVerticalStrut(15));
        uBox.add(userLabel);
        uBox.add(Box.createHorizontalStrut(15));
        uBox.add(userTextField);

        // 组装密码
        Box pBox = Box.createHorizontalBox();
        psdLabel = new JLabel("密码:");
        psdTextField = new JPasswordField(15);

        pBox.add(psdLabel);
        pBox.add(Box.createHorizontalStrut(15));
        pBox.add(psdTextField);

        //
        JPanel bPanel = new JPanel();
        bPanel.setLayout(new FlowLayout());

        loginbtn = new JButton("登陆");
        status = new JComboBox<String>();
        status.addItem("管理员");
        status.addItem("学生");


        VBox.add(tBox);
        VBox.add(Box.createVerticalStrut(21));
        VBox.add(uBox);
        VBox.add(Box.createVerticalStrut(11));
        VBox.add(pBox);
        VBox.add(Box.createVerticalStrut(11));
        bPanel.add(status);
        bPanel.add(loginbtn);
        VBox.add(bPanel);
        panel.add(VBox);

        this.add(bgPanel);
        this.add(panel,new GridLayout(1,1));





        login();




        // 自由布局
        userTextField.setBorder(utlis);
        userTextField.setPreferredSize(new Dimension(0,20));
        psdTextField.setBorder(utlis);
        psdTextField.setPreferredSize(new Dimension(0,20));
        bgPanel.setBounds(0, 0, 250, 80);
        panel.setBounds(0, 100, 230, 500);
        loginbtn.setPreferredSize(new Dimension(80, 30));
        loginbtn.setBorder(BorderFactory.createRaisedBevelBorder());
        loginbtn.setBorder(new RoundBorder());
        psdTextField.setBorder(new RoundBorder());
        userTextField.setBorder(new RoundBorder());
        setResizable(false);
        setTitle("SHU");
        setBounds((ScreenData.getScreenWidth()-LOGIN_WIDTH)/2,(ScreenData.getScreenHeight()-LOGIN_WIDTH)/2,LOGIN_WIDTH/2,LOGIN_HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void login() throws SQLException, IOException, ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        loginbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String xh = userTextField.getText(),pw,pw1 = psdTextField.getText();
                if("学生".equals((String) status.getSelectedItem())){
                    try {
                        System.out.println(xh);
                        System.out.println(pw1);
                        pw = DAO.getStuPword(xh);
                        System.out.println(pw);
                        if(pw == null ||!(pw1.equals(pw)))
                            JOptionPane.showMessageDialog(new JPanel(), "密码错误", "错误提示",JOptionPane.WARNING_MESSAGE);
                        else{
                            dispose();
                            new StudentWindow(xh);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }else if("管理员".equals((String) status.getSelectedItem())){
                    try {
                        pw = DAO.getAdmPword(xh);
                        if(pw == null ||!(pw1.equals(pw)))
                            JOptionPane.showMessageDialog(new JPanel(), "密码错误", "错误提示",JOptionPane.WARNING_MESSAGE);
                        else{
                            dispose();
                            new AdminWindow(xh);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
}
