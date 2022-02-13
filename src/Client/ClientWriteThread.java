package Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientWriteThread {
    private Socket socket;

    public ClientWriteThread(Socket socket) {
        this.socket = socket;
    }

    public void write() {
        try {
            OutputStream os = this.socket.getOutputStream();
            Scanner sc = new Scanner(System.in);
            String s = sc.next();
            os.write(s.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
