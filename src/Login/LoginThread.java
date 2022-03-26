package Login;

import Client.TCPClient1;
import Client.TCPClient2;

import java.io.IOException;

public class LoginThread {
    public static void main(String[] args) throws IOException {
        LoginThread.login();
    }
    public static void login() throws IOException {
        new Thread(() -> {
            try {
                TCPClient1.main(null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                TCPClient2.main(null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
