package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI {
    static JFrame frame = new JFrame();
    static String words;
    static JButton button = new JButton("发送");
    static JTextArea outputs = new JTextArea("消息：");
    static JTextArea inputs = new JTextArea();


    public static void runGUI(){
        // 流式布局
        frame.setLayout(new FlowLayout());
        // 获取Frame容器
        Container container = frame.getContentPane();
        // 设置窗体位置，大小
        frame.setBounds(400, 20, 400, 300);
        // 设置label大小
        outputs.setPreferredSize(new Dimension (360,100));
        // 设置文本区大小
        inputs.setPreferredSize(new Dimension (360,100));
        // 设置输入区自动换行
        inputs.setLineWrap(true);
        // 关闭窗口直接关掉进程
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 输出去设置不可编辑
        outputs.setEditable(false);


        // 添加组件
        container.add(outputs);
        container.add(inputs);
        container.add(button);
        frame.setVisible(true);
        // 事件触发不需要单独设置一个线程，放到GUI线程就行
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientGUI.words = ClientGUI.inputs.getText();
            }
        });
    }
}
