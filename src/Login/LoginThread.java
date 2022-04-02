package Login;

import Client.TCPClient;

import java.io.IOException;
import java.net.Socket;

public class LoginThread {
    public static void login(String clientName) throws IOException, InterruptedException {
        TCPClient client = new TCPClient(new Socket("192.168.158.61", 8888), clientName);
    }
}
