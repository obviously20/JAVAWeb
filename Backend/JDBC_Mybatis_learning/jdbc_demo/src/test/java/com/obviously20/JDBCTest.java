package com.obviously20;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class JDBCTest {
    //DML更新操作
    @Test
    public void testJDBCUpdate() throws ClassNotFoundException, SQLException {
        //1.加载驱动(在项目中引入mysql-connector-j依赖+现在下面配置的MySQL厂商的实现类)
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.注册连接池（格式：jdbc:mysql://主机名（IP）:端口号/数据库名）
        String url = "jdbc:mysql://localhost:3306/jdbc_learning";
        //url,用户名,密码
        Connection conn = DriverManager.getConnection(url, "root", "123456");
        //3.创建Statement对象（用于执行SQL语句）
        Statement statement = conn.createStatement();
        //4.执行SQL语句（update/delete/insert）
        //executeUpdate()方法返回值为影响的记录数
        int i = statement.executeUpdate("update user set age=22 where id=1");
        System.out.println("更新了" + i + "条记录");
        //5.关闭资源
        statement.close();
        conn.close();
    }

    //DQL查询操作
    @Test
    public void testJDBCQuery() throws SQLException {
//        System.out.println("查询操作");
        //todo 创建ResultSet对象（用于接收查询结果）
        ResultSet resultSet = null;
        //todo 创建PreparedStatement对象（用于执行SQL语句）
        PreparedStatement preparedStatement = null;
        //todo 创建Connection对象（用于连接数据库）
        Connection conn = null;

        try {
            //加载驱动(在项目中引入mysql-connector-j依赖+现在下面配置的MySQL厂商的实现类)
            Class.forName("com.mysql.cj.jdbc.Driver");
            //注册连接池（格式：jdbc:mysql://主机名（IP）:端口号/数据库名）
            String url = "jdbc:mysql://localhost:3306/jdbc_learning";
            //url,用户名,密码
            conn = DriverManager.getConnection(url, "root", "123456");

            //执行 执行SQL语句（select）
            String sql = "select id,username,password,name,age from user where username=? and password=?";//todo ?表示占位符，预编译：用于防止SQL注入攻击
            //创建PreparedStatement对象（用于执行SQL语句）
            preparedStatement = conn.prepareStatement(sql);
            //设置参数PreparedStatement.setString(1,"daqiao");
            preparedStatement.setString(1, "daqiao");
            preparedStatement.setString(2, "123456");
            //将查询结果集赋值给resultSet（1/多条记录）
            resultSet = preparedStatement.executeQuery();
            //todo 处理结果集
            //todo resultSet.next()方法返回值为boolean类型，用于判断是否有下一条记录,如果有则返回true,没有则返回false
            while (resultSet.next()) {
                User user = new User(
                        //todo .getXXX()方法用于获取结果集中的字段值（根据字段的类型）
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getInt("age")
                );
                System.out.println(user);
            }
        } catch (Exception e) {
            // 打印异常信息
            e.printStackTrace();
        } finally {
            try {
                //关闭资源
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //insert操作测试
    //DML插入操作
    @Test
    public void testJDBCInsert() throws SQLException {
        PreparedStatement preparedStatement1 = null;
        Connection conn1 = null;

        try {
            // 1. 加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. 建立连接
            String url = "jdbc:mysql://localhost:3306/jdbc_learning";
            conn1 = DriverManager.getConnection(url, "root", "123456");

            // 3. 准备SQL语句（使用PreparedStatement防止SQL注入）
            String sql = "INSERT INTO user (username, password, name, age) VALUES (?, ?, ?, ?)";
            preparedStatement1 = conn1.prepareStatement(sql);

            // 4. 设置参数（索引从1开始）
            preparedStatement1.setString(1, "zhangfei");  // username
            preparedStatement1.setString(2, "654321");   // password
            preparedStatement1.setString(3, "张飞");     // name
            preparedStatement1.setInt(4, 35);            // age

            // 5. 执行插入操作
            int affectedRows = preparedStatement1.executeUpdate();
            System.out.println("插入成功，影响了 " + affectedRows + " 条记录");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6. 关闭资源（逆序关闭）
            try {
                if (preparedStatement1 != null) preparedStatement1.close();
                if (conn1 != null) conn1.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /*todo 为什么要使用预编译SQL?
        安全(防止SQL注入)
        性能更高*/
}
