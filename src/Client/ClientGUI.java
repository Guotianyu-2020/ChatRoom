package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ClientGUI {
    static String words;
    JTextArea inputs = new JTextArea(15, 30);
    JTextArea outputs = new JTextArea("消息：", 15, 30);

    public void runGUI(String name){
        // 初始化界面和组件
        JFrame frame = new JFrame();

        frame.setTitle(name);
        JButton button = new JButton("发送");
        JPanel jp = new JPanel();
        JPanel jp2 = new JPanel();
        //新建一个滚动条界面，将文本框传入
        JScrollPane jsp=new JScrollPane(outputs);
        JScrollPane jsp2=new JScrollPane(inputs);

        // 流式布局
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        // 将滚动条界面添加到组建中，而不是添加文本框了
        jp.add(jsp);
        jp.add(jsp2);
        // 添加组件
        frame.add(jp);
        frame.add(jp2);
        frame.add(button);
        // 设置GUI大小
        frame.setSize(700,350);
        // frame.pack();
        // 设置GUI初始位置
        frame.setLocation(300,300);
        // 设置输入区自动换行
        inputs.setLineWrap(true);
        // 关闭窗口直接关掉进程
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 输出去设置不可编辑
        outputs.setEditable(false);
        // 设置可见
        frame.setVisible(true);
        // 事件触发不需要单独设置一个线程，放到GUI线程就行
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientGUI.words = inputs.getText();
            }
        });
        inputs.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER)
                {
                    //此处为要按下回车后触发的动作，应与按下登录按钮后实现的功能相同
                    ClientGUI.words = inputs.getText();
                }
            }
        });
    }
}
