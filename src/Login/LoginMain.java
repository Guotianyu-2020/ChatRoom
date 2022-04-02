package Login;

import java.io.IOException;

public class LoginMain {
    // 1.客户端没与sql分离
    // 2.建立连接后接收不到对方的数据

    public static void main(String[] args) throws IOException {
        // start from here
        LoginGUI.runLoginGUi();
    }
}
