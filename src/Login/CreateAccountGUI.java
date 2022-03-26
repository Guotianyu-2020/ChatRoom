package Login;

import MySQL.CreateAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CreateAccountGUI extends LoginGUI{
    static String rName;
    static char[] rPassword;
    public static void runCreate() {

        JFrame jf = new JFrame("Login");
        JButton register = new JButton("Register");
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
        Add_Component(jf, layout, register, gbc,2,5,1,1,0,0);
        jf.setVisible(true);

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rName = username.getText();
                rPassword = password.getPassword();
                boolean check = CreateAccount.createAccount(rName, rPassword);
                if (check) {
                    try {
                        LoginThread.login();
                        jf.dispose();
                    } catch (IOException | InterruptedException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Something wrong", "Notice", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
