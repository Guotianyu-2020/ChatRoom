package Client;

import java.awt.*;
import java.io.IOException;
import java.net.Socket;

public class TCPClient2 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8888);
        TCPClient2.runClient2(socket);
    }


    public static void runClient2(Socket socket) {
        ClientWriteThread cwt = new ClientWriteThread(socket);
        ClientListenThread clt = new ClientListenThread(socket);
        new Thread(() -> {
            while (!socket.isClosed()) {
                clt.listen();
            }
        }).start();

        new Thread(() -> {
            while (!socket.isClosed()) {
                if (ClientGUI.words != null) {
                    cwt.write();
                }
            }
        }).start();


        EventQueue.invokeLater(()->{
            ClientGUI.runGUI();
            ClientGUI.frame.setTitle("Client2");
        });
    }
}