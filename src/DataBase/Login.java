package DataBase;

import java.sql.*;

// 数据库登录
public class Login {
    static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    // 数据库的用户名与密码
    static final String username = "root";
    static final String password = "lvxin20020315";
    public Connection DataBase_connection = null;
    public Statement DataBase_state = null;

    private void setting() {
        try {
            // 打开链接
            this.DataBase_connection = DriverManager.getConnection(DB_URL, username, password);
            this.DataBase_state = this.DataBase_connection.createStatement();
        } catch (Exception e) {
            // 处理数据库连接错误
            e.printStackTrace();
        }
    }

    public boolean Search_account(String s1, String s2) {
        boolean flag = false;
        // 链接数据库
        this.setting();
        try {
            // 数据库查询
            String sql = "SELECT * FROM user";
            ResultSet result_sql = this.DataBase_state.executeQuery(sql);
            // 获取信息并转化为string
            while (result_sql.next()) {
                String Uid = result_sql.getString("Uid");
                String PassWord = result_sql.getString("PassWord");
                if (Uid.equals(s1) && PassWord.equals(s2)) {
                    flag = true;
                }
            }
            result_sql.close();
            this.DataBase_state.close();
            this.DataBase_connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public void Insert_account(String s1, String s2, String s3) {
        this.setting();
        String sql = "insert into user (Uid,PassWord) values (" + s1 + "," + s2 + ")";
        try {
            this.DataBase_state = this.DataBase_connection.createStatement();
            this.DataBase_state.executeUpdate(sql);
            this.DataBase_state.close();
            this.DataBase_connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int[] Search_operation(int n){
        int[] ResultSet = new int[n];
        this.setting();
        try {
            // 数据库查询
            String sql = "SELECT * FROM operation";
            ResultSet result_sql = this.DataBase_state.executeQuery(sql);
            // 获取信息并转化为string
            for(int i =0;i<n;i++){
                result_sql.next();
                ResultSet[i] = result_sql.getInt("Action");
            }
            result_sql.close();
            this.DataBase_state.close();
            this.DataBase_connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultSet;
    }

}