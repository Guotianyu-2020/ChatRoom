package Server;

import MySQL.InsertMessage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread{
    private int port;
    private Socket socket1;
    private Socket socket2;


    public ServerThread(int port) {
        this.port = port;
    }


    public void runServer() throws IOException {
        ServerSocket server;
        server = new ServerSocket(this.port);
        this.socket1 = server.accept();
        this.socket2 = server.accept();
        System.out.println("两个客户端已经准备好");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        InputStream is1 = socket1.getInputStream();
                        byte[] bytes = new byte[1024];
                        int len1 = is1.read(bytes);
                        String s1 = new String(bytes, 0, len1);
                        System.out.println("客户端1的消息是" + s1);
                        InsertMessage.insert(s1);
                        if (len1 != -1) {
                            OutputStream os = socket2.getOutputStream();
                            os.write(s1.getBytes());
                        }
                        System.out.println("已发出客户端1的消息");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        InputStream is2 = socket2.getInputStream();
                        byte[] bytes = new byte[1024];
                        int len2 = is2.read(bytes);
                        String s2 = new String(bytes, 0, len2);
                        System.out.println("客户端2的消息是" + s2);
                        InsertMessage.insert(s2);
                        if (len2 != -1) {
                            OutputStream os = socket1.getOutputStream();
                            os.write(s2.getBytes());
                        }
                        System.out.println("已发出客户端2的消息");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
