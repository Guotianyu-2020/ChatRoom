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
        ClientGUI client2 = new ClientGUI();
        ClientWriteThread cwt = new ClientWriteThread(socket, "Client2");
        ClientListenThread clt = new ClientListenThread(socket, "Client1");
        new Thread(() -> {
            while (!socket.isClosed()) {
                clt.listen(client2);
            }
        }).start();

        new Thread(() -> {
            while (!socket.isClosed()) {
                if (ClientGUI.words != null) {
                    cwt.write(client2);
                }
            }
        }).start();

        new Thread(() -> {
            client2.runGUI("Client2");
        }).start();
    }
}