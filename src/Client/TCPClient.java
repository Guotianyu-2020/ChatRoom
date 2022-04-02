package Client;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class TCPClient {
    // 自己客户端的名字
    public String myName;
    // 对方客户端的名字
    public String urName;



    public TCPClient(Socket socket, String name) throws IOException {
        this.myName = name;
        runClient(socket, myName);
    }


    public void runClient(Socket socket, String myName) throws IOException {
        ClientGUI client = new ClientGUI();
        ClientWriteThread cwt = new ClientWriteThread(socket, myName);
        ClientListenThread clt = new ClientListenThread(socket);
        // 上传自己的名字
        cwt.uploadMyName(myName);
        // 下载对方的名字
        byte[] bytes = new byte[1024];
        InputStream is = socket.getInputStream();
        int len = is.read(bytes);
        System.out.println(len);
        urName  = new String(bytes, 0, len);
        clt.setClientName(urName);

        // 听线程
        new Thread(() -> {
            while (!socket.isClosed()) {
                clt.listen(client);
            }
        }).start();


        // 写线程
        new Thread(() -> {
            while (!socket.isClosed()) {
                if (ClientGUI.words != null) {
                    cwt.write(client);
                }
            }
        }).start();

        new Thread(() -> client.runGUI(myName)).start();
    }
}
