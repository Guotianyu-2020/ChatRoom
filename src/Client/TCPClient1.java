package Client;

import java.io.IOException;
import java.net.Socket;

public class TCPClient1 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("10.238.16.14", 8888);  // 服务端IP地址
        TCPClient1.runClient1(socket);
    }


    public static void runClient1(Socket socket) {
        ClientWriteThread cwt = new ClientWriteThread(socket);
        ClientListenThread clt = new ClientListenThread(socket);
        new Thread(() -> {
            while (!socket.isClosed()) {
                clt.listen();
            }
        }).start();
        new Thread(() -> {
            while (!socket.isClosed()) {
                cwt.write();
            }
        }).start();
    }
}
