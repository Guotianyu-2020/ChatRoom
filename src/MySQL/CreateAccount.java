package MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateAccount {
    public static boolean createAccount(String username, char[] password){
        Statement stmt = null;
        Connection conn = null;
        try {
            // 1.注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2.定义sql
            String sql = "insert into user values('" +username + "', '" + new String(password) + "');";
            // 3.获取Connection对象
            conn = DriverManager.getConnection("jdbc:mysql:///chatroom", "root", "200818");
            // 4.获取执行sql的对象
            stmt = conn.createStatement();
            // 5.执行sql
            int count = stmt.executeUpdate(sql);
            // 6.处理结果
            return count == 1;
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
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
    }
}
