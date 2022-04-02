package Login;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class WriteAndListen {
    // 这个class用来和服务器建立连接
    private Socket socket;

    public WriteAndListen() throws IOException {
        this.socket = new Socket("192.168.158.61", 8888);
    }

    public void write(String name, String password, String op) throws IOException, InterruptedException {
        System.out.println(name);
        System.out.println(password);
        System.out.println(op);
        OutputStream os = socket.getOutputStream();
        os.write(name.getBytes());
        os.write(password.getBytes());
        Thread.sleep(1000);
        os.write(op.getBytes());
    }

    public String listen() throws IOException {
        InputStream is = this.socket.getInputStream();
        byte[] bytes = new byte[4];
        int len = is.read(bytes);
        String s = new String(bytes, 0, len);
        return s;
    }


    public void closeSocket() throws IOException {
        this.socket.close();
    }
}
