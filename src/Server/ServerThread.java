package Server;

import MySQL.CheckAccount;
import MySQL.CreateAccount;
import MySQL.InsertMessage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread{
    private final int port;
    private static int loginNum = 0;
    private Socket socket1;
    private Socket socket2;

    public ServerThread(int port) {
        this.port = port;
    }


    public void runServer() throws IOException {
        ServerSocket server;
        server = new ServerSocket(this.port);
        // 处理请求
        Socket loginSocket1 = server.accept();
        Socket loginSocket2 = server.accept();
        loginJudge(loginSocket1);
        loginJudge(loginSocket2);

        // 接收第一个socket
        this.socket1 = server.accept();
        // 接收第二个socket
        this.socket2 = server.accept();
        // 获取第一个socket的名字
        String socket1Name = getContent(socket1);
        // 获取第二个socket的名字
        String socket2Name = getContent(socket2);
        System.out.println("两个客户端已经准备好" + socket1Name + " & " + socket2Name);

        // 将双方的名字写给对方
        OutputStream os1 = socket1.getOutputStream();
        os1.write(socket2Name.getBytes());
        OutputStream os2 = socket2.getOutputStream();
        os2.write(socket1Name.getBytes());


        new Thread(() -> {
            try {
                while (true) {
                        String s1 = getContent(socket1);
                        InsertMessage.insert(s1);
                        if (s1 != null) {
                            OutputStream os = socket2.getOutputStream();
                            os.write(s1.getBytes());
                        }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                while (true) {
                        String s2 = getContent(socket2);
                        InsertMessage.insert(s2);
                        if (s2 != null) {
                            OutputStream os = socket1.getOutputStream();
                            os.write(s2.getBytes());
                        }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }


    public String getContent(Socket socket) throws IOException {
        InputStream is = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len = is.read(bytes);
        return new String(bytes, 0, len);
    }


    public void loginJudge(Socket loginSocket) throws IOException {
        // 先处理登录or创建的socket
        boolean check = false;
        System.out.println("连接到登录界面");
        // 接收处理请求
        String username = getContent(loginSocket);
        String password = getContent(loginSocket);
        String req = getContent(loginSocket);
        System.out.println(req);
        if (req.equals("create")) {
            check = CreateAccount.createAccount(username, password.toCharArray());
        } else if (req.equals("check")) {
            check = CheckAccount.checkAccount(username, password);
        }
        // 根据数据库判断情况传回指示
        OutputStream os;
        os = loginSocket.getOutputStream();
        if (check) {
            os.write(String.valueOf(++loginNum).getBytes());
        } else {
            os.write("0".getBytes());
        }
        loginSocket.close();
    }
}
