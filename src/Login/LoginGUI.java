package Login;

import Client.TCPClient1;
import MySQL.CheckAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginGUI {
    public static String rName;
    public static char[] rPassword;
    public static void runLoginGUi() {
        JFrame jf = new JFrame("Login");
        JButton login_now = new JButton("Login now");
        JButton register = new JButton("Register");
        JButton quit = new JButton("Quit");
        // 用户名和密码框都是二十列
        JTextField username = new JTextField(20);
        JPasswordField password  = new JPasswordField(20);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 布局
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        jf.setLayout(layout);
        //组件填充显示区域，当格子有剩余空间时，填充空间
        gbc.fill = GridBagConstraints.BOTH;
        jf.setSize(400, 200);
        jf.setLocation(600, 300);
        // 添加标签1
        Add_Component(jf, layout,new JLabel("username: "),gbc,0,0,1,1,0,0);
        // 右边添加用户名输入框
        Add_Component(jf, layout, username, gbc,1,0,1,0,0,0);
        // 使用占用整行的标签进行分割
        Add_Component(jf, layout, new JLabel(" "), gbc,0,2,1,0,0,0);
        // 添加标签2
        Add_Component(jf, layout, new JLabel("password: "), gbc,0,3,1,1,0,0);
        // 添加密码框
        Add_Component(jf, layout, password, gbc,1,3,1,0,0,0);
        // 使用占用整行的标签进行分割
        Add_Component(jf, layout, new JLabel(" "), gbc,0,4,1,0,0,0);
        // 添加按钮和分割标签
        Add_Component(jf, layout, login_now, gbc,0,5,1,1,0,0);
        Add_Component(jf, layout, new JLabel("  "), gbc,1,5,1,1,0,0);
        Add_Component(jf, layout, register, gbc,2,5,1,1,0,0);
        Add_Component(jf, layout, new JLabel("  "), gbc,3,5,1,1,0,0);
        Add_Component(jf, layout, quit, gbc,4,5,1,1,0,0);
        jf.setVisible(true);

        // 事件触发：退出按钮
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // 事件触发：登录
        login_now.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rName = username.getText();
                rPassword = password.getPassword();
                boolean check = CheckAccount.checkAccount(rName, rPassword);
                if (check) {
                    try {
                        LoginThread.login();
                        jf.dispose();
                    } catch (IOException | InterruptedException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "User not found", "Notice", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 事件触发：注册
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateAccountGUI.runCreate();
                jf.dispose();
            }
        });
    }


    /* Add_Component添加组件函数，形式参数从左到右依次为
       JFrame jfr：顶层容器
       GridBagLayout gbl：网格包布局管理器
       Component comp：组件，可以接收各种类型的组件对象
       GridBagConstraints gbc：网格包约束对象
       以及以下整型参数 gridx, gridy, gridheight, gridwidth, weight_x, weight_y
       其中gridx, gridy用来指定组件左上角在网格中的行和列,容器中最左边列的gridx为0，最上边行的gridy为0；
       gridwidth 和 gridheight用来指定组件显示区域所占的列数和行数，以网格为单元，默认值为 1
       gridheight=0时表示该组件是该列的最后一个组件，gridwidth=0时表示该组件是该行的最后一个组件
       weight_x=1时，组件将占满该行的全部空间, weight_y=1时组件将占满该列的所有空间
    */


    public static void Add_Component(JFrame jfr, GridBagLayout gbl, Component comp, GridBagConstraints gbc,
                                     int gridx, int gridy, int gridheight, int gridwidth, int weight_x, int weight_y) {
        gbc.weightx=weight_x;
        gbc.weighty=weight_y;
        gbc.gridheight=gridheight;
        gbc.gridwidth=gridwidth;
        gbc.gridx=gridx;
        gbc.gridy=gridy;
        gbl.setConstraints(comp, gbc);
        jfr.add(comp);
    }
}
