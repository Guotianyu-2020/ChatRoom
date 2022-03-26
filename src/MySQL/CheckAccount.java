package MySQL;

import Client.TCPClient1;
import Client.TCPClient2;

import java.io.IOException;
import java.sql.*;

public class CheckAccount {
    public static boolean checkAccount(String username, char[] password) {
        Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            // 1.注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2.定义sql
            String p = new String(password);
            String sql = "select * from user where username = '" + username +"' and password = '" + p +"';";
            // 3.获取Connection对象
            conn = DriverManager.getConnection("jdbc:mysql:///chatroom", "root", "200818");
            // 4.获取执行sql的对象
            stmt = conn.createStatement();
            // 5.执行sql
            rs = stmt.executeQuery(sql);
            // 6.处理结果
            if (rs.next()) {
                // 查到数据
                return true;
            } else {
                // 未查到数据
                return false;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // 7.释放资源，注意要避免空指针异常
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}
