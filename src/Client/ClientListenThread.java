package Client;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientListenThread {
    private Socket socket;

    public ClientListenThread(Socket socket) {
        this.socket = socket;
    }

    public void listen() {
        InputStream is;
        try {
            is = this.socket.getInputStream();
            byte[] bytes = new byte[1024];
            int len = is.read(bytes);
            System.out.println(new String(bytes, 0, len));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}