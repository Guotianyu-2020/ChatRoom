package Login;

import Client.TCPClient1;
import Client.TCPClient2;

import java.io.IOException;

public class LoginThread {
    public static void login() throws IOException, InterruptedException {
        TCPClient1.main(null);
        TCPClient2.main(null);
    }
}
