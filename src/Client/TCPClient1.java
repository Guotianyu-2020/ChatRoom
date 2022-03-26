package Client;

import java.awt.*;
import java.io.IOException;
import java.net.Socket;

public class TCPClient1 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8888);  // 服务端IP地址
        TCPClient1.runClient1(socket);
    }


    public static void runClient1(Socket socket) {
        ClientWriteThread cwt = new ClientWriteThread(socket, "Client1");
        ClientListenThread clt = new ClientListenThread(socket, "Client2");
        // 听线程
        new Thread(() -> {
            while (!socket.isClosed()) {
                clt.listen();
            }
        }).start();


        // 写线程
        new Thread(() -> {
            while (!socket.isClosed()) {
                if (ClientGUI.words != null) {
                    cwt.write();
                }
            }
        }).start();


        // GUI界面
        EventQueue.invokeLater(()->{
            ClientGUI.runGUI();
            ClientGUI.frame.setTitle("Client1");
        });
    }
}
